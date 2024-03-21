package ru.example.BankCard.service;

import ru.example.BankCard.dto.*;

import java.util.List;

public interface PeopleService {
    List<PersonDto> getPersonDtoList();

    PersonDto getPersonByIdOrThrow(Integer id);

    PersonSaveResponseDto savePersonRequestDto(PersonSaveRequestDto personSaveRequestDto);

    ShowCardsDto getCardsByPersonId(Integer id);
    UpdatePersonDto updatePersonById(UpdatePersonDto updatePersonDto, Integer id);
}
