package ru.example.BankCard.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler
    private ResponseEntity<PeopleErrorResponse> handleException(PersonNotFoundException e) {
        PeopleErrorResponse response = PeopleErrorResponse.builder()
                .message("Person with current id does not exist in the database")
                .timestamp(System.currentTimeMillis()).build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    private ResponseEntity<PeopleErrorResponse> handleException(PersonNotCreateException e) {
        PeopleErrorResponse response = PeopleErrorResponse.builder()
                .message(e.getMessage())
                .timestamp(System.currentTimeMillis()).build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    private ResponseEntity<AccountErrorResponse> handleException(AccountNotCreateException e) {
        AccountErrorResponse response = AccountErrorResponse.builder()
                .message(e.getMessage())
                .timestamp(System.currentTimeMillis()).build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    private ResponseEntity<AccountErrorResponse> handleException(AccountChangeBalanceException e) {
        AccountErrorResponse response = AccountErrorResponse.builder()
                .message("The balance cannot be negative")
                .timestamp(System.currentTimeMillis()).build();
        return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
    }
}
