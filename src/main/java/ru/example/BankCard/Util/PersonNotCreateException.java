package ru.example.BankCard.Util;

public class PersonNotCreateException extends RuntimeException {
    public PersonNotCreateException(String msg) {
        super(msg);
    }
}
