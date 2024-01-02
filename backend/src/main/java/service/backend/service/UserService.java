package service.backend.service;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.stereotype.Service;
import service.backend.model.User;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final List<User> users;

    public UserService() {
        // Načítání dat ze souboru při vytvoření instance servisní třídy
        this.users = loadUsersFromJsonFile();
    }

    public List<User> getAllUsers() {
        return users;
    }

    public User getUserByEmail(String email) {
        return users.stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElse(null);
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

//                String name = (String) user.get("name");
//                System.out.println(name);
//
//                String surname = (String) user.get("surname");
//                System.out.println(surname);
//
//                String email = (String) user.get("email");
//                System.out.println(email);

                User userA = new User(
                        (String) user.get("name"),
                        (String) user.get("surname"),
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
