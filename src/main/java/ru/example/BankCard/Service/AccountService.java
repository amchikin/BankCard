package ru.example.BankCard.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.example.BankCard.Repository.AccountsRepository;
import ru.example.BankCard.Repository.PeopleRepository;

@Service
public class AccountService {
    private final AccountsRepository accountsRepository;

    @Autowired
    public AccountService(AccountsRepository accountsRepository, PeopleRepository peopleRepository) {
        this.accountsRepository = accountsRepository;
    }


}
