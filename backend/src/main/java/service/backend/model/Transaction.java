package service.backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Transaction {
    private String date;
    private double balance;
//    "date": "2023-05-01 18:07:19"
//    "balance": 1000.00

}
