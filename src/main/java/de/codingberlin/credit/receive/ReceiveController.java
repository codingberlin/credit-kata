package de.codingberlin.credit.receive;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.codingberlin.credit.model.Credit;
import de.codingberlin.credit.model.OrderId;
import de.codingberlin.credit.model.UserId;
import de.codingberlin.credit.model.approval.Approval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Optional;

@RestController
@ControllerAdvice
@EnableAutoConfiguration
@Validated
public class ReceiveController {

	@Autowired
	public ReceiveService receiveService;

	@RequestMapping(value = "/ispermitted", method = RequestMethod.GET)
	public @ResponseBody Approval isPermitted(
			@RequestParam(value = "userId", required = true) UserId userId,
			@RequestParam(value = "credit", required = true) Credit credit,
			@RequestParam(value = "orderId", required = true) OrderId orderId) {
		return receiveService.isPermitted(userId, credit);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ConversionFailedException.class)
	public String handleError(ConversionFailedException ex) {
		ObjectMapper mapper = new ObjectMapper();
		StringWriter writer = new StringWriter();
		try {
			String message = Optional.ofNullable(ex.getCause())
					.map(Throwable::getMessage)
					.orElse(ex.getMessage());
			mapper.writeValue(writer, message);
			return "{\"error\":" + writer.toString() + "}";
		} catch (IOException e) {
			return "{'error':'could not parse parameter'}";
		}

	}
}
