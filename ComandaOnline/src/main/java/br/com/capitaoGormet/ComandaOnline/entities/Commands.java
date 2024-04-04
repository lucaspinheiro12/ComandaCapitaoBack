package br.com.capitaoGormet.ComandaOnline.entities;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_Commands")
public class Commands {
	
	@Id
	private Integer id ;
	@OneToOne
	private Client client;
	private double entry;

	 
	public Commands() {}
	
	public Commands(Client client, double entry, Integer id) {
		this.id = id;
		this.client = client;
		this.entry = entry;
	}

	public Integer getId() {
		return id;
	}

	public Double getEntry() {
		return entry;
	}

	public void setEntry(Double entry) {
		this.entry = entry;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

/*	public BigDecimal getFullValue() {
		return fullValue;
	}

	public void setFullValue(BigDecimal fullValue) {
		this.fullValue = fullValue;
	}
	*/
	private BigDecimal calculatesValueFull (List<Order> order, double entry) {
		BigDecimal calculateValueFull = BigDecimal.ZERO;
		for (Order x : order) {
			calculateValueFull = calculateValueFull.add(BigDecimal.valueOf(x.getPrice()));
		}
		calculateValueFull = calculateValueFull.add(BigDecimal.valueOf(entry)).multiply(BigDecimal.valueOf(1.1));
		return calculateValueFull;
	}

}