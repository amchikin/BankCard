package ru.example.BankCard.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
//@Builder
@Data
//@NoArgsConstructor
//@AllArgsConstructor
@Entity
@Table(name="Account")
public class Account {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "Поле номера карты не должно быть пустым")
    @Size(min = 16, max = 16, message = "Размер номера карты ровно 16")
    @Column(name = "card_number")
    private String card_number;

    @Min(value = 0, message = "Баланс не может быть отрицательным")
    @Column(name = "balance")
    private BigInteger balance;

    @Column(name="issalary")
    private Boolean isSalary;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person owner;

    //public Account(){}
}




