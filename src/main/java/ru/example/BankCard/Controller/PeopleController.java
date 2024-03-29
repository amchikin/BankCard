package ru.example.BankCard.Controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.example.BankCard.DTO.AccountDTO;
import ru.example.BankCard.Service.PeopleService;
import ru.example.BankCard.DTO.PersonDTO;
import ru.example.BankCard.Util.PeopleErrorResponse;
import ru.example.BankCard.Util.PersonNotCreateException;
import ru.example.BankCard.Util.PersonNotFoundException;

import java.util.List;

@RestController
@RequestMapping("/people")
public class PeopleController {

    private final PeopleService peopleService;

    @Autowired
    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @GetMapping()
    public List<PersonDTO> getPeople() {
        return peopleService.findAll();
    }

    // API 1 по ТЗ: Человек по id. Если нет нашёл - читаемы эксепш.
    @GetMapping("/{id}")
    public PersonDTO getPerson(@PathVariable("id") int id) {
        return peopleService.findOne(id);
    }

    // API 2 по ТЗ. Выводит инфо о всех картах человека по id (сортирует по возрастанию баланса). Пока не знаю как в вывод добавить ФИО в начало TODO
    @GetMapping("/{id}/cards")
    public List<AccountDTO> showCards(@PathVariable("id") int id) {
        return peopleService.getCardsByPersonId(id);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid PersonDTO personDTO,
                                             BindingResult bindingResult) {
        CreateErrors(bindingResult);
        peopleService.save(personDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }


    /////////////////////////////////////////////////////////
   // Обработки ошибок - Куда их?
    @ExceptionHandler
    private ResponseEntity<PeopleErrorResponse> handleException(PersonNotFoundException e) {
        PeopleErrorResponse response = new PeopleErrorResponse(
          "Person with current id does not exist in the database", System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler // метод который, ловит исключения и возвращает необходимый объект
    private ResponseEntity<PeopleErrorResponse> handleException(PersonNotCreateException e) {
        PeopleErrorResponse response = new PeopleErrorResponse(  // ResponseEntity - ?
                e.getMessage(), System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST); // NOT_FOUND - status 404
    }

    void CreateErrors(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for(FieldError error: errors){
                errorMsg.append(error.getField())
                        .append(" - ").append(error.getDefaultMessage())
                        .append(";");
            }
            throw new PersonNotCreateException(errorMsg.toString());
        }
    }
    // Обработки ошибок - Куда их?
    /////////////////////////////////////////////////////////

}
