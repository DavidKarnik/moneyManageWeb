package service.backend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class Collection {
//    private static int nextId = 1;

    private String id;
    private String nameOfAccount;
    private double balance;

    public Collection(String _nameOfAccount, double _balance) {
        this.id = generateUniqueId();
        this.nameOfAccount = _nameOfAccount;
        this.balance = _balance;
    }

    public Collection(String _id, String _nameOfAccount, double _balance) {
        this.id = _id;
        this.nameOfAccount = _nameOfAccount;
        this.balance = _balance;
    }

    public String generateUniqueId() {
        return UUID.randomUUID().toString();
    }

    @Override
    public String toString() {
        return "Collection{" +
                "id=" + id +
                ", nameOfAccount='" + nameOfAccount + '\'' +
                ", balance=" + balance +
                '}';
    }
}
