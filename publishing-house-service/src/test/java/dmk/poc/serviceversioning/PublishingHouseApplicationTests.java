package dmk.poc.serviceversioning;

import dmk.poc.publishinghouseservice.PublishingHouseApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(
		classes = PublishingHouseApplication.class,
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PublishingHouseApplicationTests {

	@Test
	void contextLoads() {
	}

}
