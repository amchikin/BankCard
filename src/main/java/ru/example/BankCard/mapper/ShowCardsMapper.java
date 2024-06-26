package ru.example.BankCard.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.example.BankCard.dto.ShowCardsDto;
import ru.example.BankCard.entity.Person;

//@Mapper(componentModel = "spring", uses = {ShowAllAccountMapper.class}) //TODO Спросить!!! Если это раскоммитить, то тест проходить не будет. Сейчас есть null при выводе.
@Mapper(componentModel = "spring")
public interface ShowCardsMapper {
    Person map(ShowCardsDto showCardsDto);

    @Mapping(target = "fio", source = "person", qualifiedByName = "fullName")
    ShowCardsDto map(Person person);

    @Named("fullName")
    default String fullName(Person person) {
        return String.format("%s %s", person.getName(), person.getSurname());
    }
}

