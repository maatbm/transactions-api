package com.matheus_moraes.transactions_api.service;

import com.matheus_moraes.transactions_api.dto.req.TransactionReqDto;
import com.matheus_moraes.transactions_api.dto.res.StatisticsResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StatisticsService {
    private final TransactionService transactionService;

    public StatisticsResDto generateStatisticsPerTimeInterval(Long timeInterval, String processid) {
        List<TransactionReqDto> transactionsList = transactionService.getTransactionsListPerTimeInterval(timeInterval, processid);
        if (transactionsList.isEmpty()) {
            return new StatisticsResDto(0L, 0.0, 0.0, 0.0, 0.0);
        }
        DoubleSummaryStatistics stats = transactionsList.stream().mapToDouble(TransactionReqDto::valor).summaryStatistics();
        return new StatisticsResDto(
                stats.getCount(),
                stats.getSum(),
                stats.getAverage(),
                stats.getMin(),
                stats.getMax()
        );
    }
}
