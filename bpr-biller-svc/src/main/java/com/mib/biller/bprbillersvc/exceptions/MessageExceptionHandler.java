package com.mib.biller.bprbillersvc.exceptions;

import com.mib.biller.bprbillersvc.dto.response.mobile.GeneralResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class MessageExceptionHandler extends ResponseEntityExceptionHandler {


    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new GeneralResponse().fail(ex));
    }

    @Override
    protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new GeneralResponse().fail(ex));
    }

    /**
     * Handle general error message.
     *
     * @param ex exception to be handled
     * @return response entity with error message
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> general(Exception ex) {
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new GeneralResponse().fail(ex));
    }

    /**
     * Handle bad request message.
     *
     * @param ex exception to be handled
     * @return response entity with error message
     */
    @ExceptionHandler(HttpClientErrorException.BadRequest.class)
    public ResponseEntity<?> badRequest(HttpClientErrorException.BadRequest ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new GeneralResponse().fail(ex));
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<?> notFound(NoSuchElementException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new GeneralResponse().fail(ex));
    }

    @ExceptionHandler(FlowException.class)
    public ResponseEntity<?> flowException(FlowException ex) {
        return ResponseEntity
                .status(HttpStatus.FAILED_DEPENDENCY)
                .body(new GeneralResponse().fail(ex));
    }


}
