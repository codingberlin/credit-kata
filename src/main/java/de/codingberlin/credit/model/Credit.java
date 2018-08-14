package de.codingberlin.credit.model;

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
}
