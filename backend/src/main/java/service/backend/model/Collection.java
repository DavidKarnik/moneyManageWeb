package service.backend.model;
import java.util.ArrayList;
import java.util.List;

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
    private List<Transaction> transactions;

    public Collection(String _nameOfAccount, double _balance, List<String> _transactions) {
        this.id = generateUniqueId();
        this.nameOfAccount = _nameOfAccount;
        this.balance = _balance;
        this.transactions = parseTransactions(_transactions);
    }

    public Collection(String _id, String _nameOfAccount, double _balance, List<String> _transactions) {
        this.id = _id;
        this.nameOfAccount = _nameOfAccount;
        this.balance = _balance;
        this.transactions = parseTransactions(_transactions);
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

    private List<Transaction> parseTransactions(List<String> transactionStrings) {
        List<Transaction> parsedTransactions = new ArrayList<>();

        for (String transactionString : transactionStrings) {
            String[] parts = transactionString.split("\\|");

            if (parts.length == 2) {
                String date = parts[0];
                double balance = Double.parseDouble(parts[1]);

                Transaction transaction = new Transaction(date, balance);
                parsedTransactions.add(transaction);
            }
        }

        return parsedTransactions;
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
