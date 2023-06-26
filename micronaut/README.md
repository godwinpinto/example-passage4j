## Passage4J + Micronaut

This example project uses Micronaut Java Framework + Passage4j, from Passage, a seamless passwordless authentication.

- Learn more about [Passage](https://docs.passage.id/)
- Learn more about [Micronaut](https://docs.micronaut.io/3.9.4/guide/index.html).

## Passage4J's Seamless Integration
1. Visit Passage console and create an app [https://console.passage.id/login](https://console.passage.id/login)
2. copy the app id and api key and set the environment variables

```shell
PASSAGE_APP_ID=XXXXX
PASSAGE_API_KEY=XXXXX
```
Setup the variables in application.yml
```yaml
passage:
  app-id: ${PASSAGE_APP_ID}
  api-key: ${PASSAGE_API_KEY}
```
Create application scope object to be easily injected into your Quarkus classes
```java
@Factory
public class PassageFactory {

  @Singleton
  public Passage getPassage(@Value("${passage.app-id}") String passageAppID,
      @Value("${passage.api-key}") String passageApiKey)
      throws PassageException {
    PassageConfig passageConfig = new PassageConfig();
    passageConfig.setApiKey(passageApiKey);
    passageConfig.setHeaderAuth(true);
    return new PassageNew(passageAppID, passageConfig);
  }
}
```
Now inject as your like in your micronaut classes

```java
  @Inject
  Passage passage;
```

## Running the application
Visit [Micronaut](https://docs.micronaut.io/3.9.4/guide/index.html) for detailed tutorials






