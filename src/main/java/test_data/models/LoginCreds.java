package test_data.models;

public class LoginCreds {
    private String email;
    private String password;

    @Override
    public String toString() {
        return "LoginCreds{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

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
