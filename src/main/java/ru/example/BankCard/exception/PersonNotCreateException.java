package ru.example.BankCard.exception;

public class PersonNotCreateException extends RuntimeException {
    public PersonNotCreateException(String msg) {
        super(msg);
    }
}
