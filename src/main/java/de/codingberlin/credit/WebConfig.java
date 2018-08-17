package de.codingberlin.credit;

import de.codingberlin.credit.receive.CreditConverter;
import de.codingberlin.credit.receive.OrderIdConverter;
import de.codingberlin.credit.receive.UserIdConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverter(new CreditConverter());
		registry.addConverter(new UserIdConverter());
		registry.addConverter(new OrderIdConverter());
	}

}
