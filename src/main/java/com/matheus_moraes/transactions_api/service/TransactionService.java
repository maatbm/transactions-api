package com.matheus_moraes.transactions_api.service;

import com.matheus_moraes.transactions_api.dto.req.TransactionReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final List<TransactionReqDto> transactionsList = new ArrayList<>();

    
}
