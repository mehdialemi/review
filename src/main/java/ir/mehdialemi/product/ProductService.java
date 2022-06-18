package ir.mehdialemi.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public Product create(String productId, String name) {
		Product product = new Product();
		product.setProductId(productId);
		product.setName(name);
		return productRepository.save(product);
	}

	public Product get(String productId) {
		return productRepository.findProductByProductId(productId).orElseThrow(EntityNotFoundException::new);
	}

}
