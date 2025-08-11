package com.mitsoa.banking.endpoint.rest.controller;

import com.mitsoa.banking.model.User;
import com.mitsoa.banking.repository.UserRepository;
import com.mitsoa.banking.repository.db.Datasource;
import com.mitsoa.banking.repository.mapper.UserMapper;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

public class UserControllerTest {
    private final UserRepository userRepositorySubject = new UserRepository(new Datasource(),new UserMapper());
    List<User> users = List.of(
            new User(
                    "RAJAONAH Mitantsoa Allan",
                    "hei.mitsoa@gmail.com",
                    LocalDate.of(2003,7,6)),
            new User(
                    "RAJAONAH Alisoa Megane",
                    "hei.megane@gmail.com",
                    LocalDate.of(2011,2,25))
    );
    
    @Test
    void assert_true() {
        assertTrue(true);
    }
    @Test
    void save_all_repository() {

        List<User> actual = userRepositorySubject.saveAll(users);

        assertTrue(actual.containsAll(users));
        assertEquals(2, actual.size());
        assertNotNull(actual);
    }

    @BeforeAll
    static void setUp() throws SQLException {
        var dataSource = new Datasource();
        try(Connection conn = dataSource.getConnection();
            Statement ps = conn.createStatement();
        ){
            ps.execute("delete from \"user\"");
            ps.execute("alter sequence user_id_seq restart ");
        }
    }

    @AfterAll
    static void clear_data() throws SQLException {
        var dataSource = new Datasource();
        try(Connection conn = dataSource.getConnection();
            Statement ps = conn.createStatement();
        ){
            ps.execute("delete from \"user\"");
            ps.execute("alter sequence user_id_seq restart ");
        }
    }
}
