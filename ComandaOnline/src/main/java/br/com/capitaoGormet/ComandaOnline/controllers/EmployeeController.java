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

import br.com.capitaoGormet.ComandaOnline.entities.Employee;
import br.com.capitaoGormet.ComandaOnline.services.EmpoyeeService;

@CrossOrigin(value = "*")
@RestController
@RequestMapping(value = "/user")
public class EmployeeController {

	@Autowired
	private EmpoyeeService empoyeeSercice;
	
	//pega todos os funcionarios
	@GetMapping
	public List<Employee> findAll(){
		List<Employee> result = empoyeeSercice.findAll();
		return result;
	}
	//pega o funcionario pelo numero do id;
	@GetMapping("/{id}")
	public Employee findById(@PathVariable Integer id) {
		Employee result = empoyeeSercice.findById(id);
		return result;
	}
	
	//adiciona um novo funcionario;
	@PostMapping
	public ResponseEntity<Employee> insert(@RequestBody Employee employee ) {
			empoyeeSercice.inser(employee);
		return new ResponseEntity(employee,  HttpStatus.CREATED);
	}
	
	//altera um funcionario
	@PutMapping
	public ResponseEntity<Employee> update(@PathVariable Integer id, @RequestBody Employee employee){
		empoyeeSercice.update(id,employee);
		return ResponseEntity.ok(employee);
	}	
	
	//deleta funcionario
	@DeleteMapping("/{id}")
	public ResponseEntity<Employee> delete (@PathVariable Integer id){
		empoyeeSercice.delete(id);
		return ResponseEntity.ok().build();
	}
	
	//criar uma busca pelas vendas do vendedor
	
	
}
