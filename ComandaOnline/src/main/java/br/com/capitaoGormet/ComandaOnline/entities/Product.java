package br.com.capitaoGormet.ComandaOnline.entities;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_product")

public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	// pratos=1 | Monte seu prato=2| Cervejas e Chopp=3 | Destilados dose=4 | Smoothies & Sobremesa=5 | Kalzones=6 | Bebidas em geral=7 | Combos=8
	private Integer category;
	private String name;
	@Column(length =2000)
	private String img;
	//@Column(scale =6, precision = 2 )
	private double price;
	
	public Product() {
		
	}
	
	public Product(Integer id, String name, double price, Integer categoria, String img) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.category = categoria;
		this.img = img;
	}
	
	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Integer getCategoria() {
		return category;
	}

	public void setCategoria(Integer categoria) {
		this.category = categoria;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return id == other.id;
	}
	
}
