# TinyURL

# auth-server

Server responsible with verifying the identity of users by checking their provided credentials (username and password) and grants access to authorized resources based on successful verification.

> [!NOTE]
> **Run Spring Boot application**

> mvn spring-boot:run


# service-registry

Service used to keep track of the available instances of each microservice in the application.

> [!NOTE]
> **Run Spring Boot application**

> mvn spring-boot:run


# config-server

Server responsible for providing a central place to manage external properties for microservices (like server port).

> [!NOTE]
> **Run Spring Boot application**

> mvn spring-boot:run


# zipkin
Zipkin is a distributed tracing system. It helps gather timing data needed to troubleshoot latency problems in service architectures. Features include both the collection and lookup of this data.
This project use a 	$${color{blue}docker}$$ image for zipkin.

> [!NOTE]
> **Run Zipkin**

> docker run -d -p 9411:9411 openzipkin/zipkin
