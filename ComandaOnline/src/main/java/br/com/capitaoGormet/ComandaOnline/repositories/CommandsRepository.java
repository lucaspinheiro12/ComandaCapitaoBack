package br.com.capitaoGormet.ComandaOnline.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.capitaoGormet.ComandaOnline.entities.Commands;


public interface CommandsRepository extends JpaRepository<Commands, Integer>{

    Commands findByClient_Cpf(String cpf);
    Commands findByClient_Name(String name);
	
}
