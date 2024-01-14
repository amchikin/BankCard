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
import ru.example.BankCard.exception.PeopleErrorResponse;
import ru.example.BankCard.exception.PersonNotCreateException;
import ru.example.BankCard.exception.PersonNotFoundException;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/people")
public class PeopleController {

    private final PeopleService peopleService; // Поле внедряется через конструктор авто средствами Lombok

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
        PeopleErrorResponse.CreateErrors(bindingResult);
        peopleService.save(personDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
