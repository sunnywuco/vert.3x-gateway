# 基于Vert3.x和Spring-boot的micro server的微服务平台
drools
======
An experiment in creating a minimal Drools web service using Spring Boot and vert.3x framework.

A little while back, I knocked up Qzr (https://github.com/gratiartis/qzr) to demonstrate using Spring Boot (http://projects.spring.io/spring-boot/) with the Drools rules engine (http://www.drools.org/). However, I also wanted to play around with a few more technologies (AngularJS and Spring HATEOAS), so it's a bit large for just demonstrating exposing Drools rules as an HTTP web service.

A few folks have mentioned that there was a bit too much going on for a beginner to pick out the essentials of running Drools in a Spring Boot application. So I thought I'd have a go at creating a simpler application, which does nothing more than that.

For the rules, I took my cues from the pay Pass example in the JBPM project:

https://github.com/droolsjbpm/drools/

I have cut the rules down a little bit and reduced the code by replacing some of the Java fact classes with DRL declared types. I prefer this for facts which are only referenced from within the DRL.

Assuming that you have a reasonably recent install of Maven and the JDK 
Base in Vertx3x & Spring-boot framework , Micro-server platform support asyn framework to handle complex business.

- [Spring Boot](http://projects.spring.io/spring-boot/)
- [Vertx 3.0](http://vertx.io/)
- [SpringBoot使用zookeeper作为配置中心](http://segmentfault.com/a/1190000004356362)

## Requirements
     $ maven and jdk 8
    
## Building from Source

     $ mvn clean install -Dmaven.test.skip=true -X
    
## Test
     $ mvn spring-boot:run -X

## Debug
     $ debug Application.java
     
     
## zookeeper
```shell
zkCli.sh -server = 120.55.243.86:2181
create /config ""
create /config/demoapp ""
create /config/demoapp/msg helloworld
create /config/demoapp/first ""
create /config/demoapp/first/second tree
quit
```
## deploy
     $ java -jar stone-micro-1.0-SNAPSHOT.jar




