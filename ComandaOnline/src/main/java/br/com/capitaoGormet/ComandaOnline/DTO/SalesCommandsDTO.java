package br.com.capitaoGormet.ComandaOnline.DTO;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.format.annotation.NumberFormat;

import br.com.capitaoGormet.ComandaOnline.entities.Commands;
import br.com.capitaoGormet.ComandaOnline.entities.Order;
import jakarta.persistence.Embeddable;

@Embeddable
public class SalesCommandsDTO {
	
	private List<Order> order;
	private Commands commands;
	private BigDecimal fullValueRate;
	 @NumberFormat(pattern = "#,##0.00")
	private BigDecimal fullValue;
	
	
	public SalesCommandsDTO() {}
	
	public SalesCommandsDTO(Commands commands, List<Order> order) {
		this.commands = commands;
		this.order = order;
		this.fullValue = calculatesValueFull(order, commands.getEntry());
		this.fullValueRate=calculatesValueFullRate(order);
	}
		

	public BigDecimal getfullValueRate() {
		return fullValueRate;
	}

	public void setfullValueRate(BigDecimal fullValueRate) {
		this.fullValueRate = fullValueRate;
	}

	public Commands getCommands() {
		return commands;
	}

	public void setCommands(Commands commands) {
		this.commands = commands;
	}

	public List<Order> getOrder() {
		return order;
	}

	public void setOrder(List<Order> order) {
		this.order = order;
	}

	public BigDecimal getFullValue() {
		return fullValue;
	}

	public void setFullValue(BigDecimal fullValue) {
		this.fullValue = fullValue;
	}
	
	private BigDecimal calculatesValueFull(List<Order> order, double entry) {
	    BigDecimal calculateValueFull = BigDecimal.ZERO;

	    for (Order x : order) {
	        BigDecimal orderPrice = BigDecimal.valueOf(x.getPrice());
	        calculateValueFull = calculateValueFull.add(orderPrice);
	    }

	    // Adiciona a entrada
	    calculateValueFull = calculateValueFull.add(BigDecimal.valueOf(entry));

	    // Define a escala e o modo de arredondamento
	    calculateValueFull = calculateValueFull.setScale(2, RoundingMode.HALF_UP);

	    return calculateValueFull;
	}
	
	private BigDecimal calculatesValueFullRate(List<Order> order) {
	    BigDecimal calculateValueFull = BigDecimal.ZERO;

	    for (Order x : order) {
	        BigDecimal orderPrice = BigDecimal.valueOf(x.getPrice()).multiply(BigDecimal.valueOf(0.1));
	        calculateValueFull = calculateValueFull.add(orderPrice);
	    }

	    // Define a escala e o modo de arredondamento
	    calculateValueFull = calculateValueFull.setScale(2, RoundingMode.HALF_UP);

	    return calculateValueFull;
	}


	
	
}
