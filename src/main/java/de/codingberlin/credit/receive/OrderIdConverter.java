package de.codingberlin.credit.receive;

import de.codingberlin.credit.model.OrderId;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@ConfigurationPropertiesBinding
public class OrderIdConverter implements Converter<String, OrderId> {

	@Override
	public OrderId convert(String id) {
		return new OrderId(id);
	}
}
