package br.com.capitaoGormet.ComandaOnline.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.capitaoGormet.ComandaOnline.entities.Order;
import br.com.capitaoGormet.ComandaOnline.repositories.OrderRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	public List<Order> findAll(){
		List<Order> order =  orderRepository.findAll();
		return order;
	}

	public Order findById (Integer id) {
		Optional<Order> result = orderRepository.findById(id);
		return result.orElseThrow();
	}
	
	public void update (Integer id, Order order) {
		Order orderDb = findById(id);
		orderRepository.save(order);	
	}
	
	public Order saveOrder(Order order) {
	    return orderRepository.save(order);
	}
	
	public void insert (Order order) {
			Order newOrder = new Order(order.getProduct(),order.getQuantity());
			orderRepository.save(newOrder);
		
	}
	
	public void delete (Integer id) {
		Order orderDelete = findById(id);
		orderRepository.delete(orderDelete);
	}
	
}
