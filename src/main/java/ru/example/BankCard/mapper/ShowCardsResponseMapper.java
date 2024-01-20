package ru.example.BankCard.mapper;
import org.mapstruct.Mapper;
import ru.example.BankCard.dto.ShowCardsResponseDto;
import ru.example.BankCard.entity.Person;
@Mapper(componentModel = "spring", uses = {AccountMapper.class})
public interface ShowCardsResponseMapper {
    Person toModel(ShowCardsResponseDto source);
    ShowCardsResponseDto toDto(Person destination);
}
