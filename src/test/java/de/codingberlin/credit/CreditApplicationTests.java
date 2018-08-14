package de.codingberlin.credit;

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

	@LocalServerPort
	private int port;

	@Autowired TestRestTemplate restTemplate;

	@Test
	public void contextLoads() {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/ispermitted", String.class)).isEqualTo("true");
	}

}
