package ru.example.BankCard.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigInteger;

@Data
@Entity
@Table(name = "account")
public class Account {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Поле номера карты не должно быть пустым")
    @Size(min = 16, max = 16, message = "Размер номера карты ровно 16")
    @Column(name = "card_number")
    private String cardNumber;

    @Min(value = 0, message = "Баланс не может быть отрицательным")
    @Column(name = "balance")
    private BigInteger balance;

    @Column(name = "salary_status")
    private Boolean isSalary;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person owner;
}




