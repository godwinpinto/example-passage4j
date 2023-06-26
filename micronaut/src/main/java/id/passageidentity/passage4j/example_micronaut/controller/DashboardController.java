package id.passageidentity.passage4j.example_micronaut.controller;

import id.passageidentity.passage4j.core.app.Passage;
import id.passageidentity.passage4j.core.bean.UserBean;
import id.passageidentity.passage4j.core.exception.PassageException;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.authentication.AuthenticationFailed;
import io.micronaut.security.rules.SecurityRule;
import javax.inject.Inject;

@Controller("/api")
public class DashboardController {

  @Inject
  Passage passage;

  @Secured(SecurityRule.IS_AUTHENTICATED)
  @Get(value = "/dashboard",produces = MediaType.APPLICATION_JSON)
  public HttpResponse<?> dashboard(HttpRequest<?> httpRequest, Authentication authentication) {
    try {
      String userID=authentication.getName();
      UserBean userBean=passage.user().getInfo(userID);
      //do whatever thing you want here of fetch passage data
      return HttpResponse.status(HttpStatus.OK).body(userBean);
    } catch (PassageException e) {
      return HttpResponse.status(HttpStatus.UNAUTHORIZED).body(new AuthenticationFailed("Invalid token"));
    }
  }
}
