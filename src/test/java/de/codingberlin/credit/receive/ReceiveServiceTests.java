package de.codingberlin.credit.receive;

import de.codingberlin.credit.model.Credit;
import de.codingberlin.credit.model.CreditForOrder;
import de.codingberlin.credit.model.CreditForOrderRepository;
import de.codingberlin.credit.model.OrderId;
import de.codingberlin.credit.model.UserId;
import de.codingberlin.credit.model.approval.Approval;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReceiveServiceTests {

	final private UserId ANY_USER_ID = new UserId("any user id");
	final private OrderId ANY_ORDER_ID = new OrderId("any order id");

	@TestConfiguration
	static class FixedClockConfiguration {

		@Bean
		public Clock clock() {
			return Clock.fixed(Instant.parse("2018-01-01T00:00:00Z"), ZoneId.of("UTC"));
		}
	}

	@Autowired
	public Clock clock;

	@MockBean
	public CreditForOrderRepository creditForOrderRepository;

	@Autowired
	public ReceiveService receiveService;

	@Test
	public void shouldApproveForOneHourWhenAmountIs250EUR() {
		final Approval approval = receiveService.isPermitted(ANY_ORDER_ID, ANY_USER_ID, new Credit(250.0));
		assertThat(approval).isEqualTo(Approval.approved(Instant.parse("2018-01-01T01:00:00Z")));
		verify(creditForOrderRepository, times(1))
				.save(CreditForOrder.buildFrom(ANY_USER_ID, ANY_ORDER_ID, new Credit(250.0), Instant.parse("2018-01-01T01:00:00Z")));
	}

	@Test
	public void shouldReturnFalseForMoreThan250EUR() {
		final Approval approval = receiveService.isPermitted(ANY_ORDER_ID, ANY_USER_ID, new Credit(250.01));
		assertThat(approval).isEqualTo(Approval.DENIED);
		verify(creditForOrderRepository, times(0))
				.save(any());
	}

}
