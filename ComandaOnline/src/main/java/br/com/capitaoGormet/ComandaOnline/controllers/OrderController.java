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
import br.com.capitaoGormet.ComandaOnline.entities.Order;
import br.com.capitaoGormet.ComandaOnline.services.OrderService;
@CrossOrigin(value = "*")
@RestController
@RequestMapping(value = "/order")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	
	@GetMapping(value = "/{id}")
	public Order findById(@PathVariable Integer id) {
		Order result = orderService.findById(id);
		return result;
	}
	
	@GetMapping
	public List<Order> findByAll(){
		List<Order> result = orderService.findAll();
		return result;
	}
	
	@PutMapping
	public ResponseEntity<Order> update (@PathVariable Integer id, @RequestBody Order order){
		orderService.update(id, order);
		return ResponseEntity.ok(order);
	}
	
	   @PostMapping
	    public ResponseEntity<Order> insert(@RequestBody Order order) {
	        Order savedOrder = orderService.saveOrder(order);
	        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
	    }
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Order> delete(@PathVariable Integer id){
		orderService.delete(id);
		return ResponseEntity.ok().build();
	}
}
