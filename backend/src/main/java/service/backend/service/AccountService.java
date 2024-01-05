package service.backend.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.stereotype.Service;
import service.backend.model.Account;
import service.backend.model.User;

import java.io.File;
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
    }

    public List<Account> getUserAccountsByEmail(String email) {
        // Filtrujeme účty podle e-mailu
        return accounts.stream()
                .filter(account -> account.getEmail().equals(email))
                .collect(Collectors.toList());
    }


    private List<User> loadUsersFromJsonFile() {
        try {
            String filePath = "data/users.json";

            InputStreamReader reader = new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8);

            JSONParser parser = new JSONParser();
            JSONObject logObject = (JSONObject) parser.parse(reader);

            // Get the users array from the log object
            JSONArray a = (JSONArray) logObject.get("users");

            List<User> usersFind = new ArrayList<>();


            for (Object o : a) {
                JSONObject user = (JSONObject) o;

                User userA = new User(
                        (String) user.get("email"),
                        (String) user.get("password")
                );
                usersFind.add(userA);
            }
            reader.close();
            return usersFind;
        } catch (IOException | ParseException e) {
            // V případě chyby při čtení ze souboru
            e.printStackTrace();
            return List.of();
        }
    }
}

