package de.codingberlin.credit.receive;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class ReceiveController {

	@RequestMapping("/ispermitted")
	public @ResponseBody String isPermitted() {
		return "true";
	}

}
