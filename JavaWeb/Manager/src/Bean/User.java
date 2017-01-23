package Bean;
import static Utils.HashPassword.getHash;
import static Utils.Replace.*;
/**
 * Created by YocyTang on 2017/1/10.
 */
public class User {
    private String password;
    private String username;
    private String email;
    private String newPassword;
    public User(String username, String password, String email){
        username = replace(username);
        email = replace(email);
        password = getHash(password);
        this.username = username;
        this.password = password;
        this.email  = email;
    }
    public User(String username, String password, String email, String newPassword){
        this(username,password,email);
        newPassword = getHash(newPassword);
        this.newPassword = newPassword;
    }
    public User(String username, String email){
        this.email = email;
        this.username = username;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        password = getHash(password);
        this.password = password;
    }

}
