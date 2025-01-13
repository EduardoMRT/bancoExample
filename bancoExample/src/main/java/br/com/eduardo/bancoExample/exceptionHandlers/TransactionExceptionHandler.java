package br.com.eduardo.bancoExample.exceptionHandlers;

import br.com.eduardo.bancoExample.exceptions.TransactionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TransactionExceptionHandler {

    @ExceptionHandler(TransactionException.class)
    public ResponseEntity<Object> handleTransactionException(TransactionException exception){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Erro de transação: " + exception.getMessage());
    }

}
