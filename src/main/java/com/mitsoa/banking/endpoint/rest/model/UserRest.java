package com.mitsoa.banking.endpoint.rest.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserRest {
    private String fullName;
    private String email;
    private LocalDate birthdate;

    public UserRest(String fullName, String email, LocalDate birthdate) {
        this.fullName = fullName;
        this.email = email;
        this.birthdate = birthdate;
    }
}
