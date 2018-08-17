package de.codingberlin.credit.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CreditForOrderRepository extends CrudRepository<CreditForOrder, String> {

	@Query(value = "SELECT sum(amount) FROM CREDIT_FOR_ORDER WHERE USER_ID = ?1 AND (STATUS = 'USED' OR LIFETIME > now())", nativeQuery = true)
	public Optional<Double> creditOfUser(String userId);
}
