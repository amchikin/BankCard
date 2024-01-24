package ru.example.BankCard.mapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.example.BankCard.dto.AccountChangeBalanceDto;
import ru.example.BankCard.entity.Account;
@Mapper(componentModel = "spring")
public interface AccountChangeBalanceMapper {
    @Mapping(target = "balance", source = "value")
    @Mapping(target = "owner.id", source = "personId")
    Account map(AccountChangeBalanceDto accountChangeBalanceDto);
    AccountChangeBalanceDto map(Account account);
}
