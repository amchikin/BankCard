package ru.example.BankCard.mapperService;

import org.springframework.stereotype.Service;
import ru.example.BankCard.entity.Person;
import ru.example.BankCard.repository.PeopleRepository;

@Service
public class AccountSaveMapperService {
    private final PeopleRepository peopleRepository;

    public AccountSaveMapperService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public Person ownerById(Integer person_id) {
        return peopleRepository.findById(person_id).get();

    }
}
