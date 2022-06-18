package ir.mehdialemi;

import ir.mehdialemi.product.ProductService;
import ir.mehdialemi.review.Comment;
import ir.mehdialemi.review.ReviewService;
import ir.mehdialemi.supply.Supply;
import ir.mehdialemi.supply.SupplyService;
import ir.mehdialemi.user.Role;
import ir.mehdialemi.user.AccountService;
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
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ReviewServiceTest {

	@Autowired
	private ReviewService reviewService;

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
		supply = supplyService.addProduct("123", username, BigDecimal.valueOf(100000));
	}

	@Test
	public void testComment() {
		String comment = "this is comment";
		reviewService.addComment(username, supply.getId(), comment);
		Set <Comment> lastComments = reviewService.getLastComments(supply.getId(), 1);
		Assert.assertEquals(1, lastComments.size());
		Comment result = lastComments.stream().findFirst().get();
		Assert.assertEquals(comment, result.getText());
		Assert.assertEquals(1, reviewService.commentCount(supply.getId()));
	}

	@Test
	public void testRate() {
		reviewService.addRate(username, supply.getId(), 7);
		long count = reviewService.rateCount(supply.getId());
		Assert.assertEquals(1, count);
		double avg = reviewService.avgRate(supply.getId());
		Assert.assertEquals(7, avg, 0.000001);

		reviewService.addRate(username, supply.getId(), 3);
		count = reviewService.rateCount(supply.getId());
		Assert.assertEquals(2, count);
		avg = reviewService.avgRate(supply.getId());
		Assert.assertEquals(5, avg, 0.000001);
	}
}
