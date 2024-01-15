package ru.example.BankCard.mapper;

import org.mapstruct.Mapper;
import ru.example.BankCard.dto.PersonDto;
import ru.example.BankCard.entity.Person;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    Person sourceToDestination(PersonDto source);
    PersonDto destinationToSource(Person destination);
}
