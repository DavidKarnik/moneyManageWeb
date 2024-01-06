package service.backend.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Collection {
    private static int nextId = 1;

    private int id;
    private String nameOfAccount;
    private double balance;

    public Collection(String _nameOfAccount, double _balance) {
        this.id = generateUniqueId();
        this.nameOfAccount = _nameOfAccount;
        this.balance = _balance;
    }

    public Collection(int _id, String _nameOfAccount, double _balance) {
        this.id = _id;
        this.nameOfAccount = _nameOfAccount;
        this.balance = _balance;
    }

    private int generateUniqueId() {
        return nextId++;
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
