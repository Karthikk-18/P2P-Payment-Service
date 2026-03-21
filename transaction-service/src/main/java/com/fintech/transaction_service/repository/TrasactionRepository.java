package com.fintech.transaction_service.repository;

import com.fintech.transaction_service.dto.TransactionResponseDto;
import com.fintech.transaction_service.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrasactionRepository extends JpaRepository<Transaction, Long> {

}
