package com.budgeting.application.inputDTO;

import com.budgeting.domain.Category;
import com.budgeting.domain.TransactionId;

public record PersistTransactionInputDTO(String description, long amount, Category category) {
}
