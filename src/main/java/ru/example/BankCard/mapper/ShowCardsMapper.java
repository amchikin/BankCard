package ru.example.BankCard.mapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.example.BankCard.dto.ShowCardsDto;
import ru.example.BankCard.entity.Person;
@Mapper(componentModel = "spring", uses = {AccountMapper.class})
public interface ShowCardsMapper {
    Person map(ShowCardsDto source);
    @Mapping(target = "fio",  source = ".", qualifiedByName = "fullName")
    ShowCardsDto map(Person destination);
    @Named("fullName")
    default String fullName(Person destination) {
        return destination.getName() + " " + destination.getSurname();
    }
}
