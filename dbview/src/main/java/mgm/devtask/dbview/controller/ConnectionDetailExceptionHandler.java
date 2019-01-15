package mgm.devtask.dbview.controller;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import mgm.devtask.dbview.api.ConnectionServiceDetailMissingException;
import mgm.devtask.dbview.api.ErrorDetail;

@ControllerAdvice
@RestController
public class ConnectionDetailExceptionHandler extends ResponseEntityExceptionHandler {
	
	  @ExceptionHandler(ConnectionServiceDetailMissingException.class)
	  public final ResponseEntity<ErrorDetail> handleDetailNotFoundException(ConnectionServiceDetailMissingException ex, WebRequest request) {
	    ErrorDetail errorDetail = new ErrorDetail(new Date(), ex.getMessage(),
	        request.getDescription(false));
	    return new ResponseEntity<>(errorDetail, HttpStatus.NOT_FOUND);
	  }
}
