package mx.com.naturgy.vass.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DatoNotFoundException extends RuntimeException {

	public DatoNotFoundException(String mensaje) {
		super(mensaje);
	}

}
