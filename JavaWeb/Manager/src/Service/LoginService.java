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
    //用户登录时，将UserDao设置为static
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
        //如果没有该用户，则告诉前端用户名不存在
        if(user == null){
            return false;
        }
        //获取密码的hash
        hashPasswd = user.getPassword();
        return true;
    }
    public boolean query(){
        return query(user);
    }
    //验证密码
    private boolean isValidPassword(String passwd, User user){


        return HashPassword.checkHash(passwd, hashPasswd);
    }
    public boolean isValidPassword(String passwd){
        return  isValidPassword(passwd, user);
    }


}
