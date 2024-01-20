package ru.example.BankCard.mapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import ru.example.BankCard.dto.AccountSaveDto;
import ru.example.BankCard.entity.Account;
import ru.example.BankCard.mapperService.AccountSaveMapperService;
@Mapper(componentModel = "spring")
public abstract class AccountSaveMapperInjectService {
    @Autowired  // TODO Почему подчёркивает и как сделать лучше
    protected AccountSaveMapperService accountSaveMapperService;
    @Mapping(target = "owner",
            expression = "java(accountSaveMapperService.ownerById(source.getPerson_id()))")
    public abstract Account ToModel(AccountSaveDto source);
    public abstract AccountSaveDto ToDto(Account destination);
}
