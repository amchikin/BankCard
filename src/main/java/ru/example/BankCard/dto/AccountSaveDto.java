package ru.example.BankCard.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import ru.example.BankCard.entity.Person;

import java.math.BigInteger;


@Getter
@Setter
public class AccountSaveDto extends AbstractDto {

    @NotEmpty(message = "Поле номера карты не должно быть пустым")
    @Size(min = 16, max = 16, message = "Размер номера карты ровно 16")
    private String card_number;
    @Min(value = 0, message = "Баланс не может быть отрицательным")
    private BigInteger balance;

    private Boolean isSalary;

    private Integer person_id;

}
