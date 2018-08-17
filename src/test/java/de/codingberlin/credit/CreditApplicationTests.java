package de.codingberlin.credit;

import de.codingberlin.credit.model.OrderId;
import de.codingberlin.credit.model.UserId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CreditApplicationTests {

	final private UserId ANY_USER_ID = new UserId("any user id");
	final private OrderId ANY_ORDER_ID = new OrderId("any order id");

	@LocalServerPort
	private int port;

	@Autowired TestRestTemplate restTemplate;

	@Test
	public void contextLoads() {
		final String url =
				String.format("http://localhost:%s/ispermitted?userId=%s&orderId=%s&credit=%s", port, ANY_USER_ID.getId(), ANY_ORDER_ID.getId(), 42);
		assertThat(this.restTemplate.getForObject(url, String.class)).contains("APPROVED");
	}

}
