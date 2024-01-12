package ru.example.BankCard.Controller;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.example.BankCard.DTO.AccountDTO;
import ru.example.BankCard.Entity.Account;
import ru.example.BankCard.Entity.Person;
import ru.example.BankCard.Service.PeopleService;
import ru.example.BankCard.DTO.PersonDTO;
import ru.example.BankCard.Util.PeopleErrorResponse;
import ru.example.BankCard.Util.PersonNotCreateException;
import ru.example.BankCard.Util.PersonNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/people")
public class PeopleController {

    private final PeopleService peopleService;
    private final ModelMapper modelMapper;

    @Autowired
    public PeopleController(PeopleService peopleService, ModelMapper modelMapper) {
        this.peopleService = peopleService;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public List<PersonDTO> getPeople() {
        return peopleService.findAll().stream().
                map(this::convertPersonToDTO).collect(Collectors.toList());
    }


    // API 1 по ТЗ
    @GetMapping("/{id}")  // Было @RequestMapping
    public PersonDTO getPerson(@PathVariable("id") int id) {
        return convertPersonToDTO(peopleService.findOne(id));
    }

    // API 2 по ТЗ. Выводит инфо о всех картах человека по id (сортирует по возрастанию баланса). Пока не знаю как в вывод добавить ФИО в начало TODO
    @GetMapping("/{id}/cards")
    public List<AccountDTO> showCards(@PathVariable("id") int id) {
        return peopleService.getCardsByPersonId(id).stream().map(this::convertEntityToDTO).collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid PersonDTO personDTO,
                                             BindingResult bindingResult) {
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
        peopleService.save(converDTOToPerson(personDTO));
        return ResponseEntity.ok(HttpStatus.OK);

    }




    // Не уверен, что этот метод уместен, но даже если так, то ему тут не место?
    private PersonDTO convertPersonToDTO(Person person) { return modelMapper.map(person, PersonDTO.class);
    }
    private Person converDTOToPerson(PersonDTO personDTO) {
        return modelMapper.map(personDTO, Person.class);
    }

    private AccountDTO convertEntityToDTO(Account account) {return modelMapper.map(account, AccountDTO.class);
    }

    private Account convertDTOToEntity(AccountDTO accountDTO) {return modelMapper.map(accountDTO, Account.class); }
    // Не уверен, что этот метод уместен, но даже если так, то ему тут не место?


    @ExceptionHandler
    private ResponseEntity<PeopleErrorResponse> handleException(PersonNotFoundException e) {
        PeopleErrorResponse response = new PeopleErrorResponse(
          "Person with current id wasn`t found", System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler // метод который ловит исключения и возвращает необходимый объект
    private ResponseEntity<PeopleErrorResponse> handleException(PersonNotCreateException e) {
        PeopleErrorResponse response = new PeopleErrorResponse(  // ResponseEntity - ?
                e.getMessage(), System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST); // NOT_FOUND - status 404
    }


}
