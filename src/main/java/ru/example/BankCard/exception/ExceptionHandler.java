package ru.example.BankCard.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler
    private ResponseEntity<PeopleErrorResponse> handleException(PersonNotFoundException e) {
        return new ResponseEntity<>(PeopleErrorResponse.builder()
                .message("Person with current id does not exist in the database")
                .timestamp(System.currentTimeMillis()).build(), HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    private ResponseEntity<PeopleErrorResponse> handleException(PersonNotCreateException e) {
        return new ResponseEntity<>(PeopleErrorResponse.builder()
                .message(e.getMessage())
                .timestamp(System.currentTimeMillis()).build(), HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    private ResponseEntity<AccountErrorResponse> handleException(AccountNotCreateException e) {
        return new ResponseEntity<>(AccountErrorResponse.builder()
                .message(e.getMessage())
                .timestamp(System.currentTimeMillis()).build(), HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    private ResponseEntity<AccountErrorResponse> handleException(AccountChangeBalanceException e) {
        return new ResponseEntity<>(AccountErrorResponse.builder()
                .message("The balance cannot be negative")
                .timestamp(System.currentTimeMillis()).build(), HttpStatus.NOT_ACCEPTABLE);
    }
}
