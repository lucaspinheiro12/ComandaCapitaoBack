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
import br.com.capitaoGormet.ComandaOnline.entities.Client;
import br.com.capitaoGormet.ComandaOnline.services.ClientService;

@CrossOrigin(value = "*")
@RestController
@RequestMapping(value = "/client")
public class ClientController {

	@Autowired
	private ClientService clienteService;


	@GetMapping(value = "/{cpf}")
	public Client findById (@PathVariable String cpf){
		Client resul = clienteService.findById(cpf);
		return resul;
	}
	
	//pega todos os clientes
	@GetMapping
	public List<Client> findAll(){
		List<Client> result = clienteService.findAll();
		return result;
	}
	
	//pega pelo numero do cpf o cliente no banco e faz as alterações	
	@PutMapping
	public ResponseEntity<Client> update(@PathVariable String cpf, @RequestBody Client client) {
		clienteService.update(cpf, client);
		return ResponseEntity.ok(client);
	}
	
	//adiciona um novo cliente
	@PostMapping
	public ResponseEntity<Client> insert (@RequestBody Client client){
		clienteService.insert(client);
		return new ResponseEntity(client, HttpStatus.CREATED);
	}
	
	//deleta o cliente do cpf passado
	@DeleteMapping(value = "/{cpf}")
	public ResponseEntity<Client> delete(@PathVariable String cpf){
		clienteService.delete(cpf);
		return ResponseEntity.ok().build();
	}
	
}
