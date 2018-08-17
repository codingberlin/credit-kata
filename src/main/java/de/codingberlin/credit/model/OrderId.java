package de.codingberlin.credit.model;

import java.util.Objects;

public class OrderId {
	private final String id;

	public OrderId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	@Override public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		OrderId userId = (OrderId) o;
		return Objects.equals(id, userId.id);
	}

	@Override public int hashCode() {
		return Objects.hash(id);
	}

	@Override public String toString() {
		return "OrderId(" + id +	')';
	}
}
