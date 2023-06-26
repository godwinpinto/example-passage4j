package id.passageidentity.passage4j.example_spring.controllers;

import id.passageidentity.passage4j.core.app.Passage;
import id.passageidentity.passage4j.core.bean.UserBean;
import id.passageidentity.passage4j.core.exception.PassageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DashboardController {

  @Autowired
  Passage passage;

  @GetMapping("/api/dashboard")
  public ResponseEntity<?> dashboard(
      @RequestHeader(value = "Authorization", required = false) String authorizationHeader) {
    try {
      Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
      UserBean userBean = passage.user().getInfo((String)authentication.getPrincipal());
      return ResponseEntity.ok(userBean);
    } catch (PassageException e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }


  }


}
