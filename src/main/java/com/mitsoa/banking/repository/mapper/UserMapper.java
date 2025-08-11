package com.mitsoa.banking.repository.mapper;

import com.mitsoa.banking.model.User;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.util.function.Function;

@Component
public class UserMapper implements Function<ResultSet, User> {
    @SneakyThrows
    @Override
    public User apply(ResultSet rs) {
        return new User(
            rs.getInt("id"),
            rs.getString("full_name"),
            rs.getString("email"),
            rs.getDate("birthdate").toLocalDate(),
            rs.getTimestamp("creation_instant").toInstant()
        );
    }
}
