package validation;

import exception.LoginException;

public interface ValidationAuth {
    void validationPassword(String passOrUserName) throws LoginException;

    void validationPassSamePass2(String pass, String pass2) throws LoginException;

    void validationName(String passOrUserName) throws LoginException;

    boolean validationEmail(String email) throws LoginException;

    boolean validationSQL(Exception e) throws LoginException;
}
