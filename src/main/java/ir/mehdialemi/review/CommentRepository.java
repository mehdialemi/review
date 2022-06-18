package ir.mehdialemi.review;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface CommentRepository extends JpaRepository <Comment, Long> {

	@Query(value = "SELECT c1.* FROM supply s1 RIGHT OUTER JOIN comment c1 on s1.id = c1.supply_id " +
			"WHERE s1.id = :id ORDER BY c1.id DESC LIMIT :limit", nativeQuery = true)
	Set <Comment> getLastComments(@Param("id") long id, @Param("limit") int limit);

	@Query(value = "SELECT count(*) FROM supply s1 RIGHT OUTER JOIN comment c1 on s1.id = c1.supply_id WHERE s1.id = :id",
			nativeQuery = true)
	Integer countComments(@Param("id") long id);
}
