package mx.com.naturgy.vass.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Controller
public class CustomerResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	// Con la @ExceptionHandler, es para el manejo de excepciones
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {
		ExceptionResponse response=new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity(response,HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@ExceptionHandler(DatoNotFoundException.class)
	public final ResponseEntity<Object> handleException(DatoNotFoundException datoNotFoundException, WebRequest request) {
		ExceptionResponse response=new ExceptionResponse(new Date(), datoNotFoundException.getMessage(), request.getDescription(false));
		return new ResponseEntity(response,HttpStatus.INTERNAL_SERVER_ERROR);

	}
}
