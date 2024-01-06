package service.backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Account {
    private String email;
    private List<Collection> collections;

    @Override
    public String toString() {
        return "Account{" +
                "email='" + email + '\'' +
                ", collections=" + collections +
                '}';
    }
}
