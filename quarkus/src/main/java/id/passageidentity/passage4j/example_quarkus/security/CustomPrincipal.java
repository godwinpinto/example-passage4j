package id.passageidentity.passage4j.example_quarkus.security;

import java.security.Principal;

public class CustomPrincipal implements Principal {

  private final String userId;

  public CustomPrincipal(String userId) {
    this.userId = userId;
  }

  @Override
  public String getName() {
    return userId;
  }

  public String getUserId() {
    return userId;
  }
}