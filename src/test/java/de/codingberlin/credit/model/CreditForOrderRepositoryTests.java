package de.codingberlin.credit.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CreditForOrderRepositoryTests {

	final private UserId ALICE = new UserId("Alice");
	final private UserId BOB = new UserId("Bob");
	final private UserId CHARLY = new UserId("Charly");

	@Autowired
	public CreditForOrderRepository creditForOrderRepository;

	@Test
	public void shouldReturnEmptyWhenNoCreditIsGiven() {
		assertThat(creditForOrderRepository.creditOfUser(CHARLY.getId())).isEmpty();
	}

	@Test
	public void shouldReturnSumOfUsedAndLivingCreditsOfSpecificUser() {
		creditForOrderRepository.save(CreditForOrder.buildFrom(ALICE, new OrderId("1"), new Credit(1.0), Instant.now().plus(1, ChronoUnit.HOURS)));
		creditForOrderRepository.save(CreditForOrder.buildFrom(ALICE, new OrderId("2"), new Credit(10.0), Instant.now().plus(1, ChronoUnit.HOURS)));
		creditForOrderRepository.save(CreditForOrder.buildFrom(BOB, new OrderId("3"), new Credit(100.0), Instant.now().plus(1, ChronoUnit.HOURS)));
		creditForOrderRepository
				.save(CreditForOrder.buildFrom(ALICE, new OrderId("4"), new Credit(1000.0), Instant.now().minus(1, ChronoUnit.HOURS)));

		assertThat(creditForOrderRepository.creditOfUser(ALICE.getId())).contains(11.0);
	}

}
