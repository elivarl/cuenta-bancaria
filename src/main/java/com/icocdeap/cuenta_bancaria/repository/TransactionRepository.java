package com.icocdeap.cuenta_bancaria.repository;

import com.icocdeap.cuenta_bancaria.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByAccountId(Long accountId);
}
