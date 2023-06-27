# Passage4J + Java Microservices Framework Examples
Now you implement seamless passwordless authentication in your favourite Microservice framework with Passage4J by [Passage](https://passage.id/).

This repository showcases examples of how to integrate with each framework.

## Passage
Know more about passage [https://docs.passage.id/](https://docs.passage.id/)

## Microservice framwork included;
- Spring Boot
- Micronaut
- VertX
- Quarkus
- React web application for a demo UI

### Prerequisite for running demo
- docker-compose
- Internet connection
- Make sure port 8080 and 3000 are free
- Starting any of the above command also starts the react web application accesible at [http://localhost:8080](http://localhost:8080).


### Prerequisite for development
- JDK 17
- Maven
- docker-compose

## Quick run example
As I also believe developer first experience 😄

**Clone the repository**
```shell
git clone https://github.com/godwinpinto/example-passage4j.git
cd example-passage4j
```

**Setup Passage application from passage console**
1. Visit Passage console [https://console.passage.id/login](https://console.passage.id/login)
2. Create an app
3. Go to Settings-> General
   1. Authentication Origin: **http://localhost:3000**
   2. Redirect URL: **/dashboard**
   3. Login URL: **/**
4. Create API_KEY: Settings->API Keys
5. Copy the app id and api key

**Add your APP_ID and API_KEY to .env file**
```shell
vi .env
```

## Few notes
If you get a message "Passage Error - , message: Failed to fetch JWKS". stop the docker-compose (CTRL+C) and run again. Investigation is under way.

### Spring boot demo
```shell
docker-compose -f docker-compose-dev.yml --env-file .env up example-web example-spring

```
Go to [http://localhost:3000](http://localhost:3000)
### Micronaut
```shell
docker-compose -f docker-compose-dev.yml --env-file .env up example-web example-micronaut

```
Go to [http://localhost:3000](http://localhost:3000)

### VertX
```shell
docker-compose -f docker-compose-dev.yml --env-file .env up --build example-web example-vertx
```
Go to [http://localhost:3000](http://localhost:3000)

### Quarkus
```shell
docker-compose -f docker-compose-dev.yml --env-file .env up --build example-web example-quarkus
```
Go to [http://localhost:3000](http://localhost:3000)

### Note
Examples are to be used a as reference point for integration of **Passage** with Java frameworks. You should choose the right security format as per the framework, review your architecture as per your needs for production.
