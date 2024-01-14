package ru.example.BankCard.Service;

import ru.example.BankCard.DTO.AccountDTO;
import ru.example.BankCard.DTO.PersonDTO;

import java.util.List;

/*
Интерфейс для работы с сущностью People
 */
public interface PeopleService {

    List<PersonDTO> findAll();

    PersonDTO findOne (int id);

    PersonDTO save(PersonDTO personDTO);

    List<AccountDTO> getCardsByPersonId(int id);



}
