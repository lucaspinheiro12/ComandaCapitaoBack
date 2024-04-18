package br.com.capitaoGormet.ComandaOnline.services;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.capitaoGormet.ComandaOnline.DTO.SalesCommandsDTO;
import br.com.capitaoGormet.ComandaOnline.entities.Client;
import br.com.capitaoGormet.ComandaOnline.entities.Commands;
import br.com.capitaoGormet.ComandaOnline.entities.Order;
import br.com.capitaoGormet.ComandaOnline.entities.Product;
import br.com.capitaoGormet.ComandaOnline.entities.Sale;
import br.com.capitaoGormet.ComandaOnline.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository saleRepository;
	
	public List<Sale> findAll(){
		List<Sale> sales =  saleRepository.findAll();
		return sales;
	}

	public Sale findById (Integer id) {
		Optional<Sale> result = saleRepository.findById(id);
		return result.orElseThrow();
	}
	
	public void update (Integer id, Sale sale) {
		Sale saleDb = findById(id);
		saleRepository.save(sale);	
	}
	
	 public void updateSaleWithRemovedOrder(Integer idSale, Integer idOrder) {
		Sale saleDb = findById(idSale);
	    // Remove o pedido da lista de pedidos da venda
        List<Order> updatedOrders = saleDb.getOrder().stream()
                                               .filter(order -> !order.getId().equals(idOrder))
                                               .collect(Collectors.toList());
        saleDb.setOrder(updatedOrders);
        update(idSale,saleDb);
	}
	 
	public void insert (Sale sale) {
		Sale newSale = new Sale();
	       saleRepository.save(sale);
	}
	
    public void pago(List<Sale> sales) throws IOException {
        // Nome do arquivo Excel
    	List<Object[]> ClientSales = findTotalSalesByClientOrderedByAmount();
        String filename = "/home/ubuntu/"
        		+ "vendas_" + LocalDate.now() + ".xlsx";

        // Cria o arquivo Excel
        Workbook workbook = new XSSFWorkbook();
        org.apache.poi.ss.usermodel.Sheet sheet = workbook.createSheet("Vendas");

        // Cria a primeira linha com os nomes das colunas
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID do Comando");
        headerRow.createCell(1).setCellValue("cpf cliente");
        headerRow.createCell(2).setCellValue("Nome do Vendedor");
        headerRow.createCell(3).setCellValue("Nome do Produto");
        headerRow.createCell(4).setCellValue("Quantidade");
        headerRow.createCell(5).setCellValue("Preço");
        headerRow.createCell(9).setCellValue("CPF");
        headerRow.createCell(10).setCellValue("NOME");
        headerRow.createCell(11).setCellValue("Contato");
        headerRow.createCell(12).setCellValue("TOTAL GASTO");
        
        

        // Escreve os dados das vendas no arquivo Excel
        int rowNum = 1;
        double totalVendas = 0.0;
        for (Sale sale : sales) {
            for (Order order : sale.getOrder()) { // Para cada pedido na venda
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(sale.getIdCommand().getId()); // ID do comando
                row.createCell(1).setCellValue(sale.getIdCommand().getClient().getCpf()); // ID do comando
                row.createCell(2).setCellValue(sale.getVendor().getName()); // Nome do vendedor
                row.createCell(3).setCellValue(order.getProduct().getName()); // Nome do produto
                row.createCell(4).setCellValue(order.getQuantity()+ order.getRate()); // Quantidade
                row.createCell(5).setCellValue(order.getPrice()); // Preço
                totalVendas += order.getQuantity() * order.getPrice() + order.getRate(); // Calcula o total das vendas
            }
        }
     
        // Adiciona uma última linha com o valor total das vendas
        Row totalRow = sheet.createRow(rowNum);
        totalRow.createCell(0).setCellValue("Total das Vendas:");
        totalRow.createCell(4).setCellValue(totalVendas);

        	rowNum = 1;
        for (Object[] clientSale : ClientSales) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(9).setCellValue((String) clientSale[0]); // CPF do cliente
            row.createCell(10).setCellValue((String) clientSale[1]); // Nome do cliente
            row.createCell(11).setCellValue((String) clientSale[2]); // Contato do cliente
            row.createCell(12).setCellValue((double) clientSale[3]); // Total de vendas do cliente
        }
        
        // Escreve o conteúdo do workbook no arquivo
        try (FileOutputStream fileOut = new FileOutputStream(filename)) {
            workbook.write(fileOut);
        } finally {
            workbook.close();
        }

        // Exclui as vendas
        sales.forEach(sale -> delete(sale.getId()));
    }
	
	public void delete (Integer id) {
		Sale saleDelete = findById(id);
		saleRepository.delete(saleDelete);
	}
	
	//pedido detalhado id comanda
	public List<Sale> findByCommandsIdCommand(Integer idCommand) {
		List<Sale> result = saleRepository.findByIdCommandId(idCommand);
		return result;
	}
	
	//pedido detalhado pelo CPF
	public List<Sale> findByIdClientCPF (String cpf) {
		List<Sale> result = saleRepository.findByIdCommandId_Client_Cpf(cpf);
		return result;
	}
	
	//pedido detalhado pelo nome
	public List<Sale> findByIdClientName (String name) {
			List<Sale> result = saleRepository.findByIdCommandId_Client_Name(name);
			return result;
		}
		
	//pedido resumido pelo numero da command
	public SalesCommandsDTO findByIdClientSumedUpCommand(Integer id) {
	    List<Sale> result = findByCommandsIdCommand(id);
	    Commands commands = result.get(0).getCommands();

	    return transforma(result,commands);
	}
	
	//pedido resumido pelo numero do cpf
	public SalesCommandsDTO findByIdClientSumedUpCPF(String id) {
	    List<Sale> result = findByIdClientCPF(id);
	    Commands commands = result.get(0).getCommands();

	    return transforma(result,commands);
	}
	
	//pedido resumido pelo nome
		public SalesCommandsDTO findByIdClientSumedUpName(String id) {
		    List<Sale> result = findByIdClientName(id);
		    Commands commands = result.get(0).getCommands();

		    return transforma(result,commands);
		}
	private SalesCommandsDTO transforma(List<Sale> result, Commands commands) {
		Map<Product, Integer> productQuantityMap = new HashMap<>();
		for (Sale sale : result) {
			List<Order> orders = sale.getOrder();

			for (Order order : orders) {
				Product product = order.getProduct();
			    Integer quantity = order.getQuantity();

			    // Verificar se o produto já está no mapa
			    if (productQuantityMap.containsKey(product)) {
			    	// Se estiver, aumentar a quantidade
			    	int existingQuantity = productQuantityMap.get(product);
			        productQuantityMap.put(product, existingQuantity + quantity);
			    } else {
			        // Se não estiver, adicionar ao mapa
			        productQuantityMap.put(product, quantity);
			       }
			 }
		}
		// Criar a lista final de Order com base no mapa
		List<Order> ListOrder = new ArrayList<>();
		for (Map.Entry<Product, Integer> entry : productQuantityMap.entrySet()) {
			Product product = entry.getKey();
			int quantity = entry.getValue();
			double price = product.getPrice(); // Defina o preço corretamente

			// Criar um novo Order com a quantidade consolidada
			Order order = new Order(product, quantity);
			ListOrder.add(order);
		}

		SalesCommandsDTO orderConcise = new SalesCommandsDTO(commands, ListOrder);// client.getEntry(),
		return orderConcise;
	}
	
	  public List<Object[]> findTotalSalesByClientOrderedByAmount() {
	        return saleRepository.findTotalSalesByClientOrderedByAmount();
	    }
			
}
