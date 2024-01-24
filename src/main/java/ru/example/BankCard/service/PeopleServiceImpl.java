package ru.example.BankCard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import ru.example.BankCard.dto.PersonDto;

import ru.example.BankCard.dto.PersonSaveDto;
import ru.example.BankCard.dto.ShowCardsDto;
import ru.example.BankCard.entity.Account;
import ru.example.BankCard.mapper.PersonMapper;


import ru.example.BankCard.mapper.PersonSaveMapper;
import ru.example.BankCard.mapper.ShowCardsMapper;
import ru.example.BankCard.repository.PeopleRepository;
import ru.example.BankCard.entity.Person;
import ru.example.BankCard.exception.PersonNotFoundException;
import java.util.*;
@RequiredArgsConstructor
@Service
public class PeopleServiceImpl implements PeopleService {
    private final PeopleRepository peopleRepository;
    private final PersonMapper personMapper;
    private final PersonSaveMapper personSaveMapper;
    private final ShowCardsMapper showCardsMapper;
    @Override
    public List<PersonDto> findAll() {  // TODO Подумать над лучшей реализацией. Через Stream
        List<PersonDto> listPersonDTO = new ArrayList<>();
        List<Person> listPerson = peopleRepository.findAll();
        listPerson.forEach(element -> listPersonDTO.add(personMapper.map(element)));
        return listPersonDTO;
    }
    @Override
    public PersonDto findOne(int id) {
        Optional<Person> foundPerson = peopleRepository.findById(id);
        return personMapper.map(foundPerson.orElseThrow(PersonNotFoundException::new));
    }
    @Override
    public PersonSaveDto save(PersonSaveDto personSaveDto) {
       return personSaveMapper.map(peopleRepository.save(personSaveMapper.map(personSaveDto)));
    }
    @Override
    public ShowCardsDto getCardsByPersonId(int id) { // TODO. Забыл про что это. Спросить у Богдана: что ему тут не понравилось
        Optional<Person> person = peopleRepository.findById(id);
        if(person.isEmpty())
            return null;
        List<Account> accountList = person.get().getAccounts();
        accountList.sort(Comparator.comparing(Account::getBalance));
        person.get().setAccounts(accountList);
        return showCardsMapper.map(person.get());
    }
}
