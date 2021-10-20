package kenzan.employee.controller.advice;


import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import kenzan.employee.domain.ErrorResponse;
import kenzan.employee.domain.ValidationException;
import kenzan.employee.service.Logger;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ValidationExceptionAdvice extends ResponseEntityExceptionHandler{

	@ExceptionHandler(value= {
			ValidationException.class
	})
	public ResponseEntity<?> exception(Exception ex, WebRequest request){
		Logger.in(getClass()).error("Request {}", request.toString());
		Logger.in(getClass()).error("Exception details:", ex);
		return handleExceptionInternal(ex, new ErrorResponse("Employee not found"), new HttpHeaders(), HttpStatus.BAD_REQUEST,
				request);
	}
}
