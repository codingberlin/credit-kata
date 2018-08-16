package de.codingberlin.credit.receive;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.codingberlin.credit.model.Credit;
import de.codingberlin.credit.model.approval.Approval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.core.convert.ConversionFailedException;
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
import java.io.IOException;
import java.io.StringWriter;
import java.util.Optional;
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
			@RequestParam(value = "credit", required = true) Credit credit,
			@RequestParam(value = "orderId", required = true) UUID orderId) {
		return receiveService.isPermitted(user, credit);
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
