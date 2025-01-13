package br.com.eduardo.bancoExample.exceptions;

import java.util.Optional;

public class TransactionException extends  RuntimeException{

    public  TransactionException(String message){
        super(message);
    }

}
