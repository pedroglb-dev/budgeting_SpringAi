package com.budgeting.domain;

import java.util.List;

public interface TransactionRepository {
    Transaction save(Transaction transaction);
    void delete(Transaction transaction);
    List<Transaction> findAllByCategory(Category category);
}
