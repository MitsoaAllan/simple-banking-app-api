package com.mitsoa.banking.endpoint.rest.mapper;

import com.mitsoa.banking.endpoint.rest.model.UserRest;
import com.mitsoa.banking.endpoint.rest.model.UserToCreate;
import com.mitsoa.banking.model.User;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.function.Function;

@Component
public class UserToCreateMapper implements Function<UserToCreate, User> {
    @Override
    public User apply(UserToCreate userToCreate) {
        return new User(userToCreate.fullName(),userToCreate.email(), Date.valueOf(userToCreate.birthdate()).toLocalDate());
    }

    public UserRest toRest(User user) {
        return new UserRest(user.getFullName(),user.getEmail(),user.getBirthDate());
    }
}
