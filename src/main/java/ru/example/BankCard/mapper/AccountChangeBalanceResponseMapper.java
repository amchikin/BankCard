package ru.example.BankCard.mapper;

import org.mapstruct.Mapper;
import ru.example.BankCard.dto.AccountChangeBalanceResponseDto;
import ru.example.BankCard.entity.Account;

@Mapper(componentModel = "spring")
public interface AccountChangeBalanceResponseMapper {
    Account map(AccountChangeBalanceResponseDto accountChangeBalanceResponseDto);

    AccountChangeBalanceResponseDto map(Account account);
}
