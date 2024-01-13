package ru.example.BankCard.Mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.example.BankCard.DTO.PersonDTO;
import ru.example.BankCard.Entity.Person;

@Component
public class PersonMapper extends AbstractMapper<Person, PersonDTO>{
    @Autowired
    public PersonMapper() {  // TODO разобраться: подчёркивает, но работает.
        super(Person.class, PersonDTO.class);
    }
}
