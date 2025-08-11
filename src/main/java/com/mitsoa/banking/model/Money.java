package com.mitsoa.banking.model;

import lombok.*;

import java.time.Instant;

@NoArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class Money {
    private int id;
    private double amount;
    private Instant creationInstant;

    public Money(int id, double amount) {
        this.id = id;
        this.amount = amount;
    }
}
