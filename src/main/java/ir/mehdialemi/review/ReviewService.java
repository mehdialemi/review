package ir.mehdialemi.review;

import ir.mehdialemi.supply.BuyService;
import ir.mehdialemi.supply.Supply;
import ir.mehdialemi.supply.SupplyRepository;
import ir.mehdialemi.user.Account;
import ir.mehdialemi.user.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Set;

@Service
public class ReviewService {

	@Value("${admin.review.lock}")
	private ReviewLock globalReviewLock;

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private RateRepository rateRepository;

	@Autowired
	private BuyService buyService;

	@Autowired
	private AccountService accountService;

	@Autowired
	private SupplyRepository supplyRepository;

	public void addComment(String username, long supplyId, String text) {
		Supply supply = getSupply(supplyId);

		if (!validate(username, supply))
			throw new RuntimeException("Invalid request");

		Account account = accountService.getAccount(username);
		Comment comment = new Comment();
		comment.setSupply(supply);
		comment.setAccount(account);
		comment.setText(text);
		commentRepository.save(comment);
	}

	public void addRate(String username, long supplyId, int value) {
		Supply supply = getSupply(supplyId);


		if (!validate(username, supply))
			throw new RuntimeException("Invalid request");

		Account account = accountService.getAccount(username);
		Rate rate = new Rate();
		rate.setAccount(account);
		rate.setSupply(supply);
		rate.setRate(value);
		rateRepository.save(rate);
	}

	private boolean validate(String username, Supply supply) {
		ReviewLock reviewLock = supply.getReviewLock();
		if (reviewLock == null)
			reviewLock = globalReviewLock;

		switch (reviewLock) {
			case ALL: return true;
			case USER: return accountService.getAccount(username) != null;
			case CUSTOMER: return buyService.findClosedBuy(username, supply.getId()) != null;
			default: return false;
		}
	}

	public double avgRate(long supplyId) {
		return rateRepository.avgRate(supplyId);
	}

	public long rateCount(long supplyId) {
		return rateRepository.countRates(supplyId);
	}

	public long commentCount(long supplyId) {
		return commentRepository.countComments(supplyId);
	}

	public Set<Comment> getLastComments(long supplyId, int n) {
		return commentRepository.getLastComments(supplyId, n);
	}

	private Supply getSupply(long id) {
		return supplyRepository.findById(id).orElseThrow(EntityNotFoundException::new);
	}
}
