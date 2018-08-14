package de.codingberlin.credit.receive;

import de.codingberlin.credit.model.Credit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReceiveServiceTests {

	final private UUID ANY_USER = UUID.randomUUID();

	@Autowired
	private ReceiveService receiveService;

	@Test
	public void shouldReturnTrueFor250EUR() {
		assertThat(receiveService.isPermitted(ANY_USER, new Credit(250.0))).isTrue();
	}

	@Test
	public void shouldReturnFalseForMoreThan250EUR() {
		assertThat(receiveService.isPermitted(ANY_USER, new Credit(250.01))).isFalse();
	}

}
