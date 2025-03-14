package com.icocdeap.cuenta_bancaria.controller;

import com.icocdeap.cuenta_bancaria.model.Account;
import com.icocdeap.cuenta_bancaria.model.Transaction;
import com.icocdeap.cuenta_bancaria.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/bank")
class BankController {
    @Autowired
    private BankService bankService;

    //http://localhost:8080/api/bank/account
    @PostMapping("/account")
    public ResponseEntity<Account> createAccount(@RequestBody String owner) {
        return ResponseEntity.ok(bankService.createAccount(owner));
    }

    //http://localhost:8080/api/bank/deposit
    @PostMapping("/deposit")
    public ResponseEntity<Transaction> deposit(@RequestParam Long accountId, @RequestParam BigDecimal amount) {
        return ResponseEntity.ok(bankService.deposit(accountId, amount));
    }

    //http://localhost:8080/api/bank/withdraw
    @PostMapping("/withdraw")
    public ResponseEntity<Transaction> withdraw(@RequestParam Long accountId, @RequestParam BigDecimal amount) {
        return ResponseEntity.ok(bankService.withdraw(accountId, amount));
    }

    //http://localhost:8080/api/bank/transactions
    @GetMapping("/transactions")
    public ResponseEntity<List<Transaction>> getTransactions(@RequestParam Long accountId) {
        return ResponseEntity.ok(bankService.getTransactions(accountId));
    }
}
