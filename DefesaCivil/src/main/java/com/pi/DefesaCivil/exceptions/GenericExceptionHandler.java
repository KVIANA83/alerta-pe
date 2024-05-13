package com.pi.DefesaCivil.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GenericExceptionHandler extends ResponseEntityExceptionHandler {

/*
    
 */
    @ExceptionHandler(ValidacaoException.class)
    public final ResponseEntity<BodyMensagem> lancaExcessao(ValidacaoException ex) {

        var body = BodyMensagem.builder().mensagem(ex.getMessage()).build();

        return new ResponseEntity<BodyMensagem>(body, HttpStatus.BAD_REQUEST);
    }
}