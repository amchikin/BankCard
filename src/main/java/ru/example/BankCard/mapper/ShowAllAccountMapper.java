package ru.example.BankCard.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.example.BankCard.dto.ShowAllAccountDto;
import ru.example.BankCard.entity.Account;

@Mapper(componentModel = "spring")
public interface ShowAllAccountMapper {

    Account map(ShowAllAccountDto showAllAccountDto);

    @Mapping(target = "isSalary", source = "isSalary")
    @Mapping(target = "personId", source = "owner.id")
    ShowAllAccountDto map(Account account);
}
