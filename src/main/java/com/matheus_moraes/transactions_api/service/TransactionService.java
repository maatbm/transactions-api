package com.matheus_moraes.transactions_api.service;

import com.matheus_moraes.transactions_api.dto.req.TransactionReqDto;
import com.matheus_moraes.transactions_api.exception.UnprocessableEntityException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionService {
    private final List<TransactionReqDto> transactionsList = new ArrayList<>();

    public void addNewTransaction(TransactionReqDto transactionReqDto, String processId) {
        log.info("[ID: {}] Service: start add transaction process", processId);
        this.validateNewTransaction(transactionReqDto, processId);
        transactionsList.add(transactionReqDto);
        log.info("[ID: {}] Service: transaction add sucessful", processId);
    }

    public void deleteAllTransactions(String processId){
        log.info("[ID: {}] Service: start delete all transactions process", processId);
        transactionsList.clear();
        log.info("[ID: {}] Service: delete all transactions sucessful", processId);
    }

    public List<TransactionReqDto> getTransactionsListPerTimeInterval(Long timeInterlval, String processId) {
        log.info("[ID: {}] Service: get transactions list per time interval", processId);
        OffsetDateTime inteval = OffsetDateTime.now().minusSeconds(timeInterlval);
        return transactionsList
                .stream()
                .filter(transaction -> transaction.dataHora().isAfter(inteval))
                .toList();
    }

    private void validateNewTransaction(TransactionReqDto transaction, String processId){
        log.info("[ID: {}] Service: start transaction validation", processId);
        if(transaction.dataHora().isAfter(OffsetDateTime.now())){
            UnprocessableEntityException ex = new UnprocessableEntityException("Date/hour is in the future");
            log.error("[ID: {}] Service: date/hour is in the future", processId, ex);
            throw ex;
        } else if (transaction.valor() < 0) {
            UnprocessableEntityException ex = new UnprocessableEntityException("Value smaller than 0");
            log.error("[ID: {}] Service: Value smaller than 0", processId, ex);
            throw ex;
        }
        log.info("[ID: {}] Service: Transaction validation sucessful", processId);
    }
}
