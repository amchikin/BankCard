package ru.example.BankCard.mapper;

import org.mapstruct.Mapper;
import ru.example.BankCard.dto.PersonSaveDto;
import ru.example.BankCard.entity.Person;

@Mapper(componentModel = "spring") //TODO Check without it
public interface PersonSaveMapper {
    Person map(PersonSaveDto personSaveDto);

    PersonSaveDto map(Person person);
}
