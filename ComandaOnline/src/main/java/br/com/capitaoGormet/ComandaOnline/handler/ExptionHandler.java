package br.com.capitaoGormet.ComandaOnline.handler;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.id.IdentifierGenerationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.RestControllerAdvice;




@RestControllerAdvice
public class ExptionHandler {
	
	private final Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);
	
		@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<String> handle(NoSuchElementException noSuchElementException){
		return new ResponseEntity<>("olho no lace error", HttpStatus.PRECONDITION_FAILED);
	}
		
		
		
		@ExceptionHandler(IdentifierGenerationException.class)
		public ResponseEntity<String> handleDataIntegrityViolation(IdentifierGenerationException ex) {
			 String customErrorMessage = "Apenas numeros no valor da comanda.";
			return ResponseEntity.status(HttpStatus.CONFLICT).body(customErrorMessage);
		}
		
		
		@ExceptionHandler(HttpMessageNotReadableException.class)
		public ResponseEntity<String> handleDataIntegrityViolation(HttpMessageNotReadableException ex) {
			 String customErrorMessage = "Apenas numeros no valor da entrada.";
			return ResponseEntity.status(HttpStatus.CONFLICT).body(customErrorMessage);
		}
	@ExceptionHandler(BusinessExectio.class)
	public ResponseEntity<String> handlerBusinessException(BusinessExectio exception){
		String error = exception.getMessage();
		return ResponseEntity.badRequest().body(error);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<String> handleIntegrityViolation(DataIntegrityViolationException ex) {
	    // Aqui você pode extrair informações específicas da exceção, como a mensagem de erro
	    String errorMessage = ex.getMessage();

	    // Adicione lógica para extrair o número da comanda e o CPF do cliente da mensagem de erro
	    String[] commandNumberAndClientCPF = extractCommandNumberAndClientCPFFromErrorMessage(errorMessage);
	    String commandNumber = commandNumberAndClientCPF[0];
	    String clientCPF = commandNumberAndClientCPF[1];

	    // Use o número da comanda e o CPF do cliente para construir uma mensagem personalizada
	    String customErrorMessage = "O cliente com o CPF: (" + clientCPF + ") já esta usando uma comanda com o numero: (" + commandNumber + ")"; ;

	    // Registre o erro se necessário
	    //logger.error(customErrorMessage);

	    // Retorne uma resposta personalizada para o front end
	    return ResponseEntity.status(HttpStatus.CONFLICT).body(customErrorMessage);
	}

	   
	// Método auxiliar para extrair o número da comanda e o CPF do cliente da mensagem de erro
	   private String[] extractCommandNumberAndClientCPFFromErrorMessage(String errorMessage) {
	       // Implemente a lógica necessária para extrair o número da comanda e o CPF do cliente da mensagem de erro
	       // Certifique-se de adaptar esta lógica à estrutura específica da mensagem de erro do seu sistema

	       // Procurar o padrão ( /* 1 */ '09458139961'), onde 1 é o número da comanda e 09458139961 é o CPF
	       Matcher matcher = Pattern.compile("/\\*\\s(\\d+)\\s\\*/\\s'([^']+)'").matcher(errorMessage);

	       // Se encontrar, retornar um array de strings com o número da comanda na posição 0 e o CPF na posição 1
	       if (matcher.find()) {
	           return new String[]{matcher.group(1), matcher.group(2)};
	       }

	       // Se não encontrar o número da comanda e o CPF na mensagem de erro, retorne um array de strings padrão ou lance uma exceção
	       return new String[]{"Comanda Desconhecida", "CPF Desconhecido"};
	   }

}
