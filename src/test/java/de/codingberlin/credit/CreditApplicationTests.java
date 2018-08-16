package de.codingberlin.credit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CreditApplicationTests {

	final private UUID ANY_USER = UUID.randomUUID();
	final private UUID ANY_ORDER_ID = UUID.randomUUID();

	@LocalServerPort
	private int port;

	@Autowired TestRestTemplate restTemplate;

	@Test
	public void contextLoads() {
		final String url = String.format("http://localhost:%s/ispermitted?user=%s&orderId=%s&credit=%s", port, ANY_USER, ANY_ORDER_ID, 42);
		assertThat(this.restTemplate.getForObject(url, String.class)).contains("APPROVED");
	}

}
