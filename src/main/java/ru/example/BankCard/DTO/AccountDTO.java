package ru.example.BankCard.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigInteger;


@Data
public class AccountDTO extends AbstractDTO {

    @NotEmpty(message = "Поле номера карты не должно быть пустым")
    @Size(min = 16, max = 16, message = "Размер номера карты ровно 16")
    private String card_number;

    @Min(value = 0, message = "Баланс не может быть отрицательным")
    private BigInteger balance;

    int person_id; // TODO не отображается - разобраться


}
