package com.icocdeap.cuenta_bancaria.service;

import com.icocdeap.cuenta_bancaria.model.Account;
import com.icocdeap.cuenta_bancaria.model.Transaction;
import com.icocdeap.cuenta_bancaria.repository.AccountRepository;
import com.icocdeap.cuenta_bancaria.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BankService {
    @Autowired
    private AccountRepository accountRepo;
    @Autowired
    private TransactionRepository transactionRepo;

    public Account createAccount(String owner) {
        return accountRepo.save(new Account(owner));
    }

    public Transaction deposit(Long accountId, BigDecimal amount) {
        Account account = accountRepo.findById(accountId).orElseThrow();
        account.setBalance(account.getBalance().add(amount));
        accountRepo.save(account);
        return transactionRepo.save(new Transaction(accountId, amount, "DEPOSIT"));
    }

    public Transaction withdraw(Long accountId, BigDecimal amount) {
        Account account = accountRepo.findById(accountId).orElseThrow();
        if (account.getBalance().compareTo(amount) < 0) throw new RuntimeException("Insufficient funds");
        account.setBalance(account.getBalance().subtract(amount));
        accountRepo.save(account);
        return transactionRepo.save(new Transaction(accountId, amount, "WITHDRAWAL"));
    }

    public List<Transaction> getTransactions(Long accountId) {
        return transactionRepo.findByAccountId(accountId);
    }
}
