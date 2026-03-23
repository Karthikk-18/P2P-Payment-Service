package com.fintech.transaction_service.controller;

import com.fintech.transaction_service.dto.TransactionRequestDto;
import com.fintech.transaction_service.dto.TransactionResponseDto;
import com.fintech.transaction_service.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/send")
    public ResponseEntity<TransactionResponseDto> sendMoney(
            @Valid @RequestBody TransactionRequestDto requestDto
            ) {
        TransactionResponseDto responseDto = transactionService.sendMoney(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionResponseDto> getById(
            @PathVariable Long id
    ) {
        TransactionResponseDto responseDto = transactionService.getTransactionById(id);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/history/{id}")
    public ResponseEntity<List<TransactionResponseDto>> getHistory(
            @PathVariable Long id
    ) {
        List<TransactionResponseDto> transactions = transactionService.getTransactionHistory(id);
        return ResponseEntity.ok(transactions);
    }
}
