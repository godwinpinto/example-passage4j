package id.passageidentity.passage4j.example_micronaut.security;

import io.micronaut.core.annotation.Nullable;
import io.micronaut.http.HttpRequest;
import io.micronaut.security.authentication.AuthenticationProvider;
import io.micronaut.security.authentication.AuthenticationRequest;
import io.micronaut.security.authentication.AuthenticationResponse;
import jakarta.inject.Singleton;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

@Singleton
public class TokenAuthenticationProvider implements AuthenticationProvider {

  @Override
  public Publisher<AuthenticationResponse> authenticate(@Nullable HttpRequest<?> httpRequest,
      AuthenticationRequest<?, ?> authenticationRequest) {
    return Flux.create(emitter -> {
      System.out.println(authenticationRequest.getIdentity());
/*
      if (authenticationRequest.getIdentity() == "sherlock" && authenticationRequest.secret == "password") {
*/
        emitter.next(AuthenticationResponse.success((String) authenticationRequest.getIdentity()));
        emitter.complete();
/*      } else {
        emitter.error(AuthenticationResponse.exception());
      }*/
    }, FluxSink.OverflowStrategy.ERROR);
  }
}
