package ru.example.BankCard.mapperService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.example.BankCard.entity.Person;
import ru.example.BankCard.repository.PeopleRepository;

@RequiredArgsConstructor
@Service
public class AccountChangeBalanceMapperService {
    private final PeopleRepository peopleRepository;
    public Person ownerById(Integer person_id) {
        return peopleRepository.findById(person_id).get();
    }
}
