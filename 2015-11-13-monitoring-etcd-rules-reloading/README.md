# [![nToggle](http://static1.squarespace.com/static/54aff901e4b09d0a90426749/t/54aff95fe4b0fbcca432e54d/1423161066764/?format=1500w)](http://www.ntoggle.com/) Monitoring etcd rules configuration reloading

Within the nRoute platform, the helix-service component is responsible for receiving supply partner bid requests and routing them to demand partners according to demand partner-provided rules.  helix-service supports dynamic rules configuration reloading at run-time by polling the configuration store, etcd.  Since reloading rules configuration is a critical business process, monitoring was added to the reload process.  This presentation outlines how monitoring the monitoring solution was conceived and lessons learned along the way.

# Guiding principles for monitoring design
We found the following principles helpful to guide us towards a flexible and robust monitoring solution.  These principles can be applied to other monitoring concerns.

| Principle | Why is it useful? |
| --------- | ----------------- |
| Support changing the alerting mechanism without changing the system-under-observation | From past experience, it is frequently the case that the alerting mechanism needs to be tweaked (e.g. alert after 5 warnings instead of 3).  Baking the alerting mechanism into the system-under-observation requires re-deploying the system-under-observation, which may be untenable and increases cycle time. |
| Be able to identify when the system-under-observation fails | This monitoring principle captures the need to consider boundary conditions.  However the monitoring system is designed, it should be able to detect a scenario where the system-under-observation fails to emit any information. |

# Solution
The solution for monitoring etcd rule configuration changes revolved around creating Datadog monitors based off of events emitted from helix-service.  This approach decouples the alerting mechanism (Datadog) from the system-under-observation (helix-service).  Below are the events helix-service emits:
```
sealed trait Event
case object ConfigurationLoaded extends Event
case object ConfigurationApplied extends Event
case object InvalidConfigurationDetected extends Event
case object EtcdCommunicationErrorDetected extends Event
case object NoRuleConfigurationStored extends Event
```

ConfigurationLoaded means a semantically valid rule configuration was retrieved from etcd, but not necessarily applied to the helix routing engine.  ConfigurationApplied indicates the loaded configuration is a different version (as denoted by the etcd document version) than the configuration in the routing engine.  In this scenario, the loaded configuration was applied to the routing engine to overwrite the existing configuration.  The remaining three events denote different error scenarios.

This set of events supports adding the following alerts:
* When the configuration fails to load repeatedly by counting number of error events
* When the reloading mechanism fails to work (i.e. the second principle) by looking for zero error and configuration loaded events

# Implementation
While working on this feature, we learned (courtesy of Ashley) there is first-class support in Datadog for emitting [events](http://docs.datadoghq.com/guides/dogstatsd/#events).  This is an extension to the StatsD protocol, which means the existing Codahale metrics library does not support emitting events.  There are several clients implementing the DogStatsD protocol available, we ended up using [this one](https://github.com/indeedeng/java-dogstatsd-client) because it looks well-supported.

Below are segments of the ansible automation used to create the two Datadog monitors.  See [datadog.yml](https://github.com/nToggle/ansible/blob/master/roles/ntoggle.helix-service/tasks/datadog.yml) for complete examples.
```
    query="events(\'tags:environment:production "helix-service.configuration.failure.*"\').by(\'dp_name,sp_name\').rollup(\'count\').last(\'5m\') >= 3"
    message="\\{\\{sp_name\\}\\}-\\{\\{dp_name\\}\\}:3+ configuration errors in last 5 minutes @victorops"
```
```
    query="events(\'tags:environment:production "helix-service.configuration.*"\').by(\'dp_name,sp_name\').rollup(\'count\').last(\'5m\') == 0"
    message="\\{\\{sp_name\\}\\}-\\{\\{dp_name\\}\\}:Zero configuration updates (success or failure) received in last 5 minutes @victorops"
```
