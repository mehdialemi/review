package ir.mehdialemi.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Account, Long> {

	Optional<Account> findUserByUsername(String username);
}
