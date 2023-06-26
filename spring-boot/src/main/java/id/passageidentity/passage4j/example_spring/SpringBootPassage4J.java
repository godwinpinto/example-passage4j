package id.passageidentity.passage4j.example_spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "id.passageidentity.passage4j")
public class SpringBootPassage4J {

  public static void main(String[] args) {
    SpringApplication.run(SpringBootPassage4J.class, args);
  }

}
