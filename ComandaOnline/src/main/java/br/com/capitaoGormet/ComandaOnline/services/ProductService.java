package br.com.capitaoGormet.ComandaOnline.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.capitaoGormet.ComandaOnline.entities.Product;
import br.com.capitaoGormet.ComandaOnline.repositories.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	public List<Product> findAll() {
		List<Product> allProducts = productRepository.findAll();
		return allProducts;
	}
	
	public Product findById(Integer id) {
		Optional<Product> productId = productRepository.findById(id);
		return productId.orElseThrow();
	}
	
	public void insert (Product product) {
		productRepository.save(product);
	}
	
	public void update (Integer id, Product product) {
		Product productId = findById(id);
		productRepository.save(product);
	}
	
	public void delete (Integer id) {
		Product productDelete = findById(id);
		productRepository.delete(productDelete);
	}
}
