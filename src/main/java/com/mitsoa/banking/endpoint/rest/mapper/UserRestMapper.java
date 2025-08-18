package com.mitsoa.banking.endpoint.rest.mapper;

import com.mitsoa.banking.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserRestMapper {

    public com.mitsoa.banking.endpoint.rest.model.User apply(User user) {
        return new  com.mitsoa.banking.endpoint.rest.model.User(
                user.getFullName(),
                user.getEmail(),
                user.getBirthDate(),
                user.getActualMoney().getAmount()
        );
    }
}
