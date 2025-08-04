package com.matheus_moraes.transactions_api.controller;

import com.matheus_moraes.transactions_api.dto.req.TransactionReqDto;
import com.matheus_moraes.transactions_api.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/transacao")
@RequiredArgsConstructor
@Slf4j
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Void> addNewTransaction(@Valid @RequestBody TransactionReqDto request) {
        String processId = UUID.randomUUID().toString();
        log.info("[ID: {}] Controller: Received request to add a new transaction -> {}", processId, request);
        transactionService.addNewTransaction(request, processId);
        log.info("[ID: {}] Controller: Transaction processed successfully. Returning HTTP status {}.", processId, HttpStatus.CREATED);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
