# Passage4J + Quarkus

This example project uses Quarkus, the Supersonic Subatomic Java Framework + Passage4j, from Passage, a seamless passwordless authentication.

- Learn more about [Passage](https://docs.passage.id/)
- Learn more about [Quarkus](https://quarkus.io/).

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

Create application scope object to be easily injected into your Quarkus classes

```java
public class PassageProducer {
  @ApplicationScoped
  public Passage getPassage(@ConfigProperty(name = "passage.app-id") String passageAppID,
      @ConfigProperty(name = "passage.api-key") String passageApiKey) throws PassageException {
    PassageConfig passageConfig = new PassageConfig();
    passageConfig.setApiKey(passageApiKey);
    passageConfig.setHeaderAuth(true);
    return new PassageNew(passageAppID, passageConfig);
  }
}
```
Now inject as your like in your quarkus classes

```java
  @Inject
  Passage passage;
```

## Running the application
Visit [Quarkus](https://quarkus.io/) for detailed tutorials
