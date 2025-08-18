package com.mitsoa.banking.endpoint.rest.controller;

import com.mitsoa.banking.endpoint.rest.mapper.UserToUpdateMapper;
import com.mitsoa.banking.endpoint.rest.model.UserToUpdate;
import com.mitsoa.banking.exception.UserNotFoundException;
import com.mitsoa.banking.model.User;
import com.mitsoa.banking.repository.UserRepository;
import com.mitsoa.banking.repository.db.Datasource;
import com.mitsoa.banking.repository.mapper.UserMapper;
import com.mitsoa.banking.service.UserService;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

public class UserControllerTest {
    private final UserRepository userRepositorySubject = new UserRepository(new Datasource(),new UserMapper());
    private final UserService userServiceSubject = new UserService(userRepositorySubject,new UserToUpdateMapper());
    List<User> users = List.of(
            new User(
                    1,
                    "RAJAONAH Mitantsoa Allan",
                    "hei.mitsoa@gmail.com",
                    LocalDate.of(2003,7,6),
                    Instant.now()),
            new User(
                    2,
                    "RAJAONAH Alisoa Megane",
                    "hei.megane@gmail.com",
                    LocalDate.of(2011,2,25),
                    Instant.now())
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

    @Test
    void find_users_with_repository(){
        userRepositorySubject.saveAll(users);
        List<User> actual = userRepositorySubject.findAll(1,2);

        assertTrue(users.size() == actual.size() && actual.size() == 2);
        assertNotNull(actual);
        assertEquals(users.getFirst().getFullName(),actual.getFirst().getFullName());
        assertEquals(users.getFirst().getBirthDate(),actual.getFirst().getBirthDate());
        assertEquals(users.getFirst().getEmail(),actual.getFirst().getEmail());
        assertEquals(users.getFirst().getTransactions(),actual.getFirst().getTransactions());

        assertEquals(users.get(1).getFullName(),actual.get(1).getFullName());
        assertEquals(users.get(1).getBirthDate(),actual.get(1).getBirthDate());
        assertEquals(users.get(1).getEmail(),actual.get(1).getEmail());
        assertEquals(users.get(1).getTransactions(),actual.get(1).getTransactions());

    }

    @Test
    void find_user_by_email_repository(){
        userRepositorySubject.saveAll(users);
        User actualMitsoa = userRepositorySubject.findByEmail("hei.mitsoa@gmail.com");
        User actualMegane = userRepositorySubject.findByEmail("hei.megane@gmail.com");

        assertNotNull(actualMitsoa);
        assertNotNull(actualMegane);
        assertEquals(actualMitsoa.getFullName(), users.getFirst().getFullName());
        assertEquals(actualMegane.getFullName(), users.get(1).getFullName());
        assertEquals(actualMitsoa.getEmail(), users.getFirst().getEmail());
        assertEquals(actualMegane.getEmail(), users.get(1).getEmail());
    }

    @Test
    void return_null_if_no_email_repository(){
        assertNull(userRepositorySubject.findByEmail("dummy@gmail.com"));

    }

    @Test
    void delete_user_by_email_repository(){
        userRepositorySubject.saveAll(users);

        userRepositorySubject.deleteByEmail("hei.mitsoa@gmail.com");
        userRepositorySubject.deleteByEmail("hei.megane@gmail.com");

        assertEquals(userRepositorySubject.findAll(1,2),List.of());
    }

    @Test
    void update_user_by_email_repository() throws UserNotFoundException {
        userRepositorySubject.saveAll(users);
        var userMitsoa = userRepositorySubject.findByEmail("hei.mitsoa@gmail.com");
        var userMegane = userRepositorySubject.findByEmail("hei.megane@gmail.com");

        userServiceSubject.updateByEmail(
                userMitsoa.getEmail(),
                new UserToUpdate(
                        "RAJAONAH Mitsoa Allan",
                        LocalDate.of(2003,7,7)
                )
        );
        userServiceSubject.updateByEmail(
                userMegane.getEmail(),
                new UserToUpdate(
                        "RAJAONAH Alisoa Gane",
                        LocalDate.of(2011,2,24)
                )
        );
        assertNotNull(userMitsoa);
        assertNotNull(userMegane);
        assertEquals("RAJAONAH Mitsoa Allan",userRepositorySubject.findByEmail(userMitsoa.getEmail()).getFullName());
        assertEquals("RAJAONAH Alisoa Gane",userRepositorySubject.findByEmail(userMegane.getEmail()).getFullName());
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

    @AfterEach
    void clear() throws SQLException {
        var dataSource = new Datasource();
        try(Connection conn = dataSource.getConnection();
            Statement ps = conn.createStatement();
        ){
            ps.execute("delete from \"user\"");
            ps.execute("alter sequence user_id_seq restart ");
        }
    }
}
