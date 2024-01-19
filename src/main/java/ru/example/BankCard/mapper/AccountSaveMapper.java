package ru.example.BankCard.mapper;

import org.mapstruct.Mapper;
import ru.example.BankCard.dto.AccountSaveDto;
import ru.example.BankCard.dto.ShowCardsResponseDto;
import ru.example.BankCard.entity.Account;
import ru.example.BankCard.entity.Person;
import ru.example.BankCard.repository.PeopleRepository;

import java.math.BigInteger;

@Mapper(componentModel = "spring")
public interface AccountSaveMapper {
    Account toModel(AccountSaveDto source);
    AccountSaveDto toDto(Account destination);
}
//abstract class AccountSaveMapper {
//    private final PeopleRepository peopleRepository;
//
//    AccountSaveMapper(PeopleRepository peopleRepository) {
//        this.peopleRepository = peopleRepository;
//    }
//
//    public Account toModel(AccountSaveDto accountSaveDto){
//        Account account = new Account();
//        account.setCard_number(accountSaveDto.getCard_number());
//        account.setBalance(accountSaveDto.getBalance());
//        account.setIsSalary(accountSaveDto.getIsSalary());
//        account.setOwner(peopleRepository.findById(accountSaveDto.getPerson_id()).get());
//        return account;
//
//   }
//
//    public AccountSaveDto toTransactionDTO(Account account) {
//        AccountSaveDto accountSaveDto = new AccountSaveDto();
//        accountSaveDto.setCard_number(account.getCard_number());
//
//        return accountSaveDto;
//    }
//
//}

