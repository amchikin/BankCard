package ru.example.BankCard.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.example.BankCard.Entity.Person;

public interface PeopleRepository extends JpaRepository<Person, Integer> {
}
