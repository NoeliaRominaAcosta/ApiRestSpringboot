package com.itrsa.monolith.handler;

import com.itrsa.monolith.dto.ErrorHTTPDTO;
import com.itrsa.monolith.exception.RandomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ RandomException.class })
    public ResponseEntity<ErrorHTTPDTO> handle(RandomException e, HttpServletRequest request) {

        ErrorHTTPDTO errorHTTP = new ErrorHTTPDTO();
        errorHTTP.setTipo("NEGOCIO");
        errorHTTP.setCodigo(HttpStatus.NOT_FOUND.value());
        errorHTTP.setEstado(HttpStatus.NOT_FOUND.getReasonPhrase());
        errorHTTP.setDetalle(e.getLocalizedMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorHTTP);
    }
}
