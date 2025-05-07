package org.example.demo.Mapper;

import org.example.demo.DTO.UserSignInDTO;
import org.example.demo.DTO.UserSignUpDTO;
import org.example.demo.Entity.UserEntity;

public class MapperUser {
    public static UserEntity mapSignUpUser(UserSignUpDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setLogin(userDTO.getLogin());
        userEntity.setPassword(userDTO.getPassword());
        return userEntity;
    }

    public static UserEntity mapSignInUser(UserSignInDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setLogin(userDTO.getLogin());
        userEntity.setPassword(userDTO.getPassword());
        return userEntity;
    }

}
