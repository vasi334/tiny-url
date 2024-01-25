# TinyURL backend

**This service will provide short aliases redirecting to long URLs.**

## auth-server

Server responsible with verifying the identity of users by checking their provided credentials (username and password) and grants access to authorized resources based on successful verification.

> [!NOTE]
> **Run Spring Boot application**

> mvn spring-boot:run


## service-registry

Service used to keep track of the available instances of each microservice in the application.

> [!NOTE]
> **Run Spring Boot application**

> mvn spring-boot:run


## config-server

Server responsible for providing a central place to manage external properties for microservices (like server port).

> [!NOTE]
> **Run Spring Boot application**

> mvn spring-boot:run


## zipkin
Zipkin is a distributed tracing system. It helps gather timing data needed to troubleshoot latency problems in service architectures. Features include both the collection and lookup of this data.
This project use a 	${\color{lightblue}docker}$ image for zipkin.

> [!NOTE]
> **Run Zipkin**

> docker run -d -p 9411:9411 openzipkin/zipkin


## api-gateway

API Gateway is a service that acts as a central entry point for the TinyURL backend microservices. It handles incoming requests, performs necessary routing, and provides a unified interface to access various functionalities of the system.

# Prerequisites

> [!NOTE]
> Before running the API Gateway, ensure that the following components are set up and running:

- auth-server: Server for user authentication.
- service-registry: Service registry to keep track of microservice instances.
- config-server: Server for managing external properties for microservices.
- zipkin: Distributed tracing system for troubleshooting latency issues.
