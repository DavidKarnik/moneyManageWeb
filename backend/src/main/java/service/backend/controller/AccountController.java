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

    @GetMapping("/balances")
    public ResponseEntity<List<Balance>> getBalanceByEmailAndCollectionId(
            @RequestParam String email,
            @RequestParam String collectionId
    ) {
        List<Balance> balance = accountService.getBalanceHistory(accountService.getTransactions(email, collectionId), accountService.getCurrentBalance(email, collectionId));

        if (!balance.isEmpty()) {
            return ResponseEntity.ok(balance);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/collectionInfos")
    public double getCurrentBalanceOfAccountByEmailAndCollectionId(
            @RequestParam String email,
            @RequestParam String collectionId
    ) {
        return accountService.getCurrentBalance(email, collectionId);
    }
}

