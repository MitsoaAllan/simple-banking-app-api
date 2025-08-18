package com.mitsoa.banking.repository;

import com.mitsoa.banking.exception.UserNotFoundException;
import com.mitsoa.banking.model.User;
import com.mitsoa.banking.repository.db.Datasource;
import com.mitsoa.banking.repository.mapper.UserMapper;
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
    private final UserMapper userMapper;

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

    @SneakyThrows
    @Override
    public List<User> findAll(Integer page, Integer size) {
        List<User> users = new ArrayList<>();
        String sql = """
                select id,full_name,email,birthdate,creation_instant
                from "user" u
                limit ?
                offset ?
                """;
        try(Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)
        ){
            ps.setInt(1, size);
            ps.setInt(2, (page-1)*size);

            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                users.add(userMapper.apply(rs));
            }
        }
        return users;
    }

    @Override
    public User findByEmail(String email) {
        String sql = """
                select id,full_name,email,birthdate,creation_instant
                from "user"
                where email = ?
                limit 1
                """;
        try(Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
        ){
            ps.setString(1,email);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    return userMapper.apply(rs);
                }else{
                    return null;
                }
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public String deleteByEmail(String email) {
        String sql = """
                delete from "user"
                where email = ?
                """;
        try(Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)
        ){
            ps.setString(1,email);
            int rows = ps.executeUpdate();
            if (rows > 0){
                return ("user with email " + email + " deleted successfully");
            }else{
                throw new UserNotFoundException("User with email "+email+" does not exists");
            }
        }catch(SQLException | UserNotFoundException e){
            throw new RuntimeException(e);
        }
    }

    public User updateById(int id, User user) {
        String sql = """
                update "user"
                set full_name=?, birthdate=?
                where id=?
        """;

        try(Connection connection = dataSource.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql)
        ){
            ps.setString(1,user.getFullName());
            ps.setDate(2,Date.valueOf(user.getBirthDate()));
            ps.setInt(3,id);

            int rows = ps.executeUpdate();
            if( rows >0 ){
                return new User(id,user.getFullName(),user.getEmail(),user.getBirthDate());
            }else{
                return null;
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
