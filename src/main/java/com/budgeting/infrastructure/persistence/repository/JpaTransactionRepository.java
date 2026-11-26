package com.budgeting.infrastructure.persistence.repository;

import com.budgeting.domain.Category;
import com.budgeting.domain.Transaction;
import com.budgeting.domain.TransactionRepository;

import java.util.List;

public class JpaTransactionRepository implements TransactionRepository {
    //o estou usando o meu repositorio do dominio como contrato mas estou usando a implementacao externa do crudrepository feito pelo spring para a minha entidade no banco, portanto o contrato eh do dominio, mas a imokementacao eh externa e depende do spring
    private final TransactionEntityRepository transactionEntityRepository;

    public JpaTransactionRepository(TransactionEntityRepository transactionEntityRepository) {
        this.transactionEntityRepository = transactionEntityRepository;
    }

    @Override
    public Transaction save(Transaction transaction) {
        return null;
    }

    @Override
    public List<Transaction> findAllByCategory(Category category) {
        return List.of();
    }
}
