package ru.example.BankCard.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    private ResponseEntity<ErrorMessage> resourceNotFoundException(NotFoundException ex) {
        ErrorMessage message = new ErrorMessage(
                NOT_FOUND.value(),
                new Date(),
                ex.getMessage()
        );
        return ResponseEntity.status(NOT_FOUND).body(message);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> globalExceptionHandler(Exception ex) {
        ErrorMessage message = new ErrorMessage(
                INTERNAL_SERVER_ERROR.value(),
                new Date(),
                ex.getMessage()
        );

        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(message);
    }

    @ExceptionHandler(AccountChangeBalanceException.class)
    private ResponseEntity<ErrorMessage> resourceAccountChangeBalanceException(AccountChangeBalanceException ex) {
        ErrorMessage message = new ErrorMessage(
                NOT_FOUND.value(),
                new Date(),
                ex.getMessage()
        );
        return ResponseEntity.status(NOT_FOUND).body(message);
    }
}
