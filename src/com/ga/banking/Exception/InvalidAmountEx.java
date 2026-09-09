package com.ga.banking.Exception;

public class InvalidAmountEx extends RuntimeException{
    public InvalidAmountEx(String message){
        super(message);
    }
}
