package ru.example.BankCard.Service;


import org.springframework.stereotype.Service;
import ru.example.BankCard.DTO.AccountDTO;
import ru.example.BankCard.DTO.PersonDTO;
import ru.example.BankCard.Entity.Account;
import ru.example.BankCard.Mapper.AccountMapper;
import ru.example.BankCard.Mapper.PersonMapper;
import ru.example.BankCard.Repository.PeopleRepository;
import ru.example.BankCard.Entity.Person;
import ru.example.BankCard.Util.PersonNotFoundException;

import java.util.*;

import static java.util.Collections.*;

@Service
public class PeopleServiceImp implements PeopleService {
    private final PeopleRepository peopleRepository;
    private final PersonMapper personMapper;
    private final AccountMapper accountMapper;

    public PeopleServiceImp(PeopleRepository peopleRepository, PersonMapper personMapper, AccountMapper accountMapper) {
        this.peopleRepository = peopleRepository;
        this.personMapper = personMapper;
        this.accountMapper = accountMapper;
    }


    @Override
    public List<PersonDTO> findAll() {  // TODO Подумать над лучшей реализацией
        List<PersonDTO> listPersonDTO = new ArrayList<>();
        List<Person> listPerson = peopleRepository.findAll();
        listPerson.forEach(element -> listPersonDTO.add(personMapper.toDto(element)));
        return listPersonDTO;
    }

    @Override
    public PersonDTO findOne(int id) {
        Optional<Person> foundPerson = peopleRepository.findById(id);
        return personMapper.toDto(foundPerson.orElseThrow(PersonNotFoundException::new));
    }

    @Override
    public PersonDTO save(PersonDTO personDTO) {
       return personMapper.toDto(peopleRepository.save(personMapper.toEntity(personDTO)));
    }

    @Override
    public List<AccountDTO> getCardsByPersonId(int id) {
        Optional<Person> person = peopleRepository.findById(id);
        if (person.isPresent()) {
            List<Account> accountList = person.get().getAccounts();
            accountList.sort((o1, o2) -> o1.getBalance().compareTo(o2.getBalance()));
            List<AccountDTO> listAccountDTO = new ArrayList<>();
            accountList.forEach(element -> listAccountDTO.add(accountMapper.toDto(element)));
            return listAccountDTO;
        }
        else {
            return emptyList();
        }
    }

}
