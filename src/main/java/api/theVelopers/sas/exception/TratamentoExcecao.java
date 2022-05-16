package api.theVelopers.sas.exception;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.nio.file.AccessDeniedException;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import api.theVelopers.sas.entity.RespostaHttp;
import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class TratamentoExcecao implements ErrorController {
	
	private static final String ACCOUNT_LOCKED = "Sua conta foi bloqueada. Por favor contate um admnistrador";
	private static final String METHOD_IS_NOT_ALLOWED = "Requisição não disponível. Por favor requisite uma '%s' requisição";
	private static final String INTERNAL_SERVER_ERROR_MSG = "Um erro ocorreu ao processar sua requisição";
	private static final String INCORRECT_CREDENTIALS = "Email / senha incorreto. Por favor tente novamente";
	private static final String ACCOUNT_DISABLED = "Sua conta foi desabilitada. Por favor contate um admnistrador";
	private static final String ERROR_PROCESSING_FILE = "Um erro ocorreu ao processar arquivo";
	private static final String NOT_ENOUGH_PERMISSION = "Usuário não possui permissão necessária";
	public static final String ERROR_PATH = "/error";

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<RespostaHttp> accessDeniedException() {
		return criarRespostaHttp(FORBIDDEN, NOT_ENOUGH_PERMISSION);
	}

	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<RespostaHttp> erroNegocio(NegocioException e) {
		return criarRespostaHttp(HttpStatus.BAD_REQUEST, e.getMessage());
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<RespostaHttp> internalServerErrorException(Exception exception) {
		return criarRespostaHttp(INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR_MSG);
	}

	private ResponseEntity<RespostaHttp> criarRespostaHttp(HttpStatus httpStatus, String message) {
		RespostaHttp resposta = new RespostaHttp(httpStatus.value(), httpStatus, httpStatus.getReasonPhrase(), message);

		return new ResponseEntity<>(resposta, httpStatus);
	}

	@RequestMapping(ERROR_PATH)
	public ResponseEntity<RespostaHttp> notFound404() {
		return criarRespostaHttp(NOT_FOUND, "URL não encontrado...");
	}

	public String getErrorPath() {
		return ERROR_PATH;
	}

}
