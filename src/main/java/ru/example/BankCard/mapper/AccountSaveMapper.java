package ru.example.BankCard.mapper;
import org.mapstruct.Mapper;
import ru.example.BankCard.dto.AccountSaveDto;
import ru.example.BankCard.entity.Account;
@Mapper(componentModel = "spring")
public interface AccountSaveMapper {
    Account toModel(AccountSaveDto source);
    AccountSaveDto toDto(Account destination);
}