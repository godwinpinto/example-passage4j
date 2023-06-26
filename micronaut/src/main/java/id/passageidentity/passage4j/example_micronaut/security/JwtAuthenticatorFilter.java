package id.passageidentity.passage4j.example_micronaut.security;

import id.passageidentity.passage4j.core.app.Passage;
import id.passageidentity.passage4j.core.exception.PassageException;
import io.micronaut.http.HttpRequest;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.authentication.AuthenticationException;
import io.micronaut.security.authentication.AuthenticationFailed;
import io.micronaut.security.authentication.ServerAuthentication;
import io.micronaut.security.filters.AuthenticationFetcher;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import org.reactivestreams.Publisher;

import javax.inject.Inject;
import javax.inject.Singleton;
import reactor.core.publisher.Mono;

@Singleton

public class JwtAuthenticatorFilter implements AuthenticationFetcher {

  private final Passage passage;

  @Inject
  public JwtAuthenticatorFilter(Passage passage) {
    this.passage=passage;
  }

  @Override
  public Publisher<Authentication> fetchAuthentication(HttpRequest<?> request) {

    return Mono.create(emitter -> {
      Optional<String> authorizationHeader = request.getHeaders().getAuthorization();
      if (authorizationHeader.isPresent()) {
        String authToken=authorizationHeader.get();
        try {
          String userID=passage.auth().authenticateRequestWithAuthHeader(authToken);
          Authentication authentication=new ServerAuthentication(userID, Collections.emptyList(),Collections.emptyMap());
          emitter.success(authentication);
        } catch (PassageException e) {
          emitter.error(e);
        }
      }else{
        emitter.error( new AuthenticationException("Authorization header not found"));
      }
    });
  }
}