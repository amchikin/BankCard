package ru.example.BankCard.mapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.example.BankCard.dto.AccountSaveDto;
import ru.example.BankCard.entity.Account;
@Mapper(componentModel = "spring")
public interface AccountSaveMapper {

    @Mapping(target = "owner.id", source = "person_id")
    Account map(AccountSaveDto source);
    AccountSaveDto map(Account destination);
}