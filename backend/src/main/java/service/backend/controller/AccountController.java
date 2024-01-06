package service.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.backend.model.Account;
import service.backend.model.Collection;
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
}

