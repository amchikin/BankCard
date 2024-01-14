package ru.example.BankCard.service;


import org.springframework.stereotype.Service;
import ru.example.BankCard.dto.AccountDto;
import ru.example.BankCard.dto.PersonDto;
import ru.example.BankCard.entity.Account;
import ru.example.BankCard.mapper.AccountMapper;
import ru.example.BankCard.mapper.PersonMapper;
import ru.example.BankCard.repository.PeopleRepository;
import ru.example.BankCard.entity.Person;
import ru.example.BankCard.exception.PersonNotFoundException;

import java.util.*;

import static java.util.Collections.*;

@Service
public class PeopleServiceImpl implements PeopleService {
    private final PeopleRepository peopleRepository;
    private final PersonMapper personMapper;
    private final AccountMapper accountMapper;

    public PeopleServiceImpl(PeopleRepository peopleRepository, PersonMapper personMapper, AccountMapper accountMapper) {
        this.peopleRepository = peopleRepository;
        this.personMapper = personMapper;
        this.accountMapper = accountMapper;
    }


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
       return personMapper.toDto(peopleRepository.save(personMapper.toEntity(personDTO)));
    }

    @Override
    public List<AccountDto> getCardsByPersonId(int id) {
        Optional<Person> person = peopleRepository.findById(id);
        if (person.isPresent()) {
            List<Account> accountList = person.get().getAccounts();
            accountList.sort((o1, o2) -> o1.getBalance().compareTo(o2.getBalance()));
            List<AccountDto> listAccountDTO = new ArrayList<>();
            accountList.forEach(element -> listAccountDTO.add(accountMapper.toDto(element)));
            return listAccountDTO;
        }
        else {
            return emptyList();
        }
    }



}
