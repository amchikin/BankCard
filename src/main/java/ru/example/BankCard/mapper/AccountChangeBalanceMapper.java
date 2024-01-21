package ru.example.BankCard.mapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.example.BankCard.dto.AccountChangeBalanceDto;
import ru.example.BankCard.entity.Account;
@Mapper(componentModel = "spring")
public interface AccountChangeBalanceMapper {
    @Mapping(target = "balance", source = "value")
    //@Mapping(target = "owner.id", source = "person_id") // TODO personId rename
    Account toModel(AccountChangeBalanceDto source); // TODO toModel -> map
    AccountChangeBalanceDto toDto(Account destination);
}
