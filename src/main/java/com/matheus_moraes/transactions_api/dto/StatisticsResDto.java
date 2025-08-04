package com.matheus_moraes.transactions_api.dto;

public record StatisticsResDto(
        long count,
        double sum,
        double avg,
        double min,
        double max
) {
}
