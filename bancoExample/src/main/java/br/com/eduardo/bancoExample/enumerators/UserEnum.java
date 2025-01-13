package br.com.eduardo.bancoExample.enumerators;

public enum UserEnum{
    NORMAL(1), CREDITARIO(2), EMPRESARIAL(3);

    private int value;

    private UserEnum(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }

}