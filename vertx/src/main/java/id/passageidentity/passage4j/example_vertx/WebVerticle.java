package id.passageidentity.passage4j.example_vertx;

import id.passageidentity.passage4j.example_vertx.bean.ExampleUserBean;
import id.passageidentity.passage4j.core.app.Passage;
import id.passageidentity.passage4j.core.app.PassageConfig;
import id.passageidentity.passage4j.core.app.PassageNew;
import id.passageidentity.passage4j.core.bean.UserBean;
import id.passageidentity.passage4j.core.exception.PassageException;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.User;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;


public class WebVerticle extends AbstractVerticle {

  private Passage passage;

  @Override
  public void start(Promise<Void> startPromise) throws PassageException {

    String passageAppID = System.getenv("PASSAGE_APP_ID");
    String passageApiKey = System.getenv("PASSAGE_API_KEY");

    HttpServer server = vertx.createHttpServer();
    PassageConfig passageConfig = new PassageConfig();
    passageConfig.setApiKey(passageApiKey);
    passageConfig.setHeaderAuth(true);
    passage = new PassageNew(passageAppID, passageConfig);

    Router router = Router.router(vertx);
    // Enable JSON body handling
    router.route().handler(BodyHandler.create());

    // Define the login endpoint
    router.route(HttpMethod.POST, "/api/login").handler(this::jwtAuthHandler).handler(this::handleLogin);

    // Define the dashboard endpoint with custom JWT handler
    router.route(HttpMethod.GET, "/api/dashboard").handler(this::jwtAuthHandler).handler(this::handleDashboard);

    server.requestHandler(router).listen(8080, http -> {
      if (http.succeeded()) {
        startPromise.complete();
        System.out.println("HTTP server started on port 8080");
      } else {
        System.out.println("Failed to start HTTP server");
        startPromise.fail(http.cause());
      }
    });
  }

  private void handleLogin(RoutingContext routingContext) {
    //get the request body
    JsonObject jsonObject = routingContext.body().asJsonObject();
    //do some audit activities or set the user details
    routingContext.response()
      .putHeader("Content-Type", "application/json")
      .end(new JsonObject().put("response", "ok").encode());
  }

  private void handleDashboard(RoutingContext routingContext) {
    // Access the username from the JWT token
    String userID = routingContext.user().subject();
    try {
      UserBean userBean = passage.user().getInfo(userID);
      routingContext.response()
        .putHeader("Content-Type", "application/json")
        .end(JsonObject.mapFrom(userBean).encode());
    } catch (PassageException e) {
      e.printStackTrace();
      routingContext.response().setStatusCode(401).end(JsonObject.mapFrom(e).encode());
    }
  }


  private void jwtAuthHandler(RoutingContext routingContext) {
    String token = routingContext.request().getHeader("Authorization");
    if (token != null && token.startsWith("Bearer ")) {
      String jwt = token.substring(7); // Remove "Bearer " prefix
      try {
        String userID = passage.auth().authenticateRequestWithAuthHeader(token);
        User user = new ExampleUserBean(userID);
        routingContext.setUser(user);
        routingContext.next();
      } catch (PassageException e) {
        routingContext.response().setStatusCode(401).end();
      }
    } else {
      routingContext.response().setStatusCode(401).end();
    }
  }
}
