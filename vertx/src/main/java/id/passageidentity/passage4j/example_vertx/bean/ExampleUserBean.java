package id.passageidentity.passage4j.example_vertx.bean;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.AuthProvider;
import io.vertx.ext.auth.User;
import io.vertx.ext.auth.authorization.Authorization;
import io.vertx.ext.auth.authorization.Authorizations;

public class ExampleUserBean implements User {

  private final String username;

  public ExampleUserBean(String username) {
    this.username = username;
  }

  @Override
  public String subject() {
    return User.super.subject();
  }

  @Override
  public JsonObject attributes() {
    return null;
  }

  @Override
  public boolean expired() {
    return User.super.expired();
  }

  @Override
  public boolean expired(int leeway) {
    return User.super.expired(leeway);
  }

  @Override
  public <T>  T get(String key) {
    return User.super.get(key);
  }

  @Override
  public <T>  T getOrDefault(String key, T defaultValue) {
    return User.super.getOrDefault(key, defaultValue);
  }

  @Override
  public boolean containsKey(String key) {
    return User.super.containsKey(key);
  }

  @Override
  public Authorizations authorizations() {
    return User.super.authorizations();
  }

  @Override
  public User isAuthorized(Authorization authorization, Handler<AsyncResult<Boolean>> handler) {
    return null;
  }

  @Override
  public User isAuthorized(String authority, Handler<AsyncResult<Boolean>> resultHandler) {
    // Implement authorization logic if needed
    // For example, check if the user has a specific authority/role
    // and invoke resultHandler with the appropriate boolean value
    resultHandler.handle(Future.succeededFuture(true)); // Always authorize for demonstration purposes
    return this;
  }


  @Override
  public JsonObject principal() {
    // Return a JsonObject containing user information
    return new JsonObject()
      .put("username", username);
  }

  @Override
  public void setAuthProvider(AuthProvider authProvider) {

  }

  @Override
  public User merge(User user) {
    return null;
  }

  @Override
  public boolean hasAmr(String value) {
    return User.super.hasAmr(value);
  }
}
