package ru.example.BankCard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.example.BankCard.entity.Person;

public interface PeopleRepository extends JpaRepository<Person, Integer> {
    //Person findById(Integer id); //
    /**
     * TODO добавить findById.
     * Зачем?
     * Богдан говорил сделать репо-слой для обработки Optional, но непослушный Сашка сделал по-своему - выкинул исключение в сервисе - уточнить.
     */
}
