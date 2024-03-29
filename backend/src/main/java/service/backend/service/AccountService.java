package service.backend.service;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.stereotype.Service;
import service.backend.model.Account;
import service.backend.model.Balance;
import service.backend.model.Collection;
import service.backend.model.Transaction;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class AccountService {

    String filePath = "data/accounts.json";

    public AccountService() {
        accounts = loadAccountsFromJsonFile();
    }

    private List<Account> accounts;

    public List<Collection> getUsersCollectionsByEmail(String email) {
        return getUserAccountByEmail(email) // první odpovídající účet
                .map(Account::getCollections) // seznam kolekcí z účtu
                .orElse(List.of()); // pokud účet neexistuje nebo nemá kolekce, vrátíme prázdný seznam
    }

    public List<Transaction> getTransactions(String email, String collectionId) {
        return getCollection(email, collectionId)
                .map(Collection::getTransactions)
                .orElse(List.of());
    }

    //
    // Collection Infos -----------------------------------------------------------------------------------------
    //


    public double getCurrentBalance(String email, String collectionId) {
        return getCollection(email, collectionId)
                .map(Collection::getBalance)
                .orElse(0.0);
    }

    public Balance getHighestBalance(String email, String collectionId) {
        List<Balance> balances = getBalanceHistory(email, collectionId);
        return balances.stream()
                .max(Comparator.comparing(Balance::getBalance))
                .orElse(null);
    }

    public Balance getLowestBalance(String email, String collectionId) {
        List<Balance> balances = getBalanceHistory(email, collectionId);
        return balances.stream()
                .min(Comparator.comparing(Balance::getBalance))
                .orElse(null);
    }

    // For Code Duplicity

    private Optional<Account> getUserAccountByEmail(String email) {
        return accounts.stream()
                .filter(account -> account.getEmail().equals(email))
                .findFirst();
    }

    private Optional<Collection> getCollection(String email, String collectionId) {
        return getUserAccountByEmail(email)
                .flatMap(account -> account.getCollections().stream()
                        .filter(collection -> collection.getId().equals(collectionId))
                        .findFirst());
    }

    // Another Methods

    private List<Account> loadAccountsFromJsonFile() {
        try {
            InputStreamReader reader = new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8);

            JSONParser parser = new JSONParser();
            JSONObject logObject = (JSONObject) parser.parse(reader);

            // Get the users array from the object
            JSONArray accountsArray = (JSONArray) logObject.get("accounts");

            List<Account> accountsFind = new ArrayList<>();


            for (Object o : accountsArray) {
                JSONObject accountUser = (JSONObject) o;

                JSONArray collectionsJsonArray = (JSONArray) accountUser.get("collections");
                List<Collection> collections = new ArrayList<>();

                for (Object collectionObj : collectionsJsonArray) {
                    JSONObject collectionJson = (JSONObject) collectionObj;

                    JSONArray transactionsArray = (JSONArray) collectionJson.get("transactions");
                    List<String> transactions = new ArrayList<>();

                    for (Object transaction : transactionsArray) {
                        transactions.add((String) transaction);
                    }

                    Collection collection = new Collection(
                            (String) collectionJson.get("id"),
                            (String) collectionJson.get("nameOfAccount"),
                            (Double) collectionJson.get("balance"),
                            transactions
                    );

                    collections.add(collection);
                }

                Account userA = new Account(
                        (String) accountUser.get("email"),
                        collections
                );
                accountsFind.add(userA);
            }
            reader.close();
            return accountsFind;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return List.of();
        }
    }


    public List<Balance> getBalanceHistory(String email, String collectionId) {

        List<Transaction> transactions = getTransactions(email, collectionId);

        List<Balance> balanceHistory = new ArrayList<>();

        double currentBalance = getCurrentBalance(email, collectionId);

        // sorted from newest to oldest - for recursive balance calculation
        transactions.sort(Comparator.comparing(Transaction::getDate).reversed());
//        transactions.sort(Comparator.comparing(Transaction::getDate));

        // Přidávání prvního záznamu bez změny
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String currentFormattedDate = currentDateTime.format(formatter);

        Balance initialEntry = new Balance(currentFormattedDate, currentBalance);
        balanceHistory.add(initialEntry);

        for (Transaction transaction : transactions) {
            String date = transaction.getDate();
            double transactionAmount = transaction.getBalance();

            // Reversed, from back to front * -1
            currentBalance += -1 * transactionAmount;
//            System.out.println(currentBalance);

            Balance historyEntry = new Balance(date, currentBalance);
            balanceHistory.add(historyEntry);
        }

        // Reverse for graph representation, from oldest to newest
        balanceHistory.sort(Comparator.comparing(Balance::getDate));

        // Výpis seřazených dat
//        balanceHistory.forEach(entry -> System.out.println(entry.getDate() + " - " + entry.getBalance()));

        return balanceHistory;
    }

    public Boolean addTransaction(String _email, String _collectionId, String _time, double _amount) {

        try {
            InputStreamReader reader = new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8);

            JSONParser parser = new JSONParser();
            JSONObject logObject = (JSONObject) parser.parse(reader);

            JSONArray accountsArray = (JSONArray) logObject.get("accounts");

            for (Object o : accountsArray) {
                JSONObject accountUser = (JSONObject) o;

                if (Objects.equals(accountUser.get("email").toString(), _email)) {
                    System.out.println("Email found successfully");
                    JSONArray collectionsJsonArray = (JSONArray) accountUser.get("collections");

                    for (Object collectionObj : collectionsJsonArray) {
                        JSONObject collectionJson = (JSONObject) collectionObj;

                        if (Objects.equals(collectionJson.get("id").toString(), _collectionId)) {
                            System.out.println("ID found successfully");
                            JSONArray transactionsArray = (JSONArray) collectionJson.get("transactions");

                            String operator = "";
                            if (_amount > 0) {
                                operator = "+";
                            }
                            transactionsArray.add(_time.replace("T", " ") + "|" + operator + _amount);

                            FileWriter file = new FileWriter(filePath);
                            file.write(logObject.toString());
                            file.flush();
                            file.close();

                            reader.close();

                            // refresh transactions
                            this.accounts = loadAccountsFromJsonFile();

                            return true;
                        }
                    }
                }

            }
            reader.close();
            return false;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return false;
        }

    }

}
