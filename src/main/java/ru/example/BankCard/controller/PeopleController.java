package ru.example.BankCard.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.example.BankCard.dto.AccountDto;
import ru.example.BankCard.service.PeopleService;
import ru.example.BankCard.dto.PersonDto;
import ru.example.BankCard.util.PeopleErrorResponse;
import ru.example.BankCard.util.PersonNotCreateException;
import ru.example.BankCard.util.PersonNotFoundException;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/people")
public class PeopleController {

    private final PeopleService peopleService;

//    @Autowired // Создаётся Lombok`ом
//    public PeopleController(PeopleService peopleService) {
//        this.peopleService = peopleService;
//    }

    @GetMapping()
    public List<PersonDto> getPeople() {
        return peopleService.findAll();
    }

    // API 1 по ТЗ: Человек по id. Если нет нашёл - читаемы эксепш.
    @GetMapping("/{id}")
    public PersonDto getPerson(@PathVariable("id") int id) {
        return peopleService.findOne(id);
    }

    // API 2 по ТЗ. Выводит инфо о всех картах человека по id (сортирует по возрастанию баланса). Пока не знаю как в вывод добавить ФИО в начало TODO
    @GetMapping("/{id}/cards")
    public List<AccountDto> showCards(@PathVariable("id") int id) {
        return peopleService.getCardsByPersonId(id);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid PersonDto personDTO,
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
