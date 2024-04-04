package br.com.capitaoGormet.ComandaOnline.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.capitaoGormet.ComandaOnline.entities.Client;
import br.com.capitaoGormet.ComandaOnline.entities.Commands;
import br.com.capitaoGormet.ComandaOnline.entities.Sale;
import br.com.capitaoGormet.ComandaOnline.repositories.ClientRepository;
import br.com.capitaoGormet.ComandaOnline.repositories.CommandsRepository;

@Service
public class ClientService {
	@Autowired
	private ClientRepository clientRepository;

	
	public List<Client> findAll(){
		List<Client> clients =  clientRepository.findAll();
		return clients;
	}
	
	public Client findById (String cpf) {
		Optional<Client> clientId = clientRepository.findById(cpf);
		 return  clientId.orElseThrow();
	}
	
	public void insert (Client client) {
		clientRepository.save(client);
	}
	
	public void update (String id, Client client) {
		Client clientDB = findById(id);
		clientRepository.save(client);
	}
	
	public void delete (String id) {
		Client clientDB = findById(id);
		if(clientDB != null) {
				clientRepository.delete(clientDB);
		}	
	}

}
