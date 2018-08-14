package de.codingberlin.credit.receive;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class ReceiveController {

	@RequestMapping("/ispermitted")
	String isPermitted() {
		return "true";
	}

}
