package com.mitsoa.banking.endpoint.rest.model;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class User {
    private String fullName;
    private String email;
    private LocalDate birthdate;
    private double balance;
}
