package de.codingberlin.credit.receive;

import de.codingberlin.credit.model.UserId;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@ConfigurationPropertiesBinding
public class UserIdConverter implements Converter<String, UserId> {

	@Override
	public UserId convert(String id) {
		return new UserId(id);
	}
}
