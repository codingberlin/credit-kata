package de.codingberlin.credit.model;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

public class CreditTests {

	@Test
	public void shouldBeAbleToInstanciatePositiveCredit() {
		assertThat(new Credit(1701).getAmount()).isEqualTo(1701);
	}

	@Test
	public void shouldThrowExceptionForNegativeCredit() {
		try {
			new Credit(-0.01);
			fail("No IllegalArgumentException was thrown");
		} catch (Throwable t) {
			assertThat(t instanceof IllegalArgumentException).isTrue();
		}

	}


}
