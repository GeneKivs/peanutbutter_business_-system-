package com.peanutbutter.peanutbutter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peanutbutter.peanutbutter.model.User;
import com.peanutbutter.peanutbutter.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void defineUser(User user){
        userRepository.save(user);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public void updateUser(User user){
        userRepository.save(user);
    }

    public void deleteUserByID(int userID){
        userRepository.deleteById(userID);
    }
}
