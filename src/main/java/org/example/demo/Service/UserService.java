package org.example.demo.Service;


import org.example.demo.Entity.User;
import org.example.demo.Repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    private final TestRepository userRepository;

    @Autowired
    public UserService(TestRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User getOne() {
        return userRepository.findById(1L);

    }

    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}

