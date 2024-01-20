package ru.example.BankCard.dto;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
@Getter
@Setter
public class ShowCardsResponseDto extends AbstractDto{
    private String fio;
    private List<AccountDto> accounts;
}
