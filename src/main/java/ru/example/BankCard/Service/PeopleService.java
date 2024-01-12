package ru.example.BankCard.Service;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.example.BankCard.Entity.Account;
import ru.example.BankCard.Repository.PeopleRepository;
import ru.example.BankCard.Entity.Person;
import ru.example.BankCard.Util.PersonNotFoundException;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class PeopleService {
    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @Transactional(readOnly = true)
    public List<Person> findAll() {
        return peopleRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Person findOne (int id) {
        Optional<Person> foundPerson = peopleRepository.findById(id);
        return foundPerson.orElseThrow(PersonNotFoundException::new);
    }

    @Transactional
    public void save(Person person) {
       peopleRepository.save(person);
    }

    @Transactional(readOnly = true)
    public List<Account> getCardsByPersonId(int id) {
        Optional<Person> person = peopleRepository.findById(id);
        if (person.isPresent()) {
            //Hibernate.initialize(person.get().getAccounts()); // Или нужен? Без него работает.
            List<Account> accountList = person.get().getAccounts();
            Collections.sort(accountList, new Comparator<Account>() {
                @Override
                public int compare(Account o1, Account o2) {
                    return o1.getBalance().compareTo(o2.getBalance());
                }
            });
            return accountList;
        }
        else {
            return Collections.emptyList();
        }
    }


}
