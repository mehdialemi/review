package ir.mehdialemi.supply;

import ir.mehdialemi.user.Account;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "buy")
public class Buy {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "supply_id")
	private Supply supply;

	private BigDecimal discount;

	@Column(nullable = false)
	private BigDecimal price;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "account_id")
	private Account account;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate = new Date();

	@Enumerated(EnumType.STRING)
	private BuyStatus status = BuyStatus.EMPTY_BASKET;
}
