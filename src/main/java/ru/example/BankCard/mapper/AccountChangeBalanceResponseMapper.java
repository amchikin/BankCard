package ru.example.BankCard.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.example.BankCard.dto.AccountChangeBalanceResponseDto;
import ru.example.BankCard.entity.Account;

@Mapper(componentModel = "spring")
public interface AccountChangeBalanceResponseMapper {
    Account map(AccountChangeBalanceResponseDto accountChangeBalanceResponseDto);

    @Mapping(target = "personId", source = "owner.id")
    AccountChangeBalanceResponseDto map(Account account);
}
