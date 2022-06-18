package ir.mehdialemi.supply;

import ir.mehdialemi.user.Account;
import ir.mehdialemi.user.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class BuyService {

	@Autowired
	private SupplyService supplyService;

	@Autowired
	private AccountService accountService;

	@Autowired
	private BuyRepository buyRepository;

	public void closeBuy(String username, long supplyId, BigDecimal discount) {
		Supply supply = supplyService.get(supplyId);
		Account account = accountService.getAccount(username);
		Buy buy = new Buy();
		buy.setAccount(account);
		buy.setSupply(supply);
		if (discount == null)
			discount = BigDecimal.ZERO;

		BigDecimal subtract = supply.getPrice().multiply(discount);
		BigDecimal price = supply.getPrice().subtract(subtract);
		buy.setDiscount(discount);
		buy.setPrice(price);
		buy.setStatus(BuyStatus.CLOSE_BASKET);
		buyRepository.save(buy);
	}

	public Buy findClosedBuy(String uesrname, long supplyId) {
		Optional <Buy> optional = buyRepository.findUserBuy(uesrname, supplyId, BuyStatus.CLOSE_BASKET.name());
		if (optional.isPresent())
			return optional.get();
		return null;
	}
}
