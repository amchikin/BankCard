package ru.example.BankCard.mapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.example.BankCard.dto.AccountSaveDto;
import ru.example.BankCard.entity.Account;
@Mapper(componentModel = "spring")
public interface AccountSaveMapper {
    @Mapping(target = "owner.id", source = "personId")
    Account map(AccountSaveDto accountSaveDto);
    AccountSaveDto map(Account account);
}