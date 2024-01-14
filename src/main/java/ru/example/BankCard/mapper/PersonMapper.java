package ru.example.BankCard.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.example.BankCard.dto.PersonDto;
import ru.example.BankCard.entity.Person;

@Component
public class PersonMapper extends AbstractMapper<Person, PersonDto>{
    @Autowired
    public PersonMapper() {  // TODO разобраться: подчёркивает, но работает.
        super(Person.class, PersonDto.class);
    }
}
