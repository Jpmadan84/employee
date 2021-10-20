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

import kenzan.employee.service.Logger;

@Order(Ordered.LOWEST_PRECEDENCE)
@ControllerAdvice
public class CatchAllExceptionAdvice extends ResponseEntityExceptionHandler{

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<?> exception(Exception ex, WebRequest request){
		Logger.in(getClass()).error("Request {}", request.toString());
		Logger.in(getClass()).error("Exception details:", ex);
		
		return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), 
				HttpStatus.INTERNAL_SERVER_ERROR,
				request);
	}
}
