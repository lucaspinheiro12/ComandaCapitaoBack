package br.com.capitaoGormet.ComandaOnline.controllers;

import java.io.IOException;
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

import br.com.capitaoGormet.ComandaOnline.DTO.SalesCommandsDTO;
import br.com.capitaoGormet.ComandaOnline.entities.Commands;
import br.com.capitaoGormet.ComandaOnline.entities.Sale;
import br.com.capitaoGormet.ComandaOnline.services.CommandsService;

@CrossOrigin(value = "*")
@RestController
@RequestMapping(value = "/commands")
public class CommandsController {

	@Autowired
	private CommandsService commandsService;

	
	@GetMapping(value = "/{id}")
	public Commands findById (@PathVariable Integer id){
		Commands resul = commandsService.findById(id);
		return resul;
	}
	@GetMapping(value = "/cpf/{cpf}")
	public Commands findByCPF(@PathVariable String cpf){
		Commands result = commandsService.findByCPF(cpf);
		return result;
	}
	@GetMapping(value = "/name/{name}")
	public Commands findByName (@PathVariable String name){
		Commands result = commandsService.findByName(name);
		return result;
	}
	
	@GetMapping
	public List<Commands> findAll(){
		List<Commands> result = commandsService.findAll();
		return result;
	}
	@PutMapping
	public ResponseEntity<Commands> update(@PathVariable Integer id, @RequestBody Commands commands) {
		commandsService.update(id, commands);
		return ResponseEntity.ok(commands);
	}
	
	@PostMapping
	public ResponseEntity<Commands> insert (@RequestBody Commands commands){
		commandsService.insert(commands);
		return new ResponseEntity(commands, HttpStatus.CREATED);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Commands> delete(@PathVariable Integer id){
		commandsService.delete(id);
		return ResponseEntity.ok().build();
	}
	
	//exclui todas as commandas do dia.
	@DeleteMapping(value = "/encerrar" )
	public ResponseEntity<List<Sale>> encerrarCommands() throws IOException{
		List<Commands> result = commandsService.findAll();
		commandsService.encerrarCommands(result);
		return ResponseEntity.ok().build();
	}
	
}
