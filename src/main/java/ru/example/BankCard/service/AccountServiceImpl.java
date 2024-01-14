package ru.example.BankCard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.example.BankCard.dto.AccountDto;
import ru.example.BankCard.entity.Account;
import ru.example.BankCard.mapper.AccountMapper;
import ru.example.BankCard.repository.AccountsRepository;
import ru.example.BankCard.repository.PeopleRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService{
    private final AccountsRepository accountsRepository;
    private final AccountMapper accountMapper;

    @Autowired
    public AccountServiceImpl(AccountsRepository accountsRepository, PeopleRepository peopleRepository, AccountMapper accountMapper) {
        this.accountsRepository = accountsRepository;
        this.accountMapper = accountMapper;
    }

    @Override
    public List<AccountDto> findAll() {  // TODO Подумать над лучшей реализацией
        List<AccountDto> listAccountDTO = new ArrayList<>();
        List<Account> listAccount = accountsRepository.findAll();
        listAccount.forEach(element -> listAccountDTO.add(accountMapper.toDto(element)));
        return listAccountDTO;
    }


}
