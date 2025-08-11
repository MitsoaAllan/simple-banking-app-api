package com.mitsoa.banking.service;

import com.mitsoa.banking.model.User;
import com.mitsoa.banking.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> saveAll(List<User> users){
        return userRepository.saveAll(users);
    }
}
