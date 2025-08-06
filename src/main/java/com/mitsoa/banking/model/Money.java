package com.mitsoa.banking.model;

import lombok.*;

import java.time.Instant;
import java.util.List;

@NoArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class Money {
    private int id;
    private double amount;
    private Instant creationInstant;
    private List<UserMoney> transactions;

    public Money(int id, double amount) {
        this.id = id;
        this.amount = amount;
    }

}
