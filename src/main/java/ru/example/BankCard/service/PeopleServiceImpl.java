package ru.example.BankCard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import ru.example.BankCard.dto.*;

import ru.example.BankCard.entity.Account;
import ru.example.BankCard.mapper.*;


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
    private final PersonSaveRequestMapper personSaveRequestMapper;
    private final PersonSaveResponseMapper personSaveResponseMapper;
    private final ShowCardsMapper showCardsMapper;

    @Override
    public List<PersonDto> getPersonDtoList() {
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
    public PersonSaveResponseDto savePersonRequestDto(PersonSaveRequestDto personSaveRequestDto) {
        return personSaveResponseMapper.map(peopleRepository.
                save(personSaveRequestMapper.map(personSaveRequestDto)));
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

    @Override
    public UpdatePersonDto updatePersonById(UpdatePersonDto updatePersonDto, Integer id) {
        Person person = peopleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        String.format("Person with id %d does not exist in the database.", id)));
        person.setSurname(updatePersonDto.getSurname());
        person.setName(updatePersonDto.getName());
        person.setBirthday(updatePersonDto.getBirthday());
        peopleRepository.save(person);
        return updatePersonDto;
    }
}
