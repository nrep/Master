package Dao;

import Bean.User;
import Database.Database;
import groovy.sql.Sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by YocyTang on 2017/1/10.
 */
public class UserDao {
    Database database;
    public UserDao(){
        database = new Database("cute");
    }
    public boolean add(User user) throws SQLException{
        String username = user.getUsername();
        String password = user.getPassword();
        String email = user.getEmail();
        String sql = "insert into users (username, password, email) values (?,?,?)";
        PreparedStatement preparedStatement = database.getPreparedStatement(sql);
        int flag = 0;
        try{
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            preparedStatement.setString(3,email);
            flag = preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            throw new SQLException(e);
        }
        if(preparedStatement!=null){
            preparedStatement.close();
        }
        if(flag>0){
            return true;
        }
        return  false;
    }
    public boolean delete(User user) throws SQLException{
        String username = user.getUsername();
        String sql = "delete from users where username = ?";
        int flag = 0;
        PreparedStatement preparedStatement =database.getPreparedStatement(sql);
        try {
            preparedStatement.setString(1,username);
            flag = preparedStatement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
            throw new SQLException(e);
        }
        if(preparedStatement!=null){
            preparedStatement.close();
        }
        if(flag>0){
            return true;
        }
        return  false;
    }
    public User query(User user){
        String username = user.getUsername();
        String sql = "select * from users where username = ?";
        ResultSet resultSet = null;
        User res = null;
        PreparedStatement preparedStatement = database.getPreparedStatement(sql);
        try{
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                res = new User(username, password, password);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return res;
    }
    public List<User> query(int page, int limit){
        int start = (page-1)*limit;
        String sql = "select * from users limit ?,?";
        List<User> users = new ArrayList<User>();
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = database.getPreparedStatement(sql);
        try{
            preparedStatement.setInt(1, start);
            preparedStatement.setInt(2, limit);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String username = resultSet.getString("username");
                String email = resultSet.getString("email");
                User user = new User(username, email);
                users.add(user);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return users;
    }
    public boolean edit(User user) throws SQLException{
        String username = user.getUsername();
        String password = user.getPassword();
        String email = user.getEmail();
        String sql = "update users set password = ?,email=? where username = ?";
        PreparedStatement preparedStatement = database.getPreparedStatement(sql);
        int flag = 0;
        try{
            preparedStatement.setString(1, password);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, username);
            flag = preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            throw new SQLException();
        }
        return flag>0;
    }


}
