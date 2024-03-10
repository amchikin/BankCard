package ru.example.BankCard.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.example.BankCard.dto.*;
import ru.example.BankCard.service.PeopleService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/people")
public class PeopleController {
    private final PeopleService peopleService;

    @GetMapping()
    public List<PersonDto> showAllPerson() {
        return peopleService.getPersonDtoList();
    }

    @PostMapping()
    public ResponseEntity<PersonSaveResponseDto> createPerson(@RequestBody @Valid PersonSaveRequestDto personSaveRequestDto) {
        return new ResponseEntity<>(peopleService.savePersonRequestDto(personSaveRequestDto), HttpStatus.CREATED);
    }

    /**
     * Get person by id. If not found, a readable exception.
     */
    @GetMapping("/{id}")
    public PersonDto showPerson(@PathVariable("id") Integer id) {
        return peopleService.getPersonByIdOrThrow(id);
    }

    /**
     * Displays information about all cards of a person by id (sorts by ascending balance).
     */
    @GetMapping("/{id}/cards")
    public ShowCardsDto showCards(@PathVariable("id") Integer id) {
        return peopleService.getCardsByPersonId(id);
    }
}
