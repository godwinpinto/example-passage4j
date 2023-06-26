package id.passageidentity.passage4j.example_micronaut.security;

import id.passageidentity.passage4j.core.app.Passage;
import id.passageidentity.passage4j.core.app.PassageConfig;
import id.passageidentity.passage4j.core.app.PassageNew;
import id.passageidentity.passage4j.core.exception.PassageException;
import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Value;
import io.micronaut.security.authentication.AuthenticationProvider;
import javax.inject.Singleton;


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

  @Bean
  @Singleton
  public AuthenticationProvider myAuthProvider() {
    // Create and return your authentication provider implementation
    return new TokenAuthenticationProvider();
  }

}
