package ru.example.BankCard.service;

import ru.example.BankCard.dto.AccountDto;
import ru.example.BankCard.dto.PersonDto;

import java.util.List;

public interface PeopleService {

    List<PersonDto> findAll();

    PersonDto findOne (int id);

    PersonDto save(PersonDto personDTO);

    List<AccountDto> getCardsByPersonId(int id);



}
