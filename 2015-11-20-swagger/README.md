# [![nToggle]
(http://static1.squarespace.com/static/54aff901e4b09d0a90426749/t/54aff95fe4b0fbcca432e54d/1423161066764/?format=1500w)](http://www.ntoggle.com/)
# Overview of Swagger

We have chosen to use the Swagger standard for providing REST API documentation within the Kubitschek project.
The Kubitschek project uses the Swagger JSON format to provide developer documentation for the configuration API.

# Some Background Notes
The Swagger specification is a JSON schema specification targeted toward providing high quality REST API documentation.
Apigee, the online API gateway provider seems to have both used this framework and provided enhancements over a period of years.
There is a whole ecosystem of tools that rely on this format to provide

* interactive documentation - see our test server [here](http://apitest.internal.ntoggle.com/api/v1/docs/). Log in as 'ntoggle' user with the usual 'chilly' password.
* static documentation - HTML self-contained files.
* client libraries generated in many languages - Java, JavaScript (Angular and other frameworks), Python and many others

# Some Usage Notes
We have found it best to run a Swagger JSON file through both the online editor (link below) and a JSON validator, before publishing / committing a new version.
Neither tool by itself catches all the common errors and typos you are likely to make when hand-editing JSON files. The online editor is relatively new and
provides some validation of object references and proper Swagger syntax (required attribute names and object structures), but it does miss some common JSON
errors like extra or missing commas.

There is currently no automation or validation between the Scala code and the Swagger JSON specification. For now it is a manual process
to keep the code in sync with the documentation. It is possible that there are toolkits that will parse the JSON specification and generate a test suite,
this should be investigated.

# Links

[Swagger Specification](http://swagger.io/specification/)

[Editor Overview](http://swagger.io/swagger-editor/)

[Online Swagger Editor](http://editor.swagger.io/#/)

[Blog Post on Swagger Project](http://smartbear.com/news/news-releases/swagger-creator-tony-tam-joins-smartbear-software/)