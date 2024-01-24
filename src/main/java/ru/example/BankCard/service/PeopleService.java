package ru.example.BankCard.service;
import ru.example.BankCard.dto.PersonDto;
import ru.example.BankCard.dto.PersonSaveDto;
import ru.example.BankCard.dto.ShowCardsDto;
import java.util.List;
public interface PeopleService {
    List<PersonDto> findAll();
    PersonDto findOne (int id);
    PersonSaveDto save(PersonSaveDto personSaveDto);
    ShowCardsDto getCardsByPersonId(int id);
}
