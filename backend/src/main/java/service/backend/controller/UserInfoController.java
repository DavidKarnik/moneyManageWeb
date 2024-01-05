package service.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/api") // prefix
//@CrossOrigin(origins = "http://localhost:5173") // Povolit přístup pouze z tohoto portu
public class UserInfoController {
    @GetMapping("/home")
    public String home() {

        return "";
    }

    @GetMapping("/getName")
    public ResponseEntity<Map<String, String>> getName() {
        Map<String, String> response = new HashMap<>();
        response.put("name", "John Doe");
        System.out.println("/getName");
        return ResponseEntity.ok(response);
    }
}
