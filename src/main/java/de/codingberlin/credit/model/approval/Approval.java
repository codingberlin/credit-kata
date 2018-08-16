package de.codingberlin.credit.model.approval;

import java.time.Instant;
import java.util.Objects;
import java.util.Optional;

public class Approval {
	public static Approval DENIED = new Approval(ApprovalState.DENIED, Optional.empty());

	private final ApprovalState state;
	private final Optional<Instant> timeout;

	private Approval(ApprovalState state, Optional<Instant> timeout) {
		this.state = state;
		this.timeout = timeout;
	}

	public ApprovalState getState() {
		return state;
	}

	public Optional<Instant> getTimeout() {
		return timeout;
	}

	public static Approval approved(Instant timeout) {
		return new Approval(ApprovalState.APPROVED, Optional.of(timeout));
	}

	@Override
	public String toString() {
		return "ApprovalState(" + this.state + this.timeout.map(t -> ", " + t.toString()).orElse("") + ")";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Approval approval = (Approval) o;
		return state == approval.state &&
				Objects.equals(timeout, approval.timeout);
	}

	@Override
	public int hashCode() {
		return Objects.hash(state, timeout);
	}
}
