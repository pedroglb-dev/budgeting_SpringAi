package com.budgeting.domain;

import java.util.UUID;

public record TransactionId(UUID uuid) {
    public TransactionId() {
        this(UUID.randomUUID()); // O this nesse caso ta fazendo referencia em chamando o outro construtor canonico desse record do java.
    }
}
