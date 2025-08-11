package com.mitsoa.banking.repository;

import com.mitsoa.banking.model.User;
import com.mitsoa.banking.repository.db.Datasource;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
public class UserRepository implements CrudRepostory<User> {
    private final Datasource dataSource;

    @SneakyThrows
    @Override
    public List<User> saveAll(List<User> list) {
        List<User> users = new ArrayList<>();
        String sql = """
                insert into "user"
                (full_name,email,birthdate)
                values (?,?,?)
                on conflict (email) do nothing
                """;
        try(Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ){
            list.forEach(user -> {
                try{
                    ps.setString(1, user.getFullName());
                    ps.setString(2, user.getEmail());
                    ps.setDate(3, Date.valueOf(user.getBirthDate()));
                    int rows = ps.executeUpdate();
                    if (rows > 0){
                        users.add(user);
                    }
                }catch(SQLException e){
                    throw new RuntimeException(e);
                }
            });
        }
        return users;
    }

    @Override
    public List<User> findAll() {
        // TODO: find all users method
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
