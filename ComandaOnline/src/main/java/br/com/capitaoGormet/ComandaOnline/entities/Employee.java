package br.com.capitaoGormet.ComandaOnline.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_employee")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	@Column(nullable = false, unique = true )
	private String userName;
	@Column(nullable = false)
	private String password;
	//1: fUNCIONARIO PADRAO --- 2: CHEFE --- 3 porta
	private Integer function;
	
	public Employee() {
	
	}
	
	public Employee(Integer id, String userName, String name, String password, Integer function) {
		this.id = id;
		this.name = name;
		this.userName = userName;
		this.password = password;
		this.function = function;
	}
	

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getFunction9() {
		return function;
	}

	public void setFunction(Integer function) {
		this.function = function;
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
	
	
	
}
