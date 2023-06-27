# Passage4J + Spring-Boot

This example project uses Spring-Boot, the popular Java Framework + Passage4j, from Passage, a seamless passwordless authentication.

- Learn more about [Passage](https://docs.passage.id/)
- Learn more about [Quarkus]([https://quarkus.io/](https://spring.io/projects/spring-boot)).

## Passage4J's Seamless Integration
1. Visit Passage console and create an app [https://console.passage.id/login](https://console.passage.id/login)
2. copy the app id and api key and set the environment variables

```shell
PASSAGE_APP_ID=XXXXX
PASSAGE_API_KEY=XXXXX
```

Setup the variables in application.properties
```properties
passage.app-id=${PASSAGE_APP_ID}
passage.api-key=${PASSAGE_API_KEY}
```

Create a configuration class to create Passage bean that can help to injected into your Spring classes

```java
  @Value("${passage.app.id}")
  String passageAppID;

  @Value("${passage.api.key}")
  String passageApiKey;

  @Bean
  Passage getPassageObject() throws PassageException {
    PassageConfig passageConfig = new PassageConfig();
    passageConfig.setApiKey(passageApiKey);
    passageConfig.setHeaderAuth(true);
    return new PassageNew(passageAppID, passageConfig);
  }

```
Now inject as your like in your quarkus classes

```java
  @Autowired
  Passage passage;
```

## Running the application
Visit [Spring Boot](https://spring.io/projects/spring-boot) for detailed tutorials
