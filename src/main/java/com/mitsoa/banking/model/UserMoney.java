package com.mitsoa.banking.model;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class UserMoney {
    private Money money;
    private TypeTransaction  typeTransaction;
    private Instant creationInstant;

    public UserMoney(Money money, TypeTransaction typeTransaction) {
        this.money = money;
        this.typeTransaction = typeTransaction;
    }
}
