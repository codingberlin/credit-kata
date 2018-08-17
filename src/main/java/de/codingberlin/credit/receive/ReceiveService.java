package de.codingberlin.credit.receive;

import de.codingberlin.credit.model.Credit;
import de.codingberlin.credit.model.CreditForOrder;
import de.codingberlin.credit.model.CreditForOrderRepository;
import de.codingberlin.credit.model.OrderId;
import de.codingberlin.credit.model.UserId;
import de.codingberlin.credit.model.approval.Approval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class ReceiveService {

	@Autowired
	public Clock clock;

	@Autowired
	private CreditForOrderRepository creditForOrderRepository;

	public Approval isPermitted(OrderId orderId, UserId userId, Credit credit) {
		if (credit.getAmount() > 250.0) {
			return Approval.DENIED;
		} else {
			Instant lifetime = clock.instant().plus(1, ChronoUnit.HOURS);
			CreditForOrder creditForOrder = CreditForOrder.buildFrom(userId, orderId, credit, lifetime);
			creditForOrderRepository.save(creditForOrder);
			return Approval.approved(lifetime);
		}
	}

}
