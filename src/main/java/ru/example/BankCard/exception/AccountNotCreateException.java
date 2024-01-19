package ru.example.BankCard.exception;

public class AccountNotCreateException extends RuntimeException{
    public AccountNotCreateException(String msg) {

        super(msg);
    }
}
