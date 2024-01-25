package service.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.backend.model.Balance;
import service.backend.model.Collection;
import service.backend.model.Transaction;
import service.backend.service.AccountService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/collections/{email}")
    public List<Collection> getUserAccounts(@PathVariable String email) {
        return accountService.getUsersCollectionsByEmail(email);
    }

    @GetMapping("/balances")
    public ResponseEntity<List<Balance>> getBalanceByEmailAndCollectionId(
            @RequestParam String email,
            @RequestParam String collectionId
    ) {
        List<Balance> balance = accountService.getBalanceHistory(email, collectionId);

        if (!balance.isEmpty()) {
            return ResponseEntity.ok(balance);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/currentBalance")
    public ResponseEntity<Double> getCurrentBalance(@RequestParam String email, @RequestParam String collectionId) {
        double currentBalance = accountService.getCurrentBalance(email, collectionId);
        return ResponseEntity.ok(currentBalance);
    }

    @GetMapping("/highestBalance")
    public ResponseEntity<Balance> getHighestBalance(@RequestParam String email, @RequestParam String collectionId) {
        Balance highestBalance = accountService.getHighestBalance(email,collectionId);
        return ResponseEntity.ok(highestBalance);
    }

    @GetMapping("/lowestBalance")
    public ResponseEntity<Balance> getLowestBalance(@RequestParam String email, @RequestParam String collectionId) {
        Balance lowestBalance = accountService.getLowestBalance(email,collectionId);
        return ResponseEntity.ok(lowestBalance);
    }

}

