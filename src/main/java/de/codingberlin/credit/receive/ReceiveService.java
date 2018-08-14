package de.codingberlin.credit.receive;

import de.codingberlin.credit.model.Credit;
import org.springframework.stereotype.Service;

@Service
public class ReceiveService {

	public boolean isPermitted(Credit credit) {
		if (credit.getAmount() > 250.0) {
			return false;
		} else {
			return true;
		}
	}

}
