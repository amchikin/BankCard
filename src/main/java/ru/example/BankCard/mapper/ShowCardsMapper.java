package ru.example.BankCard.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.example.BankCard.dto.ShowCardsDto;
import ru.example.BankCard.entity.Person;

@Mapper(componentModel = "spring", uses = {AccountMapper.class}) //TODO Check without it
public interface ShowCardsMapper {
    Person map(ShowCardsDto showCardsDto);

    @Mapping(target = "fio", source = "person", qualifiedByName = "fullName")
    ShowCardsDto map(Person person);

    @Named("fullName")
    default String fullName(Person person) {
        return String.format("%s %s", person.getName(), person.getSurname());
    }
}

