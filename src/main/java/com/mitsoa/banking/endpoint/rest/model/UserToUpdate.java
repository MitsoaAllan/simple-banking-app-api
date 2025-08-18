package com.mitsoa.banking.endpoint.rest.model;

import java.time.LocalDate;

public record UserToUpdate(String fullName, LocalDate birthdate) {}
