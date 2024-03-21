package ru.example.BankCard.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.example.BankCard.dto.AccountSaveResponseDto;
import ru.example.BankCard.entity.Account;

@Mapper(componentModel = "spring")
public interface AccountSaveResponseMapper {
    Account map(AccountSaveResponseDto accountSaveResponseDto);

    @Mapping(target = "personId", source = "owner.id")
    AccountSaveResponseDto map(Account account);
}
