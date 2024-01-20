package ru.example.BankCard.mapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import ru.example.BankCard.dto.AccountChangeBalanceDto;
import ru.example.BankCard.entity.Account;
import ru.example.BankCard.mapperService.AccountChangeBalanceMapperService;
@Mapper(componentModel = "spring")
public abstract class AccountChangeBalanceMapperUsingInjectedService {
        @Autowired
        protected AccountChangeBalanceMapperService accountChangeBalanceMapperService;
        @Mapping(target = "owner",
                expression = "java(accountChangeBalanceMapperService.ownerById(source.getPerson_id()))")
        public abstract Account toModel(AccountChangeBalanceDto source);
        public abstract AccountChangeBalanceDto ToDto(Account destination);
}