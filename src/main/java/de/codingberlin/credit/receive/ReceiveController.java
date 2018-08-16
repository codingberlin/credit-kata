package de.codingberlin.credit.receive;

import de.codingberlin.credit.model.Credit;
import de.codingberlin.credit.model.approval.Approval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.Min;
import java.util.UUID;

@RestController
@ControllerAdvice
@EnableAutoConfiguration
@Validated
public class ReceiveController {

	@Autowired
	public ReceiveService receiveService;

	@RequestMapping(value = "/ispermitted", method = RequestMethod.GET)
	public @ResponseBody Approval isPermitted(
			@RequestParam(value = "user", required = true) UUID user,
			@Min(0) @RequestParam(value = "credit", required = true) double credit,
			@RequestParam(value = "orderId", required = true) UUID orderId) {
		return receiveService.isPermitted(user, new Credit(credit));
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseBody String handleConstraintViolationException(ConstraintViolationException e) {
		return e.getMessage();
	}

}
