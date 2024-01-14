package service.backend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class Collection {

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

    public static String generateUniqueId() {

        String uuidString = UUID.randomUUID().toString();

        String[] parts = uuidString.split("-");

        if (parts.length >= 3) {
            return parts[0] + "-" + parts[1] + "-" + parts[2];
        } else {
            return uuidString;
        }
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
