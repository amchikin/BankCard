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
import ru.example.BankCard.exception.NotFoundException;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PeopleServiceImpl implements PeopleService {
    private final PeopleRepository peopleRepository;
    private final PersonMapper personMapper;
    private final PersonSaveMapper personSaveMapper;
    private final ShowCardsMapper showCardsMapper;

    @Override
    public List<PersonDto> getPersonList() {
        return peopleRepository.findAll().stream().map(personMapper::map).collect(Collectors.toList());
    }

    @Override
    public PersonDto getPersonByIdOrThrow(Integer id) {
        return personMapper.map(peopleRepository.findById(id).
                orElseThrow(() ->
                        new NotFoundException(
                                String.format("Person with id %d does not exist in the database.", id))));
    }

    @Override
    public PersonSaveDto savePersonRqDto(PersonSaveDto personSaveDto) {
        return personSaveMapper.map(peopleRepository.
                save(personSaveMapper.map(personSaveDto)));
    }

    @Override
    public ShowCardsDto getCardsByPersonId(Integer id) {
        Person person = peopleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        String.format("Person with id %d does not exist in the database.", id)));
        if (person.getAccounts() != null) {
            person.setAccounts(person.getAccounts().stream().sorted(Comparator.comparing(Account::getBalance)).collect(Collectors.toList()));
            return showCardsMapper.map(person);
        }
        return null;
    }
}
