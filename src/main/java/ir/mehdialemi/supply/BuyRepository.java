package ir.mehdialemi.supply;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BuyRepository extends JpaRepository<Buy, Long> {

	@Query(value = "SELECT b1.* FROM account a1 JOIN buy b1 on a1.id = b1.account_id" +
			" WHERE a1.username = :username AND b1.supply_id = :id AND b1.status = :status", nativeQuery = true)
	Optional<Buy> findUserBuy(@Param("username") String username, @Param("id") long id,
							  @Param("status") String status);
}
