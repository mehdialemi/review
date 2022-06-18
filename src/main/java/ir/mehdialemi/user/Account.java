package ir.mehdialemi.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ir.mehdialemi.review.Comment;
import ir.mehdialemi.supply.Buy;
import ir.mehdialemi.supply.Supply;
import ir.mehdialemi.review.Rate;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "account")
public class Account {

	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Role role = Role.USER;

	@Column(unique = true, nullable = false)
	private String username;

	@Column(nullable = false)
	private String password;

	@JsonIgnore
	@OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
	private List<Supply> supplyList = new ArrayList <>();

	@JsonIgnore
	@OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
	private List<Rate> rateList = new ArrayList <>();

	@JsonIgnore
	@OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
	private List<Comment> commentList = new ArrayList <>();

	@JsonIgnore
	@OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
	private List<Buy> buyList = new ArrayList <>();
}
