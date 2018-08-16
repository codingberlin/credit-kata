package de.codingberlin.credit.receive;

import de.codingberlin.credit.model.Credit;
import de.codingberlin.credit.model.approval.Approval;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.util.UUID;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ReceiveController.class)
public class ReceiveControllerTests {

	private static final UUID ANY_USER_ID = UUID.randomUUID();
	private static final UUID ANY_ORDER_ID = UUID.randomUUID();
	private static final double ANY_VALID_CREDIT = 42.0;
	private static final Approval ANY_APPROVAL = Approval.approved(Instant.parse("2018-01-01T00:00:00Z"));

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ReceiveController receiveController;

	@MockBean
	private ReceiveService receiveService;

	@Test
	public void shouldRequireUserId() throws Exception {
		String url = String.format("/ispermitted?orderId=%s&credit=%s", ANY_ORDER_ID, ANY_VALID_CREDIT);

		mockMvc.perform(get(url))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void shouldRequireOrderId() throws Exception {
		String url = String.format("/ispermitted?user=%s&credit=%s", ANY_USER_ID, ANY_VALID_CREDIT);

		mockMvc.perform(get(url))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void shouldRequireCredit() throws Exception {
		String url = String.format("/ispermitted?user=%s&orderId=%s", ANY_USER_ID, ANY_ORDER_ID);

		mockMvc.perform(get(url))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void shouldRequireNonNegativeCredit() throws Exception {
		String url = String.format("/ispermitted?user=%s&orderId=%s&credit=%s", ANY_USER_ID, ANY_ORDER_ID, -5);

		mockMvc.perform(get(url))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void shouldAllowWhenReceiveServiceAllows() throws Exception {
		String url = String.format("/ispermitted?user=%s&orderId=%s&credit=%s", ANY_USER_ID, ANY_ORDER_ID, ANY_VALID_CREDIT);

		given(receiveService.isPermitted(ANY_USER_ID, new Credit(ANY_VALID_CREDIT))).willReturn(ANY_APPROVAL);

		mockMvc.perform(get(url))
				.andExpect(status().isOk())
				.andExpect(content().json("{'state':'APPROVED','timeout':'2018-01-01T00:00:00Z'}"));
	}



}
