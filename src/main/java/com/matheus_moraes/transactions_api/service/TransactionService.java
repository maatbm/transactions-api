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
