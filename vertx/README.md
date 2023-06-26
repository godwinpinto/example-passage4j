## Passage4J + VERT.X

This example project uses Vertx Java Framework + Passage4j, from Passage, a seamless passwordless authentication.

- Learn more about [Passage](https://docs.passage.id/)
- Learn more about [Vert.x](https://vertx.io/docs/).

## Passage4J's Seamless Integration
1. Visit Passage console and create an app [https://console.passage.id/login](https://console.passage.id/login)
2. copy the app id and api key and set the environment variables

```shell
PASSAGE_APP_ID=XXXXX
PASSAGE_API_KEY=XXXXX
```
Create Passage object
```java
  String passageAppID = System.getenv("PASSAGE_APP_ID");
  String passageApiKey = System.getenv("PASSAGE_API_KEY");
  PassageConfig passageConfig = new PassageConfig();
  passageConfig.setApiKey(passageApiKey);
  passageConfig.setHeaderAuth(true);
  passage = new PassageNew(passageAppID, passageConfig);
```
Now Use the value as your like in your vertx application

```java
 String userID = passage.auth().authenticateRequestWithAuthHeader(token);
```

Is there a different way to create a global object? Yes. Service proxies, if you want to isolate the passage object.
More information, refer vertx [documentation](https://vertx.io/docs/vertx-service-proxy/java/)

## Running the application
Visit [Vertx](https://vertx.io/get-started/) for detailed tutorials






