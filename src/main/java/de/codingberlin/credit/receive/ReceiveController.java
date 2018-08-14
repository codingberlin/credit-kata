package de.codingberlin.credit.receive;

import de.codingberlin.credit.model.Credit;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.UUID;

@RestController
@EnableAutoConfiguration
@Validated
public class ReceiveController {

	@RequestMapping(value = "/ispermitted", method = RequestMethod.GET)
	public @ResponseBody String isPermitted(
			@RequestParam(value = "user", required = true) UUID user,
			@Min(0) @RequestParam(value = "credit", required = true) double credit,
			@RequestParam(value = "orderId", required = true) UUID orderId) {
		//TODO add validation tests
		return "true";
	}

}
