package ch.fhnw.webeC.model.web;

import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class UserData implements Serializable {
    @Email(message = "Email is not valid!")
    private String email;

    @NotEmpty(message = "Username can not be empty!")
    private String userName;

    @NotEmpty(message = "Password can not be empty!")
    @Transient
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

