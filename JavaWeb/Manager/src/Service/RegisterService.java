package Service;

import Bean.User;
import Dao.UserDao;

import java.sql.SQLException;

/**
 * Created by YocyTang on 2017/1/23.
 */
public class RegisterService {
    private User user;
    private UserDao userDao;
    public RegisterService(User user){
        this.user = user;
        this.userDao = new UserDao();
    }
    public boolean add(){
        try{
            userDao.add(user);
        }catch (SQLException e){
            return false;
        }
        return true;
    }
    public boolean delete(){
        try{
            userDao.delete(user);
        }catch (SQLException e){
            return  false;
        }
        return true;
    }
    public boolean updatePassword(String password){
        user.setPassword(password);
        try{
            userDao.edit(user);
        }catch (SQLException e){
            return false;
        }
        return true;
    }
}
