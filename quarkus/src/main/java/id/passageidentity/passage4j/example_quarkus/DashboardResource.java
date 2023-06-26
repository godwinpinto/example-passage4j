package id.passageidentity.passage4j.example_quarkus;

import id.passageidentity.passage4j.core.bean.UserBean;
import id.passageidentity.passage4j.core.exception.PassageException;
import io.quarkus.security.Authenticated;
import io.quarkus.security.AuthenticationFailedException;
import io.quarkus.security.identity.SecurityIdentity;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import id.passageidentity.passage4j.core.app.Passage;


@Path("/api")
public class DashboardResource {

  @Inject
  Passage passage;

  @Inject
  SecurityContext securityContext;

  @GET
  @Path("/dashboard")
  @Authenticated
  @Produces(MediaType.APPLICATION_JSON)
  public Response dashboard() {
    try {
      String userID=securityContext.getUserPrincipal().getName();
      UserBean userBean=passage.user().getInfo(userID);
      //do whatever thing you want here of fetch passage data
      return Response.status(Response.Status.OK).entity(userBean).build();
    } catch (PassageException e) {
      return Response.status(Response.Status.UNAUTHORIZED).entity(new AuthenticationFailedException("Invalid token")).build();
    }
  }
}
