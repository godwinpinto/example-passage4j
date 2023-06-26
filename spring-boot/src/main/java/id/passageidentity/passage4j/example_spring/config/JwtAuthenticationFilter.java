package id.passageidentity.passage4j.example_spring.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import id.passageidentity.passage4j.core.app.Passage;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

  private final ObjectMapper objectMapper;
  private final Passage passage;

  public JwtAuthenticationFilter(AuthenticationManager authenticationManager,Passage passage,
      ObjectMapper objectMapper) {
    super(authenticationManager);
    this.objectMapper = objectMapper;
    this.passage=passage;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    String header = request.getHeader("Authorization");

    if (StringUtils.hasText(header) && header.startsWith("Bearer ")) {
      try {
        String userID=passage.auth().authenticateRequestWithAuthHeader(header);


        if (StringUtils.hasText(userID)) {
          String token = header.substring(7);
          UsernamePasswordAuthenticationToken authenticationToken =
              new UsernamePasswordAuthenticationToken(userID, token, Collections.emptyList());
          SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
      } catch (Exception ex) {
        // Handle authentication failure
        handleAuthenticationFailure(request, response, ex);
        return;
      }
    }

    chain.doFilter(request, response);
  }
  private void handleAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
      Exception exception) throws IOException {
    Map<String, String> errorResponse = new HashMap<>();
    errorResponse.put("error", "Authentication failed");
    errorResponse.put("message", exception.getMessage());

    response.setStatus(HttpStatus.UNAUTHORIZED.value());
    response.setContentType("application/json");
    response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    response.getWriter().flush();
  }
}
