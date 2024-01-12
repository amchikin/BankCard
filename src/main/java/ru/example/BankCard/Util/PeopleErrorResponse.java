package ru.example.BankCard.Util;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

//@Data
@Getter
@Setter
public class PeopleErrorResponse {

    private String message;
    private long timestamp;

    // Если просто @Data, то в PeopleController ругается на PeopleErrorResponse. Разобраться! TODO

    public PeopleErrorResponse(String message, long timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//
//    public long getTimestamp() {
//        return timestamp;
//    }
//
//    public void setTimestamp(long timestamp) {
//        this.timestamp = timestamp;
//    }
}
