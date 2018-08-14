package de.codingberlin.credit.receive;

import de.codingberlin.credit.model.Credit;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ReceiveService {

	public boolean isPermitted(UUID user, Credit credit) {
		if (credit.getAmount() > 250.0) {
			return false;
		} else {
			return true;
		}
	}

}
