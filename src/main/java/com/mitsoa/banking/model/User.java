package com.mitsoa.banking.model;

import lombok.*;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class User {
    private int id;
    private String fullName;
    private String email;
    private LocalDate birthDate;
    private Instant creationInstant;
    @Setter private List<UserMoney> transactions;

    public User(String fullName, String email, LocalDate birthDate)
    {
        this.fullName = fullName;
        this.email = email;
        this.birthDate = birthDate;
    }
    public User(int id,String fullName, String email, LocalDate birthDate,Instant creationInstant)
    {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.birthDate = birthDate;
        this.creationInstant = creationInstant;
    }

    public Money getActualMoney(){
        if(this.transactions == null ||  this.transactions.isEmpty()){
            return new Money(0);
        }
        double deposit = this.transactions.stream().filter(um-> um.getTypeTransaction().equals(TypeTransaction.IN)).map(UserMoney::getMoney).mapToDouble(Money::getAmount).sum();
        double withdrawals = this.transactions.stream().filter(um-> um.getTypeTransaction().equals(TypeTransaction.OUT)).map(UserMoney::getMoney).mapToDouble(Money::getAmount).sum();
        return new Money(deposit - withdrawals);
    }
}
