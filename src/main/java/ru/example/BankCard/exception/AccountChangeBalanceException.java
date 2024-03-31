package ru.example.BankCard.exception;

public class AccountChangeBalanceException extends RuntimeException {
    public AccountChangeBalanceException(String msg) {
        super(msg);
    }
}
