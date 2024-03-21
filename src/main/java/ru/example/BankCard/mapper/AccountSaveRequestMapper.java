package ru.example.BankCard.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.example.BankCard.dto.AccountSaveRequestDto;
import ru.example.BankCard.entity.Account;

@Mapper(componentModel = "spring")
public interface AccountSaveRequestMapper {
    @Mapping(target = "owner.id", source = "personId")
    Account map(AccountSaveRequestDto accountSaveRequestDto);

    AccountSaveRequestDto map(Account account);
}