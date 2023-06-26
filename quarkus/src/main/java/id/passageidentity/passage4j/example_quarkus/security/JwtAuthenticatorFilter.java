package id.passageidentity.passage4j.example_quarkus.security;

import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import jakarta.ws.rs.ext.Provider;
import id.passageidentity.passage4j.core.app.Passage;
import id.passageidentity.passage4j.core.exception.PassageException;



@Provider
@Priority(Priorities.AUTHENTICATION)
public class JwtAuthenticatorFilter implements ContainerRequestFilter {

  @Inject
  Passage passage;

  private static final String AUTHORIZATION_HEADER_PREFIX = "Bearer ";

  @Override
  public void filter(ContainerRequestContext requestContext) {
    // Extract the Authorization header
    String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

    try {
      String userID = passage.auth().authenticateRequestWithAuthHeader(authorizationHeader);
      SecurityContext sc = new CustomSecurityIdentity(userID);
      requestContext.setSecurityContext(sc);
    } catch (PassageException passageError) {
      requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
    }
  }


  private boolean isValidToken(String token) {
    // Implement your custom token validation logic here
    // Return true if the token is valid, false otherwise
    // You can check against a database, validate its format, etc.
    // For simplicity, this example considers the token valid if it equals "secret_token"

    return "secret_token".equals(token);
  }
}