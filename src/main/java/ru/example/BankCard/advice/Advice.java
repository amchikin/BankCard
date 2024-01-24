package ru.example.BankCard.advice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.example.BankCard.exception.*;

@ControllerAdvice
public class Advice { //TODO rename packet & class. How? Ask from Bogdan
    @ExceptionHandler  // метод который, ловит исключения и возвращает необходимый объект
    private ResponseEntity<PeopleErrorResponse> handleException(PersonNotFoundException e) {
        PeopleErrorResponse response = PeopleErrorResponse.builder()
                .message("Person with current id does not exist in the database")
                .timestamp(System.currentTimeMillis()).build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    private ResponseEntity<PeopleErrorResponse> handleException(PersonNotCreateException e) {
        PeopleErrorResponse response = PeopleErrorResponse.builder()
                .message(e.getMessage())
                .timestamp(System.currentTimeMillis()).build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    private ResponseEntity<AccountErrorResponse> handleException(AccountNotCreateException e) {
        AccountErrorResponse response = AccountErrorResponse.builder()
                .message(e.getMessage())
                .timestamp(System.currentTimeMillis()).build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    private ResponseEntity<AccountErrorResponse> handleException(AccountChangeBalanceException e) {
        AccountErrorResponse response = AccountErrorResponse.builder()
                .message("The balance cannot be negative")
                .timestamp(System.currentTimeMillis()).build();
        return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
    }
}
