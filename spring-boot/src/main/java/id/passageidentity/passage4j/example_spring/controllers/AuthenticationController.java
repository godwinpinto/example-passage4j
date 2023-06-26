package id.passageidentity.passage4j.example_spring.controllers;

import id.passageidentity.passage4j.core.app.Passage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {


  @Autowired
  Passage passage;

  @PostMapping("/api/login")
  public ResponseEntity<?> authenticate(@RequestHeader(value="Authorization", required = false) String authorizationHeader,@RequestBody String requestBody) {

    try {
      Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
      //Token is already verified by filter and hence you are on this controller
      String userID=passage.auth().authenticateRequestWithAuthHeader(authorizationHeader);
      // Fetch user details if needed to set your temporary session data or create your own session
      //UserBean userBean=passage.user().getUser(userID);
      //do custom check if any
      return ResponseEntity.ok("hello");
    }catch(Exception e){
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
    }
//    return ResponseEntity.ok("hello");
  }
}
