package id.passageidentity.passage4j.example_spring.config;

import java.util.Collections;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

@Configuration
public class CustomAuthenticationManager implements AuthenticationManager {

  /**
   * private AuthServiceAPI authServiceAPI;
   *
   * <p>public AuthenticationManager(AuthServiceAPI authServiceAPI) { this.authServiceAPI =
   * authServiceAPI; }
   */
  @Override
  public Authentication authenticate(Authentication authentication) {
    Object principal = authentication.getPrincipal();
    return
        new UsernamePasswordAuthenticationToken(
            principal, authentication.getCredentials(), Collections.emptyList());
  }
}
