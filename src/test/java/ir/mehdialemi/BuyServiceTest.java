package ir.mehdialemi;

import ir.mehdialemi.product.ProductService;
import ir.mehdialemi.supply.Buy;
import ir.mehdialemi.supply.BuyService;
import ir.mehdialemi.supply.Supply;
import ir.mehdialemi.supply.SupplyService;
import ir.mehdialemi.user.AccountService;
import ir.mehdialemi.user.Role;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.management.relation.InvalidRoleInfoException;
import java.math.BigDecimal;
import java.nio.file.attribute.UserPrincipalNotFoundException;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class BuyServiceTest {

	@Autowired
	private BuyService buyService;

	@Autowired
	private ProductService productService;

	@Autowired
	private SupplyService supplyService;

	@Autowired
	private AccountService accountService;

	private static String username = "alemi";

	private Supply supply;

	@Before
	public void setup() throws UserPrincipalNotFoundException, InvalidRoleInfoException {
		accountService.create(username, username, Role.PROVIDER);
		productService.create("123", "p1");
		supply = supplyService.addProduct("123", username, BigDecimal.valueOf(100000.0));
	}

	@Test
	public void buy() {
		buyService.closeBuy(username, supply.getId(), null);
		Buy buy = buyService.findClosedBuy(username, supply.getId());
		Assert.assertEquals(supply.getId(), buy.getSupply().getId());
		Assert.assertEquals(supply.getPrice().doubleValue(), buy.getPrice().doubleValue(), 0.0000001);
	}
}
