package ru.example.BankCard.mapperService;
import org.springframework.stereotype.Service;
@Service
public class ShowCardsResponseService {
    public String enrichName(String name, String surname) {
        return name + " " + surname;
    }
}
