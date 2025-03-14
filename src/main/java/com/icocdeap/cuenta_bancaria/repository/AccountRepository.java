package com.icocdeap.cuenta_bancaria.repository;

import com.icocdeap.cuenta_bancaria.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {}