package de.codingberlin.credit.model;

import java.util.Objects;

public class Credit {
	final private double amount;

	public Credit(double amount) {
		if (amount < 0.0) {
			throw new IllegalArgumentException("Credit must be positive.");
		}

		this.amount = amount;
	}

	public double getAmount() {
		return amount;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Credit credit = (Credit) o;
		return Double.compare(credit.amount, amount) == 0;
	}

	@Override public int hashCode() {
		return Objects.hash(amount);
	}
}
