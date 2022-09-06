![example workflow](https://github.com/joao4018/api-login/actions/workflows/maven.yml/badge.svg?branch=master)
![example workflow](https://github.com/joao4018/api-login/actions/workflows/docker-image.yml/badge.svg?branch=master)


mvn -N io.takari:maven:wrapper

# api-login

Commits will only be accepted with the tags listed below in their prefix

Coommit: 
Configure: 
Test: 
Create: 
Refactor: 

# Getting Started


### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.5.2/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.5.2/maven-plugin/reference/html/#build-image)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.5.2/reference/htmlsingle/#using-boot-devtools)



# spring-boot
The main library providing features that support the other parts of Spring Boot. These include:

* The `SpringApplication` class, providing static convenience methods that can be used to write a stand-alone Spring Application.
  Its sole job is to create and refresh an appropriate Spring `ApplicationContext`.
* Embedded web applications with a choice of container (Tomcat, Jetty, or Undertow).
* First-class externalized configuration support.
* Convenience `ApplicationContext` initializers, including support for sensible logging defaults.



# spring-boot-autoconfigure
Spring Boot can configure large parts of typical applications based on the content of their classpath.
A single `@EnableAutoConfiguration` annotation triggers auto-configuration of the Spring context.

Auto-configuration attempts to deduce which beans a user might need. For example, if `HSQLDB` is on the classpath, and the user has not configured any database connections, then they probably want an in-memory database to be defined.
Auto-configuration will always back away as the user starts to define their own beans.



# spring-boot-starters
Starters are a set of convenient dependency descriptors that you can include in your application.
You get a one-stop shop for all the Spring and related technology you need without having to hunt through sample code and copy-paste loads of dependency descriptors.
For example, if you want to get started using Spring and JPA for database access, include the `spring-boot-starter-data-jpa` dependency in your project, and you are good to go.



# spring-boot-actuator
Actuator endpoints let you monitor and interact with your application.
Spring Boot Actuator provides the infrastructure required for actuator endpoints.
It contains annotation support for actuator endpoints.
This module provides many endpoints, including the `HealthEndpoint`, `EnvironmentEndpoint`, `BeansEndpoint`, and many more.



# spring-boot-actuator-autoconfigure
This provides auto-configuration for actuator endpoints based on the content of the classpath and a set of properties.
For instance, if Micrometer is on the classpath, it will auto-configure the `MetricsEndpoint`.
It contains configuration to expose endpoints over HTTP or JMX.
Just like Spring Boot AutoConfigure, this will back away as the user starts to define their own beans.



# spring-boot-test
This module contains core items and annotations that can be helpful when testing your application.



# spring-boot-loader
Spring Boot Loader provides the secret sauce that allows you to build a single jar file that can be launched using `java -jar`.
Generally, you will not need to use `spring-boot-loader` directly but work with the link:spring-boot-project/spring-boot-tools/spring-boot-gradle-plugin[Gradle] or link:spring-boot-project/spring-boot-tools/spring-boot-maven-plugin[Maven] plugin instead.


