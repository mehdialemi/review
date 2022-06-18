package ir.mehdialemi.supply;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ir.mehdialemi.product.Product;
import ir.mehdialemi.review.ReviewLock;
import ir.mehdialemi.review.Comment;
import ir.mehdialemi.review.Rate;
import ir.mehdialemi.user.Account;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "supply")
public class Supply {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "account_id")
	private Account account;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "product_id")
	private Product product;

	private BigDecimal price;

	@OneToMany(mappedBy = "supply", fetch = FetchType.LAZY)
	private List<Comment> comments = new ArrayList <>();

	@OneToMany(mappedBy = "supply", fetch = FetchType.LAZY)
	private List<Rate> rates = new ArrayList <>();

	private ReviewLock reviewLock;

	@OneToMany(mappedBy = "supply", fetch = FetchType.LAZY)
	private List<Buy> buyList = new ArrayList <>();
}
