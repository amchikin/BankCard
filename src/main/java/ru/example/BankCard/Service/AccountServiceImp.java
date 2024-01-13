package ru.example.BankCard.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.example.BankCard.DTO.AccountDTO;
import ru.example.BankCard.DTO.PersonDTO;
import ru.example.BankCard.Entity.Account;
import ru.example.BankCard.Entity.Person;
import ru.example.BankCard.Mapper.AccountMapper;
import ru.example.BankCard.Repository.AccountsRepository;
import ru.example.BankCard.Repository.PeopleRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountServiceImp implements AccountService{
    private final AccountsRepository accountsRepository;
    private final AccountMapper accountMapper;

    @Autowired
    public AccountServiceImp(AccountsRepository accountsRepository, PeopleRepository peopleRepository, AccountMapper accountMapper) {
        this.accountsRepository = accountsRepository;
        this.accountMapper = accountMapper;
    }

    @Override
    public List<AccountDTO> findAll() {  // TODO Подумать над лучшей реализацией
        List<AccountDTO> listAccountDTO = new ArrayList<>();
        List<Account> listAccount = accountsRepository.findAll();
        listAccount.forEach(element -> listAccountDTO.add(accountMapper.toDto(element)));
        return listAccountDTO;
    }


}
