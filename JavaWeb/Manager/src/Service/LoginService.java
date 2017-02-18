package Service;

import Bean.People;
import Bean.User;
import Dao.UserDao;
import Utils.HashPassword;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static Utils.Print.print;

/**
 * Created by YocyTang on 2017/1/11.
 */
public class LoginService {
    private User user;
    private static  UserDao userDao;
    private String hashPasswd;
    public LoginService(User user){
        this.user = user;
        if(userDao == null){
            this.userDao = new UserDao();
        }
    }
    private boolean query(User user){
        user = userDao.query(user);
        if(user == null){
            return false;
        }
        hashPasswd = user.getPassword();
        return true;
    }
    public boolean query(){
        return query(user);
    }

    private boolean isValidPassword(String passwd, User user){

        System.out.println(hashPasswd);
        System.out.print(passwd);
        return HashPassword.checkHash(passwd, hashPasswd);
    }
    public boolean isValidPassword(String passwd){
        return  isValidPassword(passwd, user);
    }


}
