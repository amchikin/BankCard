package ru.example.BankCard.dto;

import lombok.Data;
import java.util.List;

@Data
public class ShowCardsResponseDto extends AbstractDto{
   // private String fio; // В сервисе Mapping
    private String surname;
    private String name;
    private List<AccountDto> accounts;

}
