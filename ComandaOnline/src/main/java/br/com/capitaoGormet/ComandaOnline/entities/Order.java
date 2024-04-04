package br.com.capitaoGormet.ComandaOnline.entities;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_order")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	private Product product;
	private Integer quantity; 
	 @Column(columnDefinition = "DECIMAL(10,2)")
	private double price;
	 @Column(columnDefinition = "DECIMAL(10,2)")
	private double rate;
	 
	public Order() {}
	
	public Order(Product product, Integer quantity) {
		this.product = product;
		this.quantity = quantity;
		this.price = product.getPrice()* quantity;
		this.rate = (product.getPrice()* quantity ) *0.1;
	}
	
	

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public double calculatesValueFull (double produto) {
		return produto  * 0.1;
	}
	
}
