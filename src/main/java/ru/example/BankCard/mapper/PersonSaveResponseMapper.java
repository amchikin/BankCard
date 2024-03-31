package ru.example.BankCard.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.example.BankCard.dto.PersonSaveResponseDto;
import ru.example.BankCard.entity.Person;

@Mapper(componentModel = "spring")
public interface PersonSaveResponseMapper {
    Person map(PersonSaveResponseDto personSaveResponseDto);

    @Mapping(target = "id", source = "id")
    PersonSaveResponseDto map(Person person);
}
