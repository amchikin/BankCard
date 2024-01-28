package ru.example.BankCard.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.example.BankCard.dto.PersonSaveDto;
import ru.example.BankCard.dto.ShowCardsDto;
import ru.example.BankCard.service.PeopleService;
import ru.example.BankCard.dto.PersonDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/people")
public class PeopleController {
    private final PeopleService peopleService;

    @GetMapping()
    public List<PersonDto> showAllPerson() {
        return peopleService.getPersonList();
    }

    @PostMapping()
    public String createPerson(@RequestBody @Valid PersonSaveDto personSaveDto) {
        personSaveDto = peopleService.savePersonRqDto(personSaveDto);
        return String.format("Person with id %d has been created.", personSaveDto.getId());
    }

    /**
     * Get person by id. If not found, a readable exception.
     */
    @GetMapping("/{id}")
    public PersonDto showPerson(@PathVariable("id") Integer id) {
        return peopleService.getPersonById(id);
    }

    /**
     * Displays information about all cards of a person by id (sorts by ascending balance).
     */
    @GetMapping("/{id}/cards")
    public ShowCardsDto showCards(@PathVariable("id") Integer id) {
        return peopleService.getCardsByPersonId(id);
    }
}
