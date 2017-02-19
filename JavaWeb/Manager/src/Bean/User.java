package Bean;
import static Utils.HashPassword.getHash;
import static Utils.Replace.*;
/**
 * Created by YocyTang on 2017/1/10.
 * 用户
 */
public class User {
    private String password;
    private String username;
    private String email;
    private String newPassword;

    public User(String username, String password, String email){
        username = replace(username);
        email = replace(email);
        this.username = username;
        this.password = password;
        this.email  = email;
    }
    //hash密码，用于保存在数据库之中
    public void hashPassword(){
            this.password = getHash(this.password);

    }
    public User(String username, String password, String email, String newPassword){
        this(username,password,email);
        newPassword = getHash(newPassword);
        this.newPassword = newPassword;
    }
    //用于由数据库中读出的数据构建User对象，此时并不需要hash处理密码，
    // 用于拿出来验证hash是否与传过来的密码相符合
    public User(String username, String password){
        this.username = username;
        this.password = password;
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
    //修改密码所用
    public void setPassword(String password) {
        password = getHash(password);
        this.password = password;
    }

}
