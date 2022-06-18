package ir.mehdialemi.review;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RateRepository extends JpaRepository<Rate, Long> {

	@Query(value = "SELECT avg(r1.rate) FROM supply s1 RIGHT OUTER JOIN rate r1 on s1.id = r1.supply_id WHERE s1.id = :id",
			nativeQuery = true)
	Double avgRate(@Param("id") long id);

	@Query(value = "SELECT count(*) FROM supply s1 RIGHT OUTER JOIN rate r1 on s1.id = r1.supply_id WHERE s1.id = :id",
			nativeQuery = true)
	Integer countRates(@Param("id") long id);


}
