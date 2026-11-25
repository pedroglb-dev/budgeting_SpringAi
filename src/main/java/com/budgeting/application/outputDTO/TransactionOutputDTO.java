package com.budgeting.application.outputDTO;

import com.budgeting.domain.Transaction;

import java.math.BigDecimal;
import java.math.RoundingMode;

public record TransactionOutputDTO (String id, String description, String category, double value) {

    public static TransactionOutputDTO from(Transaction transaction) {
        return new TransactionOutputDTO(transaction.getId().uuid().toString(),
                transaction.getDescription(),
                transaction.getCategory().name(),
                BigDecimal.valueOf(transaction.getAmount()).setScale(2, RoundingMode.HALF_UP).doubleValue());
    }
}
