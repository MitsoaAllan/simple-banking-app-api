package com.mitsoa.banking.endpoint.rest.mapper;

import com.mitsoa.banking.endpoint.rest.model.UserToUpdate;
import com.mitsoa.banking.model.User;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class UserToUpdateMapper implements Function<UserToUpdate, User> {
    @Override
    public User apply(UserToUpdate userToUpdate) {
        return null;
    }

    public User toUser(UserToUpdate userToUpdate,String email) {
        return new User(userToUpdate.fullName(),email,userToUpdate.birthdate());
    }
}
