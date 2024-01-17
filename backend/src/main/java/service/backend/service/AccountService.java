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
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
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
        return getUserAccountByEmail(email)
                .flatMap(account -> account.getCollections().stream()
                        .filter(collection -> collection.getId().equals(collectionId))
                        .findFirst()
                        .map(Collection::getTransactions))
                .orElse(List.of());
    }

    private Optional<Account> getUserAccountByEmail(String email) {
        return accounts.stream()
                .filter(account -> account.getEmail().equals(email))
                .findFirst();
    }

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


    public List<Balance> getBalanceHistory(List<Transaction> transactions, double initialBalance) {
        List<Balance> balanceHistory = new ArrayList<>();
        double currentBalance = initialBalance;

        // sorted from newest to oldest - for recursive balance calculation
        transactions.sort(Comparator.comparing(Transaction::getDate).reversed());
//        transactions.sort(Comparator.comparing(Transaction::getDate));

        // Přidávání prvního záznamu bez změny
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String currentFormattedDate = currentDateTime.format(formatter);

        // Přidání prvního záznamu bez změny
        Balance initialEntry = new Balance(currentFormattedDate, currentBalance);
        balanceHistory.add(initialEntry);

        for (Transaction transaction : transactions) {
            String date = transaction.getDate();
            double transactionAmount = transaction.getBalance();

            // Reversed, from back to front * -1
            currentBalance += -1*transactionAmount;
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

    public double getCurrentBalance(String email, String collectionId) {
        return getUserAccountByEmail(email)
                .flatMap(account -> account.getCollections().stream()
                        .filter(collection -> collection.getId().equals(collectionId))
                        .findFirst()
                        .map(Collection::getBalance))
                .orElse(0.0);  // Pokud účet neexistuje nebo nemá kolekci, vrátíme 0.0
    }

}

