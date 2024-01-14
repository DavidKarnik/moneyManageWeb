package service.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
}

