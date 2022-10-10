package org.example.registration.inter;


import org.example.registration.inter.exception.LoginException;
import org.example.registration.user.User;

import java.io.File;


public interface CheckUserInterface {
    boolean IsExistUser(User user, File file) throws LoginException;
    void setExistUser(boolean exist);
    void isValidationPasswordOrUserName(String passOrUserName) throws LoginException;
    void isValidationEmail(String email) throws LoginException;
    void IsExistUserUserName(User user, File file)throws LoginException;
    void IsExistUserPassword(User user, File file) throws LoginException;
    void IsExistUserEmail(User user, File file) throws LoginException;
}
