package test_data.models;

public class LoginCred {
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public LoginCreds(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
