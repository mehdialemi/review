package ir.mehdialemi.product;

import ir.mehdialemi.supply.Supply;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(unique = true, nullable = false)
	private String productId;

	@Column(nullable = false)
	private String name;

	private String category;

	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
	private List<Supply> supply = new ArrayList <>();
}
