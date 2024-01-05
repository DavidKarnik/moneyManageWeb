package service.backend.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Collection {
    private static long nextId = 1;

    private Long id;
    private String nameOfAccount;
    private double balance;

    public Collection(String _nameOfAccount, double _balance) {
        this.id = generateUniqueId();
        this.nameOfAccount = _nameOfAccount;
        this.balance = _balance;
    }

    private Long generateUniqueId() {
        return nextId++;
    }
}
