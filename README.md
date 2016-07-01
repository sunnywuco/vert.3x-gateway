# 基于Vert3.x和Spring-boot的micro server的微服务平台

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




