package id.passageidentity.passage4j.example_micronaut.controller;

import id.passageidentity.passage4j.core.app.Passage;
import id.passageidentity.passage4j.core.bean.AppInfoBean;
import id.passageidentity.passage4j.core.exception.PassageException;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.authentication.AuthenticationFailed;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.validation.Validated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

@Controller("/api")
@Validated
public class LoginController {

  private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

  @Inject
  private Passage passage;


  @Secured(SecurityRule.IS_ANONYMOUS)
  @Post("/login")
  public HttpResponse<?> login(HttpRequest<?> httpRequest, Authentication authentication) {
    try {
      String userID=authentication.getName();
      AppInfoBean appInfoBean=passage.app().getInfo();
      //do whatever thing you want here of fetch passage data
      return HttpResponse.status(HttpStatus.OK).body("OK");
    } catch (PassageException e) {
      logger.error("Login failed: {}", e.getMessage());
      return HttpResponse.status(HttpStatus.UNAUTHORIZED).body(new AuthenticationFailed("Invalid token"));
    }
  }
}
