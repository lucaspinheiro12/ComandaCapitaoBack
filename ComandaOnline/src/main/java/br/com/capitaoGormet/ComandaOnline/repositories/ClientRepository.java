package br.com.capitaoGormet.ComandaOnline.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import br.com.capitaoGormet.ComandaOnline.entities.Client;

public interface ClientRepository extends JpaRepository<Client, String>{
	//Client findByIdCommand(Integer idClient);
}
