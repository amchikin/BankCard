package ru.example.BankCard.mapper;

import org.mapstruct.Mapper;
import ru.example.BankCard.dto.AccountDto;
import ru.example.BankCard.entity.Account;


@Mapper(componentModel = "spring")

public interface AccountMapper {
    Account sourceToDestination(AccountDto source);
    AccountDto destinationToSource(Account destination);
}