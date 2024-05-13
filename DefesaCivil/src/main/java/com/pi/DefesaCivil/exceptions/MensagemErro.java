package com.pi.DefesaCivil.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MensagemErro {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final Map<String,String> lancaExcessao(MethodArgumentNotValidException ex) {

        Map<String,String> erros = new HashMap<>();
        
        ex.getBindingResult().getAllErrors().forEach(erro -> {

            var campo = erro.getObjectName();
            var motivo = erro.getDefaultMessage();

            erros.put(campo, motivo);
        });

        return erros;
    }
}
