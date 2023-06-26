package id.passageidentity.passage4j.example_quarkus;

import id.passageidentity.passage4j.core.app.Passage;
import id.passageidentity.passage4j.core.bean.AppInfoBean;
import id.passageidentity.passage4j.core.bean.UserBean;
import id.passageidentity.passage4j.core.exception.PassageException;
import io.quarkus.security.AuthenticationFailedException;
import io.quarkus.security.identity.SecurityIdentity;
import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;

@Path("/api")
public class AuthenticationResource {

  @Inject
  SecurityContext securityContext;

  @Inject
  Passage passage;

  @POST
  @Path("/login")
  @Produces(MediaType.APPLICATION_JSON)
  @PermitAll
  public Response login() {
    // Handle login logic here to set values in redis context
    try {
      String userID=securityContext.getUserPrincipal().getName();
      AppInfoBean appInfoBean=passage.app().getInfo();
      //do whatever thing you want here of fetch passage data
      return Response.status(Response.Status.OK).entity(appInfoBean.getName()).build();
    } catch (PassageException e) {
      return Response.status(Response.Status.UNAUTHORIZED).entity(new AuthenticationFailedException("Invalid token")).build();
    }

  }
}
