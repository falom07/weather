package org.example.demo.Service;

import org.example.demo.DTO.UserSignInDTO;
import org.example.demo.DTO.UserSignUpDTO;
import org.example.demo.Entity.UserEntity;
import org.example.demo.Mapper.MapperUser;
import org.example.demo.Repository.UserRepository;
import org.example.demo.Validate.ValidateSignUp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

@Service
@Transactional
public class SignService {

    private final ValidateSignUp validateSignUp;
    private final UserRepository userRepository;
    private final PasswordService passwordService;

    @Autowired
    public SignService(ValidateSignUp validateSignUp, UserRepository userRepository, PasswordService passwordService) {
        this.userRepository = userRepository;
        this.validateSignUp = validateSignUp;
        this.passwordService = passwordService;
    }

    public boolean isSignUpFormCorrect(BindingResult bindingResult, UserSignUpDTO user) {
        try {
            if (validateSignUp.formHasErrors(bindingResult, user)) {
                return false;
            } else if (!isUniqueUsername(user)) {
                bindingResult.rejectValue("login", "user.login", "User with the sane username already exists");
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isSignInFormCorrect(BindingResult bindingResult, UserSignInDTO userDTO) {

        if(null != userDTO.getLogin()){
            UserEntity user = userRepository.getUserByUserName(userDTO.getLogin());
            if(null != user){
                if(passwordService.checkPassword(userDTO.getPassword(), user.getPassword())){
                    return true;
                }
            }
        }

        bindingResult.rejectValue("login", "user.login", "Login or password is incorrect");
        return false;
    }

    public Long saveUser(UserSignUpDTO user) {
        try {
            UserEntity newUserEntity = MapperUser.mapSignUpUser(user);
            String hashedPassword = passwordService.hashPassword(user.getPassword());
            newUserEntity.setPassword(hashedPassword);

            return userRepository.save(newUserEntity);
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    protected boolean isUniqueUsername(UserSignUpDTO user) {
        if(userRepository.existByLogin(user.getLogin())){
            return false;
        }else {
            return true;
        }

    }

    public Long getUserByLogin(String login) {
        UserEntity userEntity = userRepository.getUserByUserName(login);
        return userEntity.getId();
    }
}
// for(String username : listOfUser) {
//            System.out.println(username);
//            if(username.equals(user.getLogin())) {
//                return false;
//            }
//        }
//        return true;