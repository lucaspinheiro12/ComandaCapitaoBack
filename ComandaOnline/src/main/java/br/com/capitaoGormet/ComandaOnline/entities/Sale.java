package br.com.capitaoGormet.ComandaOnline.entities;



import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_sales")
public class Sale{
	@Column (name = "NumeroPedido")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	@JsonBackReference
	private Commands idCommand;
	@OneToMany( fetch = FetchType.EAGER)
	private List<Order> order;
	//private String vendor;
	@ManyToOne
	private Employee vendor;
		
		public Sale() {}
	
	public Sale( Commands commands,List<Order> orders, Employee vendor) {

		this.idCommand= commands;
		/*for (Order order : orders) {
            order.setPrice(order.getProduct().getPrice() * order.getQuantity()); 
            order.setRate(order.getProduct().getPrice() * order.getQuantity());
            this.order.add(order);
         }*/
		this.vendor = vendor;
	}

	public Integer getId() {
		return id;
	}

 
	public Commands getIdCommand() {
		return idCommand;
	}

	public List<Order> getOrder() {
		return order;
	}

	public void setOrder(List<Order> order) {
		this.order = order;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Commands getCommands() {
		return idCommand;
	}

	public void setCommands(Commands commands) {
		this.idCommand = commands;
	}

	public Employee getVendor() {
		return vendor;
	}


	public void setVendor(Employee vendor) {
		this.vendor = vendor;
	}
	/*
	private double priceEnd(List<Order> orders) {
		double priceEnd = 0;
		Integer quantity = 0;
		for (Order orderPrice : orders) {
			quantity = orderPrice.getQuantity();
			priceEnd += orderPrice.getPrice() * quantity;
		}
		return priceEnd;
		//this.order = new ArrayList<>();
	}*/
}
