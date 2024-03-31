package ru.example.BankCard.mapper;

import org.mapstruct.Mapper;
import ru.example.BankCard.dto.PersonSaveRequestDto;
import ru.example.BankCard.entity.Person;

@Mapper(componentModel = "spring")
public interface PersonSaveRequestMapper {
    Person map(PersonSaveRequestDto personSaveRequestDto);

    PersonSaveRequestDto map(Person person);
}
