package ru.example.BankCard.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.example.BankCard.dto.AccountChangeBalanceRequestDto;
import ru.example.BankCard.entity.Account;

@Mapper(componentModel = "spring")
public interface AccountChangeBalanceRequestMapper {
    @Mapping(target = "balance", source = "value")
    @Mapping(target = "owner.id", source = "personId")
    Account map(AccountChangeBalanceRequestDto accountChangeBalanceRequestDto);

    AccountChangeBalanceRequestDto map(Account account);
}
