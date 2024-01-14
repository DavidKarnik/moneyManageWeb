package service.backend.service;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.stereotype.Service;
import service.backend.model.Account;
import service.backend.model.Collection;
import service.backend.model.Transaction;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {

    String filePath = "data/accounts.json";

    public AccountService() {
        accounts = loadAccountsFromJsonFile();
    }

    private List<Account> accounts;

    public List<Collection> getUsersCollectionsByEmail(String email) {
        return accounts.stream()
                .filter(account -> account.getEmail().equals(email))
                .findFirst() // první odpovídající účet
                .map(Account::getCollections) // seznam kolekcí z účtu
                .orElse(List.of()); // pokud účet neexistuje nebo nemá kolekce, vrátíme prázdný seznam
    }

    public List<Transaction> getTransactions(String email, String collectionId) {
        return accounts.stream()
                .filter(account -> account.getEmail().equals(email))
                .findFirst()
                .flatMap(account -> account.getCollections().stream()
                        .filter(collection -> collection.getId().equals(collectionId))
                        .findFirst()
                        .map(Collection::getTransactions))
                .orElse(List.of());
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

}

