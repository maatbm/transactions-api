package com.matheus_moraes.transactions_api.controller;

import com.matheus_moraes.transactions_api.dto.req.TransactionReqDto;
import com.matheus_moraes.transactions_api.service.TransactionService;
import com.matheus_moraes.transactions_api.util.ControllerUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transacao")
@RequiredArgsConstructor
@Slf4j
public class TransactionController {
    private final TransactionService transactionService;
    private final ControllerUtils controllerUtils;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewTransaction(@Valid @RequestBody TransactionReqDto request) {
        String processId = controllerUtils.generateProcessId();
        log.info("[ID: {}] Controller: Received request to add a new transaction -> {}", processId, request);
        transactionService.addNewTransaction(request, processId);
        log.info("[ID: {}] Controller: Transaction processed successfully. Returning HTTP status {}.", processId, HttpStatus.CREATED);
    }

    @DeleteMapping
    public void deleteAllTransactions() {
        String processId = controllerUtils.generateProcessId();
        log.info("[ID: {}] Controller: Received request to delete all transactions.", processId);
        transactionService.deleteAllTransactions(processId);
        log.info("[ID: {}] Controller: All transactions deleted successfully. Returning HTTP status {}.", processId, HttpStatus.OK);
    }
}
