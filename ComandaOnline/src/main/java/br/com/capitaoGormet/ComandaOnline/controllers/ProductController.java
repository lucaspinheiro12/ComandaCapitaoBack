package br.com.capitaoGormet.ComandaOnline.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.capitaoGormet.ComandaOnline.entities.Product;
import br.com.capitaoGormet.ComandaOnline.services.ProductService;
@CrossOrigin(value = "*")
@RestController
@RequestMapping(value = "/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping(value = "/{id}")
	public Product findById(@PathVariable int id) {
		Product result = productService.findById(id);
		return result;
	}
	
	@GetMapping
	public List<Product> findAll(){
		List<Product> result = productService.findAll();
		return result;
	}
	
	@PutMapping
	public ResponseEntity<Product> update(@PathVariable int id, @RequestBody Product product) {
		productService.update(id, product);
		return ResponseEntity.ok(product);
	}
	
	@PostMapping
	public ResponseEntity<Product> insert (@RequestBody Product product){
		productService.insert(product);
		return new ResponseEntity(product, HttpStatus.CREATED);
	}
	
	@DeleteMapping
	public ResponseEntity<Product> delete(@PathVariable int id){
		productService.delete(id);
		return ResponseEntity.ok().build();
	}
}
