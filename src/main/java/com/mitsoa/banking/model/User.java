package com.mitsoa.banking.model;

import lombok.*;

import java.time.Instant;
import java.time.LocalDate;

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

    public  User(String fullName, String email, LocalDate birthDate)
    {
        this.fullName = fullName;
        this.email = email;
        this.birthDate = birthDate;
    }
}
