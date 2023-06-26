package id.passageidentity.passage4j.example_quarkus.security;

import id.passageidentity.passage4j.core.app.Passage;
import id.passageidentity.passage4j.core.app.PassageConfig;
import id.passageidentity.passage4j.core.app.PassageNew;
import id.passageidentity.passage4j.core.exception.PassageException;
import io.quarkus.arc.DefaultBean;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Dependent;
import jakarta.ws.rs.Produces;
import org.eclipse.microprofile.config.inject.ConfigProperty;

public class PassageProducer {

  @ApplicationScoped
  public Passage getPassage(@ConfigProperty(name = "passage.app-id") String passageAppID,
      @ConfigProperty(name = "passage.api-key") String passageApiKey) throws PassageException {
    PassageConfig passageConfig = new PassageConfig();
    passageConfig.setApiKey(passageApiKey);
    passageConfig.setHeaderAuth(true);
    return new PassageNew(passageAppID, passageConfig);

  }

}
