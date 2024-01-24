package ru.example.BankCard.controller;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.example.BankCard.dto.ShowCardsDto;
import ru.example.BankCard.service.PeopleService;
import ru.example.BankCard.dto.PersonDto;
import ru.example.BankCard.exception.PeopleErrorResponse;
import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping("/people")
public class PeopleController {
    private final PeopleService peopleService;
    @GetMapping()
    public List<PersonDto> getPeople() {
        return peopleService.findAll();
    }
    @PostMapping()
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid PersonDto personDTO,
                                             BindingResult bindingResult) {
        PeopleErrorResponse.CreateErrors(bindingResult);
        peopleService.save(personDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }
     /**
     * Get person by id. If not found, a readable exception.
     */
    @GetMapping("/{id}")
    public PersonDto getPerson(@PathVariable("id") int id) {
        return peopleService.findOne(id);
    }

    /**
     *  Displays information about all cards of a person by id (sorts by ascending balance).
     */
    @GetMapping("/{id}/cards")
    public ShowCardsDto showCards(@PathVariable("id") int id) {
        return peopleService.getCardsByPersonId(id);
    }
}
