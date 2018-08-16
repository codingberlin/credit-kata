package de.codingberlin.credit.receive;

import de.codingberlin.credit.model.Credit;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@ConfigurationPropertiesBinding
public class CreditConverter implements Converter<String, Credit> {

	@Override
	public Credit convert(String amount) {
		return new Credit(Double.parseDouble(amount));
	}
}
