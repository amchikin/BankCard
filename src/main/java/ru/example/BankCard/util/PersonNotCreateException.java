package ru.example.BankCard.util;

public class PersonNotCreateException extends RuntimeException {
    public PersonNotCreateException(String msg) {
        super(msg);
    }
}
