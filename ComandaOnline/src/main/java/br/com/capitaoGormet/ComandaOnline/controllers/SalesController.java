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
import br.com.capitaoGormet.ComandaOnline.entities.Order;
import br.com.capitaoGormet.ComandaOnline.entities.Sale;
import br.com.capitaoGormet.ComandaOnline.services.OrderService;
import br.com.capitaoGormet.ComandaOnline.services.SaleService;

@CrossOrigin(value = "*")
@RestController
@RequestMapping(value = "/sales")
public class SalesController {

	@Autowired
	private SaleService saleService;
	@Autowired
	private OrderService orderService;
	
	@GetMapping(value = "/{id}")
	public Sale findById (@PathVariable Integer id){	
		return saleService.findById(id);
	}
	
	@GetMapping
	public List<Sale> findAll(){
		List<Sale> result = saleService.findAll();
		return  result;
	}

	 @PostMapping
	    public ResponseEntity<Sale> insert(@RequestBody Sale sale) {
	        List<Order> orders = sale.getOrder();
	        
	        // Salva cada Order separadamente
	        for (Order order : orders) {
	        	order.setPrice(order.getPrice()*order.getQuantity());
	        	order.setRate(order.calculatesValueFull(order.getPrice()));
	            orderService.saveOrder(order);
	        }

	        // Salva Sale associando os Orders salvos
	        	saleService.insert(sale);
	        return new ResponseEntity<>(sale, HttpStatus.CREATED);
	    }
	 
	 //pega o pedido que tem mais de uma order que o vendedor quer excluir.
	 //passando o id da sale e o id da order.
	 //assim ela pega a sale e faz um update removendo apenas a order que o vendedor gostaria de remover.
	 @PutMapping("/{saleId}/{orderId}")
	  public void removeOrderFromSale(@PathVariable Integer saleId, @PathVariable Integer orderId) {
		 saleService.updateSaleWithRemovedOrder(saleId, orderId);
	  }
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Sale> delete(@PathVariable Integer id){
		saleService.delete(id);
		return ResponseEntity.ok().build();
	}
	//exclui todas as vendas apos o termino do dia e joga para uma plani
	@DeleteMapping(value = "/pago" )
	public ResponseEntity<List<Sale>> pagoSale() throws IOException{
		List<Sale> result = saleService.findAll();
		saleService.pago(result);
		return ResponseEntity.ok().build();
	}
	
	//pedido detalhado id comanda
	@GetMapping(value = "/client/id/{idCommand}")
	public List<Sale>  requestIdCommand(@PathVariable Integer idCommand) {
		List<Sale> result = saleService.findByCommandsIdCommand(idCommand);
		return result;
	}

	//pedido detalhado pelo CPF
	@GetMapping(value = "/client/cpf/{cpf}")
	public List<Sale> requestCpf (@PathVariable String cpf){	
		List<Sale> result = saleService.findByIdClientCPF(cpf);
		return result ;
	}
	//pedido detalhado pelo nome
	@GetMapping(value = "/client/name/{name}")
	public List<Sale> requestName (@PathVariable String name){	
		List<Sale> result = saleService.findByIdClientName(name);
		return result ;
	}
	//pedido resumido pelo numero do cpf
		@GetMapping(value = "/client/summedUp/cpf/{cpf}")
		public SalesCommandsDTO requesCPF (@PathVariable String cpf ){
			 SalesCommandsDTO result =  saleService.findByIdClientSumedUpCPF(cpf);
			return result;	
		}
		//pedido resumido pelo NOME dO CLIENTE
		@GetMapping(value = "/client/summedUp/name/{name}")
		public SalesCommandsDTO requesCommand(@PathVariable String name ){
			 SalesCommandsDTO result =  saleService.findByIdClientSumedUpName(name);
			return result;	
		}
	
	//pedido resumido pelo numero da command
	@GetMapping(value = "/client/summedUp/id/{idCommand}")
	public SalesCommandsDTO requesCommand(@PathVariable Integer idCommand ){
		 SalesCommandsDTO result =  saleService.findByIdClientSumedUpCommand(idCommand);
		return result;	
	}
	//retorna em orde decrecente os cliente que mais gastaram
	@GetMapping("/total")
    public List<Object[]> getTotalSalesByClientOrderedByAmount() {
        return saleService.findTotalSalesByClientOrderedByAmount();
    }
}
