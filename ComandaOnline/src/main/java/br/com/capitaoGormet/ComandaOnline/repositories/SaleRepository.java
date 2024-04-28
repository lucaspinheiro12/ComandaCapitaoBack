package br.com.capitaoGormet.ComandaOnline.repositories;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.capitaoGormet.ComandaOnline.entities.Sale;
@Repository
public interface SaleRepository extends JpaRepository<Sale, Integer> {
	
	List<Sale>findByIdCommandId(Integer idCommand);
	
	List<Sale> findByIdCommandId_Client_Cpf(String cpf);
	
	List<Sale> findByIdCommandId_Client_Name(String name);
	
	/**
	 * Retorna uma lista de arrays de objetos contendo informações sobre as vendas totais de cada cliente, 
	 * ordenadas pelo valor total das vendas em ordem decrescente.
	 * Cada array contém os seguintes elementos:
	 *   - clientCpf: CPF do cliente
	 *   - clientName: Nome do cliente
	 *   - clientContact: Contato do cliente
	 *   - totalAmount: Valor total das vendas para esse cliente
	 * Os valores são calculados somando o produto da quantidade e preço de cada item do pedido, 
	 * além da taxa associada, para cada venda.
	 * 
	 * @return Uma lista de arrays de objetos contendo as informações sobre as vendas totais de cada cliente.
	 */
	@Query("SELECT s.idCommand.client.cpf AS clientCpf, s.idCommand.client.name AS clientName, s.idCommand.client.contact AS clientContact, SUM(o.quantity * o.price + o.rate) AS totalAmount " +
	        "FROM Sale s " +
	        "JOIN s.idCommand c " +
	        "JOIN s.order o " +
	        "GROUP BY s.idCommand.client.cpf, s.idCommand.client.name, s.idCommand.client.contact " +
	        "ORDER BY SUM(o.quantity * o.price) DESC")
	List<Object[]> findTotalSalesByClientOrderedByAmount();




}

