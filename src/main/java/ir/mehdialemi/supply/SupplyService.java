package ir.mehdialemi.supply;

import ir.mehdialemi.product.Product;
import ir.mehdialemi.product.ProductService;
import ir.mehdialemi.user.Account;
import ir.mehdialemi.user.Role;
import ir.mehdialemi.user.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.relation.InvalidRoleInfoException;
import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.nio.file.attribute.UserPrincipalNotFoundException;

@Service
public class SupplyService {

	@Autowired
	private ProductService productService;

	@Autowired
	private AccountService accountService;

	@Autowired
	private SupplyRepository supplyRepository;

	public Supply addProduct(String productId, String username, BigDecimal price)
			throws UserPrincipalNotFoundException, InvalidRoleInfoException {
		Product product = productService.get(productId);
		Account account = accountService.getAccount(username);

		if (account == null)
			throw new UserPrincipalNotFoundException(username);

		if (account.getRole() != Role.PROVIDER)
			throw new InvalidRoleInfoException("Invalid Role");

		Supply supply = new Supply();
		supply.setAccount(account);
		supply.setPrice(price);
		supply.setProduct(product);

		return supplyRepository.save(supply);
	}

	public Supply get(long supplyId) {
		return supplyRepository.findById(supplyId).orElseThrow(EntityNotFoundException::new);
	}

}
