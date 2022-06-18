package ir.mehdialemi.review;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ir.mehdialemi.supply.Supply;
import ir.mehdialemi.user.Account;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "rate")
public class Rate {


	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "account_id")
	private Account account;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "supply_id")
	private Supply supply;

	private int rate;

}
