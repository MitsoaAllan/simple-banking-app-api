package com.mitsoa.banking.service;

import com.mitsoa.banking.endpoint.rest.mapper.UserToUpdateMapper;
import com.mitsoa.banking.endpoint.rest.model.UserToUpdate;
import com.mitsoa.banking.exception.UserNotFoundException;
import com.mitsoa.banking.model.User;
import com.mitsoa.banking.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserToUpdateMapper userToUpdateMapper;

    public List<User> saveAll(List<User> users){
        return userRepository.saveAll(users);
    }

    public List<User> findAll(Integer page, Integer size) {
        return userRepository.findAll(page,size);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public String deleteByEmail(String email) {
        return userRepository.deleteByEmail(email);
    }

    public User updateByEmail(String email, UserToUpdate userToUpdate) throws UserNotFoundException {
        var user = userRepository.findByEmail(email);
        if (user != null) {
            return userRepository.updateById(user.getId(),userToUpdateMapper.toUser(userToUpdate,email));
        }else{
            throw new UserNotFoundException("user with email "+email+" not found");
        }
    }
}
