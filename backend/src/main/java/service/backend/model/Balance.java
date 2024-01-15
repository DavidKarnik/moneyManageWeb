package service.backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Balance {
    // reálné konečné stavy účtu v čase
    private String date;
    private double balance;

    @Override
    public String toString() {
        return "Balance{" +
                "date='" + date + '\'' +
                ", balance=" + balance +
                '}';
    }
}
