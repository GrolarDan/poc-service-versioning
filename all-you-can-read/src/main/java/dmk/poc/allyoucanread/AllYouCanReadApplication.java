package dmk.poc.allyoucanread;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"dmk.poc.allyoucanread"})
public class AllYouCanReadApplication  {

  public static void main(String[] args) {
    SpringApplication.run(AllYouCanReadApplication.class, args);
  }
}
