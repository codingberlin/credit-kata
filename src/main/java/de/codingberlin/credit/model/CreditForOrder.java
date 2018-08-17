package de.codingberlin.credit.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.Instant;
import java.util.Objects;

@Entity
public class CreditForOrder {

	@Id
	private String orderId;
	private String userId;
	private double amount;
	private Instant lifetime;
	private CreditForOrderStatus status;

	private CreditForOrder() {

	}

	private CreditForOrder(String orderId, String userId, double amount, Instant lifetime, CreditForOrderStatus status) {
		this.orderId = orderId;
		this.userId = userId;
		this.amount = amount;
		this.lifetime = lifetime;
		this.status = status;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Instant getLifetime() {
		return lifetime;
	}

	public void setLifetime(Instant lifetime) {
		this.lifetime = lifetime;
	}

	public CreditForOrderStatus getStatus() {
		return status;
	}

	public void setStatus(CreditForOrderStatus status) {
		this.status = status;
	}

	@Override public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		CreditForOrder that = (CreditForOrder) o;
		return Double.compare(that.amount, amount) == 0 &&
				Objects.equals(orderId, that.orderId) &&
				Objects.equals(userId, that.userId) &&
				Objects.equals(lifetime, that.lifetime) &&
				status == that.status;
	}

	@Override public int hashCode() {
		return Objects.hash(orderId, userId, amount, lifetime, status);
	}

	public static CreditForOrder buildFrom(UserId userId, OrderId orderId, Credit credit, Instant lifetime) {
		return new CreditForOrder(orderId.getId(), userId.getId(), credit.getAmount(), lifetime, CreditForOrderStatus.UNUSED);
	}
}
