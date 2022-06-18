package ir.mehdialemi.user;

import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

	@Autowired
	private UserRepository repository;

	public Account getAccount(String username) {
		if (Strings.isNullOrEmpty(username))
			return null;
		return repository.findUserByUsername(username).get();
	}

	public void create(String username, String password, Role role) {
		Account account = new Account();
		account.setUsername(username);
		account.setRole(role);
		account.setPassword(password);
		repository.save(account);
	}
}
