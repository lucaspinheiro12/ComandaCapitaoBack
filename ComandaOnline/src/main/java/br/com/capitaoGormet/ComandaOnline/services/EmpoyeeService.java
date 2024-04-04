package br.com.capitaoGormet.ComandaOnline.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.capitaoGormet.ComandaOnline.entities.Employee;
import br.com.capitaoGormet.ComandaOnline.repositories.EmpoyeeRepository;

@Service
public class EmpoyeeService {
	
	@Autowired
	private EmpoyeeRepository empoyeeRepository;
	

	public List<Employee> findAll() {
		List<Employee> result = empoyeeRepository.findAll();
		return result;
	}

	public Employee findById(Integer id) {
		Optional<Employee> result = empoyeeRepository.findById(id);
		return result.orElseThrow();
	}

	public void inser(Employee employee) {
		empoyeeRepository.save(employee);
		
	}

	public void update(Integer id, Employee employee) {
		Employee employeedb = findById(id);
		empoyeeRepository.save(employee); 
		
	}

	public void delete(Integer id) {
		Employee employeeDb = findById(id);
		empoyeeRepository.delete(employeeDb);
		
	}
	//criar uma que pega pelo nove to vendedor todas as vendas

}
