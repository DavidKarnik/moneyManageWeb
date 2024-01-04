package service.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/api") // prefix
public class userInfoController {
    @GetMapping("/home")
    public String home() {

        return "";
    }

    @GetMapping("/getName")
    public ResponseEntity<Map<String, String>> getName() {
        Map<String, String> response = new HashMap<>();
        response.put("name", "John Doe"); // Nahraďte tím, co odpovídá vašim datům

        return ResponseEntity.ok(response);
    }
}
