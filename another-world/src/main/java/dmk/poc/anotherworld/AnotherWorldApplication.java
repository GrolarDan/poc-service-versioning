package dmk.poc.anotherworld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"dmk.poc.anotherworld"})
public class AnotherWorldApplication {

  public static void main(String[] args) {
    SpringApplication.run(AnotherWorldApplication.class, args);
  }
}
