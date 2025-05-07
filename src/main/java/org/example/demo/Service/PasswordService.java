package org.example.demo.Service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {

    public boolean checkPassword(String passwordFromPage, String passwordFromDB) {
        return BCrypt.checkpw(passwordFromPage, passwordFromDB);
    }

    public String hashPassword(String passwordFromPage) {
        return BCrypt.hashpw(passwordFromPage, BCrypt.gensalt());
    }
}
