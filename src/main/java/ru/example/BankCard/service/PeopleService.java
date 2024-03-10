package ru.example.BankCard.service;

import ru.example.BankCard.dto.PersonDto;
import ru.example.BankCard.dto.PersonSaveRequestDto;
import ru.example.BankCard.dto.PersonSaveResponseDto;
import ru.example.BankCard.dto.ShowCardsDto;

import java.util.List;

public interface PeopleService {
    List<PersonDto> getPersonDtoList();

    PersonDto getPersonByIdOrThrow(Integer id);

    PersonSaveResponseDto savePersonRequestDto(PersonSaveRequestDto personSaveRequestDto);

    ShowCardsDto getCardsByPersonId(Integer id);
}
