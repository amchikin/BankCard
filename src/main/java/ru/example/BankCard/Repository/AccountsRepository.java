package ru.example.BankCard.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.example.BankCard.Entity.Account;

public interface AccountsRepository extends JpaRepository<Account, Integer> {
}
