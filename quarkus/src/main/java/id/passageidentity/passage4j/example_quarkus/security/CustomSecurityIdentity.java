package id.passageidentity.passage4j.example_quarkus.security;

import jakarta.ws.rs.core.SecurityContext;
import java.security.Principal;


public class CustomSecurityIdentity implements SecurityContext {

  private final Principal principal;

  public CustomSecurityIdentity(String userId) {
    this.principal = new CustomPrincipal(userId);
  }

  @Override
  public Principal getUserPrincipal() {
    return principal;
  }

  @Override
  public boolean isUserInRole(String s) {
    return false;
  }

  @Override
  public boolean isSecure() {
    return false;
  }

  @Override
  public String getAuthenticationScheme() {
    return null;
  }
}
