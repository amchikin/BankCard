package ru.example.BankCard.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.example.BankCard.exception.PeopleErrorResponse;
import ru.example.BankCard.exception.PersonNotCreateException;
import ru.example.BankCard.exception.PersonNotFoundException;


@ControllerAdvice
public class Advice {

    @ExceptionHandler  // метод который, ловит исключения и возвращает необходимый объект
    private ResponseEntity<PeopleErrorResponse> handleException(PersonNotFoundException e) {
        PeopleErrorResponse response = new PeopleErrorResponse(
                "Person with current id does not exist in the database", System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    private ResponseEntity<PeopleErrorResponse> handleException(PersonNotCreateException e) {
        PeopleErrorResponse response = new PeopleErrorResponse(  //
                e.getMessage(), System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST); // NOT_FOUND - status 404
    }

}
