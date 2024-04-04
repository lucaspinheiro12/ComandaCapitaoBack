package br.com.capitaoGormet.ComandaOnline.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.capitaoGormet.ComandaOnline.DTO.SalesCommandsDTO;
import br.com.capitaoGormet.ComandaOnline.entities.Client;
import br.com.capitaoGormet.ComandaOnline.entities.Commands;
import br.com.capitaoGormet.ComandaOnline.entities.Order;
import br.com.capitaoGormet.ComandaOnline.entities.Product;
import br.com.capitaoGormet.ComandaOnline.entities.Sale;
import br.com.capitaoGormet.ComandaOnline.handler.BusinessExectio;
import br.com.capitaoGormet.ComandaOnline.repositories.CommandsRepository;

@Service
public class CommandsService {

	@Autowired
	private CommandsRepository commandsRepository;
	
	@Autowired 
	private SaleService saleService;
	
	
	public List<Commands> findAll() {
		List<Commands> allCommans = commandsRepository.findAll();
		return allCommans;
	}
	
	public Commands findById(Integer id) {
	    Optional<Commands> commandOptional = commandsRepository.findById(id);

	    if (! commandOptional.isPresent()) {
	    	return commandOptional.orElseThrow();
	    }
	    return commandOptional.orElseThrow(() -> new BusinessExectio("Command não encontrada para o ID: " + id));
	}

	public Commands findByCPF(String CPF) {
		Commands result = commandsRepository.findByClient_Cpf(CPF);
		return result;
	}

	public Commands findByName(String name) {
		Commands result = commandsRepository.findByClient_Name(name);
		return result;
	}
	
	public void insert(Commands commands) {
		Optional<Commands> existingCommand = commandsRepository.findById(commands.getId());
	   
	    if (existingCommand.isPresent()) {
	        throw new BusinessExectio("Essa comanda já está sendo usada pelo cliente " + commands.getClient().getName()+ ". Feche o pagamento para continuar usando.");
	    } else {
	        commandsRepository.save(commands);
	    }
	}
	
	public void update (Integer id, Commands commands) {
		Commands commandsId = findById(id);
		commandsRepository.save(commandsId);
	}
	
	public void delete (Integer id) {
		Commands commandsDelete = findById(id);
		commandsRepository.delete(commandsDelete);
	}
	

	public void encerrarCommands(List<Commands> result) {
		result.forEach(command -> delete(command.getId()));
		
	}


}
