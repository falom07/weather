package org.example.demo.Validate;

import org.example.demo.DTO.UserSignUpDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

@Component
public class ValidateSignUp {

    public boolean formHasErrors(BindingResult bindingResult, UserSignUpDTO user) {
        if (bindingResult.hasErrors()) {
            return true;
        }else if(!user.getPassword().equals(user.getRePassword())){
            bindingResult.rejectValue("password", "user.password","Your passwords do not match");
            return true;
        }else if(!nameHasCorrectBeginning(user.getLogin())){
            bindingResult.rejectValue("login", "user.login","Username can begin only from chapter");
            return true;
        }else {
            return false;
        }
    }

    private boolean nameHasCorrectBeginning(String login) {
        String alphabet = "qwertyuioplkjhgfdsazxcvbnm";
        char firstLetter = login.toLowerCase().charAt(0);
        for (int i = 0; i < alphabet.length(); i++) {
            if(firstLetter == alphabet.charAt(i)){
                return true;
            }
        }
        return false;
    }


}
