package Service;

import Bean.User;
import Dao.UserDao;

import java.sql.SQLException;
import java.util.List;

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
        if(query()){
            return false;
        };
        userDao = new UserDao();
        user.hashPassword();
        try{
            userDao.add(user);
        }catch (SQLException e){
            e.printStackTrace();
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
    public List<User> query(int page, int limit){
        List<User> res = userDao.query(page, limit);
        return res;
    }
    public List<User> query(int page){
        List<User> res = query(page, 10);
        return res;
    }
    public boolean query(){
        User res = userDao.query(user);
        if(res==null){
            return false;
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
