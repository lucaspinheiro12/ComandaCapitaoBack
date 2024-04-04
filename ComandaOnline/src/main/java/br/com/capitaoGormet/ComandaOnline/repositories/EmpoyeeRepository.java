package br.com.capitaoGormet.ComandaOnline.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.capitaoGormet.ComandaOnline.entities.Employee;

public interface EmpoyeeRepository extends JpaRepository<Employee, Integer>{

}
