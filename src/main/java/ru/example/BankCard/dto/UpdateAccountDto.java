package ru.example.BankCard.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigInteger;

@Data
public class UpdateAccountDto {

    @NotEmpty(message = "Поле номера карты не должно быть пустым")
    @Size(min = 16, max = 16, message = "Размер номера карты ровно 16")
    @Column(name = "card_number")
    private String cardNumber;

    @Min(value = 0, message = "Баланс не может быть отрицательным")
    @Column(name = "balance")
    private BigInteger balance;

    @Column(name = "salary_status")
    private Boolean isSalary;
}
