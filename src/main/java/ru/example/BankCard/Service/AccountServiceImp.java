package ru.example.BankCard.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.example.BankCard.Repository.AccountsRepository;
import ru.example.BankCard.Repository.PeopleRepository;

@Service
public class
AccountServiceImp {
    private final AccountsRepository accountsRepository;

    @Autowired
    public AccountServiceImp(AccountsRepository accountsRepository, PeopleRepository peopleRepository) {
        this.accountsRepository = accountsRepository;
    }


}
