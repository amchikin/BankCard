package ru.example.BankCard.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PeopleErrorResponse {

    private String message;
    private long timestamp;

}
