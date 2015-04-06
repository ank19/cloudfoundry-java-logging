# cloudfoundry-java-logging
Injects Cloud Foundry specific variables into a variety of Java logging frameworks. Cloud Foundry injects environment
variables into your applications at runtime. These environment variables include useful information that can help
consumers of log messages associate multiple application instances into consolidated dashboards and/or reports. Currently,
this library focuses on parsing the VCAP_APPLICATION environment variable, which Cloud Foundry injects as a JSON string.
A full list of Cloud Foundry environment variables can be found [here](http://docs.run.pivotal.io/devguide/deploy-apps/environment-variable.html) 


## log4j
Currently only supports log4j v2. This library uses [log4j's plugin extension point](https://logging.apache.org/log4j/2.x/manual/plugins.html) 
to allow you to use this library as a plug and play extension within your application while maintaining external patterns 
with PatternLayout.

To use this library, add the following to your log4j2.xml file's Configuration element:

```xml
<Configuration status="WARN" packages="io.pivotal.cloudfoundry.log4j">
```

Then you may use the custom cf namespace as a prefix for Cloud Foundry placeholders in your PatternLayout. Here is
an example:

```xml
<Appenders>
  <Console name="console" target="SYSTEM_OUT">
    <PatternLayout pattern="%d{HH:mm:ss.SSS} [%t] %-5level [CF-AppName:${cf:appName} CF-SpaceName:${cf:spaceName}] %c{1}:%L - %m%n"/>
  </Console>
</Appenders>
```

Currently, the only supported placeholders are `${cf:appName}` and `${cf:spaceName}`.

