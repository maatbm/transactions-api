package com.matheus_moraes.transactions_api.dto.req;

import jakarta.validation.constraints.NotNull;

import java.time.OffsetDateTime;

public record TransactionReqDto(
        @NotNull(message = "Value is required")
        Double valor,

        @NotNull(message = "Date/hour is required")
        OffsetDateTime dataHora
) {
}
