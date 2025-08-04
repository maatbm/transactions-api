package com.matheus_moraes.transactions_api.dto.res;

public record StatisticsResDto(
        long count,
        double sum,
        double avg,
        double min,
        double max
) {
}
