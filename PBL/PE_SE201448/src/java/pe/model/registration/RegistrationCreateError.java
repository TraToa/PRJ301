/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.model.registration;

/**
 *
 * @author TGDD-MSI
 */
public class RegistrationCreateError {
    private String usernameLengthErr;
    private String passwordLengthErr;
    private String fullNameLengthErr;
    private String confirmNotMatched;
    private String usernameExisted;

    public String getUsernameLengthErr() {
        return usernameLengthErr;
    }

    public String getPasswordLengthErr() {
        return passwordLengthErr;
    }

    public String getFullNameLengthErr() {
        return fullNameLengthErr;
    }

    public String getConfirmNotMatched() {
        return confirmNotMatched;
    }

    public String getUsernameExisted() {
        return usernameExisted;
    }

    public void setUsernameLengthErr(String usernameLengthErr) {
        this.usernameLengthErr = usernameLengthErr;
    }

    public void setPasswordLengthErr(String passwordLengthErr) {
        this.passwordLengthErr = passwordLengthErr;
    }

    public void setFullNameLengthErr(String fullNameLengthErr) {
        this.fullNameLengthErr = fullNameLengthErr;
    }

    public void setConfirmNotMatched(String confirmNotMatched) {
        this.confirmNotMatched = confirmNotMatched;
    }

    public void setUsernameExisted(String usernameExisted) {
        this.usernameExisted = usernameExisted;
    }

    
}
