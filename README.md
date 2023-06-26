# Passage4J + Java Microservices Framework Examples
Now you implement seamless passwordless authentication in your favourite Microservice framework with Passage4J.

This repository showcase examples of it is done.

## Microservice framwork included;
- Spring Boot
- Micronaut
- VertX
- Quarkus
- React web application for a demo UI

### Prerequisite for running demo
- docker-compose
- Make sure port 8080 and 3000 are free
- Starting any of the above command also starts the react web application accesible at [http://localhost:8080](http://localhost:8080).


### Prerequisite for development
- JDK 17
- Maven
- docker-compose

## Quick run example

Clone the repository
```shell
git clone https://github.com/godwinpinto/example-passage4j.git
cd example-passage4j
```

Spring boot
```shell
docker-compose -f docker-compose-dev.yml --env-file .env up example-web example-spring

```
Micronaut
```shell
docker-compose -f docker-compose-dev.yml --env-file .env up example-web example-micronaut

```
VertX
```shell
docker-compose -f docker-compose-dev.yml --env-file .env up --build example-web example-vertx
```

Quarkus
```shell
docker-compose -f docker-compose-dev.yml --env-file .env up --build example-web example-quarkus
```

