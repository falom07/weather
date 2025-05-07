package org.example.demo.Service;


import org.example.demo.Repository.TestRepository;
import org.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
//
//    @Transactional
//    public UserEntity getOne() {
//        return userRepository.findById(1L);
//
//    }
//
//    @Transactional(readOnly = true)
//    public List<UserEntity> getAllUsers() {
//        return userRepository.findAll();
//    }

    public String getLoginByID(String loginID) {
        return userRepository.getLoginByID(loginID);
    }
}

