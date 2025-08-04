package com.matheus_moraes.transactions_api.service;

import com.matheus_moraes.transactions_api.dto.req.TransactionReqDto;
import com.matheus_moraes.transactions_api.exception.UnprocessableEntityException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionService {
    private final List<TransactionReqDto> transactionsList = new CopyOnWriteArrayList<>();

    public void addNewTransaction(TransactionReqDto transactionReqDto, String processId) {
        log.info("[ID: {}] Service: Starting to add new transaction -> {}", processId, transactionReqDto);
        this.validateNewTransaction(transactionReqDto, processId);
        transactionsList.add(transactionReqDto);
        log.info("[ID: {}] Service: Transaction added successfully.", processId);
    }

    public void deleteAllTransactions(String processId) {
        log.info("[ID: {}] Service: Starting process to delete all transactions.", processId);
        int itemsToDelete = transactionsList.size();
        transactionsList.clear();
        log.info("[ID: {}] Service: Successfully deleted {} transactions.", processId, itemsToDelete);
    }

    public List<TransactionReqDto> getTransactionsListPerTimeInterval(Long timeInterval, String processId) {
        log.info("[ID: {}] Service: Getting transactions for time interval of {} seconds.", processId, timeInterval);
        OffsetDateTime interval = OffsetDateTime.now().minusSeconds(timeInterval);
        List<TransactionReqDto> transactions = transactionsList
                .stream()
                .filter(transaction -> transaction.dataHora().isAfter(interval))
                .toList();
        log.info("[ID: {}] Service: Found {} transactions for the given time interval.", processId, transactions.size());
        return transactions;
    }

    private void validateNewTransaction(TransactionReqDto transaction, String processId) {
        log.info("[ID: {}] Service: Starting transaction validation.", processId);
        if (transaction.dataHora().isAfter(OffsetDateTime.now())) {
            UnprocessableEntityException ex = new UnprocessableEntityException("Transaction date/hour cannot be in the future.");
            log.error("[ID: {}] Service: Validation failed. Date/hour is in the future -> {}", processId, transaction.dataHora(), ex);
            throw ex;
        } else if (transaction.valor() < 0) {
            UnprocessableEntityException ex = new UnprocessableEntityException("Transaction value cannot be smaller than 0.");
            log.error("[ID: {}] Service: Validation failed. Value is smaller than 0 -> {}", processId, transaction.valor(), ex);
            throw ex;
        }
        log.info("[ID: {}] Service: Transaction validation successful.", processId);
    }
}