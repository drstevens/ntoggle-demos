# [![nToggle](http://static1.squarespace.com/static/54aff901e4b09d0a90426749/t/54aff95fe4b0fbcca432e54d/1423161066764/?format=1500w)](http://www.ntoggle.com/) ngrep and jq demo

Analyzing the features of traffic received by nRoute from a supply partner and 
traffic from nRoute to a demand partner is a common occurrence for debugging 
connectivity and correctness of configured rules.  This demo shows how to use
[ngrep](http://ngrep.sourceforge.net/) and [jq](https://stedolan.github.io/jq/)
to quickly grok JSON bid request traffic.

# Files
[ngrep-output.txt](ngrep-output.txt) - Output of running 
`ngrep -q -W byline port 4242` on Samdex used to illustrate jq usage

[annotated-examples.txt](annotated-examples.txt) - Commands shown in demo plus
examples provided by attendees

