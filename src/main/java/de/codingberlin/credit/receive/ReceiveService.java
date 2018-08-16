package de.codingberlin.credit.receive;

import de.codingberlin.credit.model.Credit;
import de.codingberlin.credit.model.approval.Approval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
public class ReceiveService {

	@Autowired
	public Clock clock;

	public Approval isPermitted(UUID user, Credit credit) {
		if (credit.getAmount() > 250.0) {
			return Approval.DENIED;
		} else {
			return Approval.approved(clock.instant().plus(1, ChronoUnit.HOURS));
		}
	}

}
