package com.budgeting.application;

import com.budgeting.application.inputDTO.PersistTransactionInputDTO;
import com.budgeting.application.outputDTO.TransactionOutputDTO;
import com.budgeting.domain.Transaction;
import com.budgeting.domain.TransactionRepository;

public class PersistTransactionUseCase { // deve ter o principio da responsabilidade unica
    private final TransactionRepository transactionRepository;
    public PersistTransactionUseCase(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    //por padrao os useCases devem ter apenas o metodo execute
    public TransactionOutputDTO execute(PersistTransactionInputDTO inputDTO) {
       var transaction = transactionRepository.save(new Transaction(inputDTO.description(), inputDTO.amount(), inputDTO.category()));

       return TransactionOutputDTO.from(transaction);
    }
}
