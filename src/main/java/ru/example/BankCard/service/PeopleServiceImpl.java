package ru.example.BankCard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import ru.example.BankCard.dto.PersonDto;

import ru.example.BankCard.dto.ShowCardsResponseDto;
import ru.example.BankCard.entity.Account;
import ru.example.BankCard.mapper.PersonMapper;


import ru.example.BankCard.mapper.ShowCardsResponseMapperInjectService;
import ru.example.BankCard.repository.PeopleRepository;
import ru.example.BankCard.entity.Person;
import ru.example.BankCard.exception.PersonNotFoundException;

import java.util.*;

@RequiredArgsConstructor
@Service
public class PeopleServiceImpl implements PeopleService {
    private final PeopleRepository peopleRepository;
    private final PersonMapper personMapper;
    private final ShowCardsResponseMapperInjectService showCardsResponseMapperInjectService;

    @Override
    public List<PersonDto> findAll() {  // TODO Подумать над лучшей реализацией. Через Stream
        List<PersonDto> listPersonDTO = new ArrayList<>();
        List<Person> listPerson = peopleRepository.findAll();
        listPerson.forEach(element -> listPersonDTO.add(personMapper.toDto(element)));
        return listPersonDTO;
    }

    @Override
    public PersonDto findOne(int id) {
        Optional<Person> foundPerson = peopleRepository.findById(id);
        return personMapper.toDto(foundPerson.orElseThrow(PersonNotFoundException::new));
    }

    @Override
    public PersonDto save(PersonDto personDTO) {
       return personMapper.toDto(peopleRepository.save(personMapper.toModel(personDTO)));
    }

    @Override
    public ShowCardsResponseDto getCardsByPersonId(int id) { // TODO
        Optional<Person> person = peopleRepository.findById(id);
        if(person.isEmpty())
            return null;
        List<Account> accountList = person.get().getAccounts();
        accountList.sort(Comparator.comparing(Account::getBalance));
        person.get().setAccounts(accountList);
        return showCardsResponseMapperInjectService.ToDto(person.get());
    }
}
