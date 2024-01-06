package service.backend.service;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.stereotype.Service;
import service.backend.model.Account;
import service.backend.model.Collection;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {

    String filePath = "data/accounts.json";

    public AccountService() {
        accounts = loadAccountsFromJsonFile();
    }

    private List<Account> accounts; // inicializujte tuto proměnnou v konstruktoru nebo metode

    public List<Collection> getUsersCollectionsByEmail(String email) {
        return accounts.stream()
                .filter(account -> account.getEmail().equals(email))
                .findFirst() // získáme první odpovídající účet
                .map(Account::getCollections) // získáme seznam kolekcí z účtu
                .orElse(List.of()); // pokud účet neexistuje nebo nemá kolekce, vrátíme prázdný seznam
    }


    private List<Account> loadAccountsFromJsonFile() {
        try {
            InputStreamReader reader = new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8);

            JSONParser parser = new JSONParser();
            JSONObject logObject = (JSONObject) parser.parse(reader);

            // Get the users array from the log object
            JSONArray accountsArray = (JSONArray) logObject.get("accounts");

            List<Account> accountsFind = new ArrayList<>();


            for (Object o : accountsArray) {
                JSONObject accountUser = (JSONObject) o;

                JSONArray collectionsJsonArray = (JSONArray) accountUser.get("collections");
                List<Collection> collections = new ArrayList<>();

                for (Object collectionObj : collectionsJsonArray) {
                    JSONObject collectionJson = (JSONObject) collectionObj;

                    Collection collection = new Collection(
                            (int) collectionJson.get("id"),
                            (String) collectionJson.get("nameOfAccount"),
                            (Double) collectionJson.get("balance")
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

