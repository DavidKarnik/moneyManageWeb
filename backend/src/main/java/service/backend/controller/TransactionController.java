package service.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.backend.model.Transaction;
import service.backend.service.AccountService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class TransactionController {

    private final AccountService accountService;

    @Autowired
    public TransactionController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/transactions")
    public ResponseEntity<List<Transaction>> getTransactionsByEmailAndCollectionId(
            @RequestParam String email,
            @RequestParam String collectionId
    ) {
        List<Transaction> transactions = accountService.getTransactions(email, collectionId);

        if (!transactions.isEmpty()) {
            return ResponseEntity.ok(transactions);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/transaction")
    public ResponseEntity<String> createTransaction(@RequestBody Map<String, Object> transactionData) {
        try {
            // Změna castingu na Double.valueOf pro správné zpracování různých typů čísel
            double amount = Double.parseDouble(transactionData.get("amount").toString());
            String time = (String) transactionData.get("time");
            String email = (String) transactionData.get("email");
            String collectionId = (String) transactionData.get("collectionId");

            System.out.println(accountService.addTransaction(email, collectionId, time, amount));

            // Zpracování dat a výpis do konzole
            System.out.println("Amount: " + amount);
            System.out.println("Time: " + time);
            System.out.println("email: " + email);
            System.out.println("collectionId: " + collectionId);
//            System.out.println("Transaction created successfully");
            // Vrácení odpovědi
            return ResponseEntity.ok("Transaction created successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error creating transaction");
        }
    }
}
