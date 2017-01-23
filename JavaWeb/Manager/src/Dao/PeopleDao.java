package Dao;

import Bean.People;
import Database.Database;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



/**
 * Created by YocyTang on 2017/1/8.
 */
public class PeopleDao {
    Database database ;
    public PeopleDao(){
        database = new Database("cute");
    }
    public boolean add(People people) throws SQLException{
        String username = people.getUsername();
        String sex = people.getSex();
        Integer age = people.getAge();
        String address = people.getAddress();
        String descript = people.getDescript();
        String tel = people.getTel();
        String email = people.getEmail();
        String admin = people.getAdmin();
        if(username==null|| sex == null|| age == null|| address==null||tel==null||email ==null){
            return false;
        }
        String sql= "insert into manager (username, sex, age, address, descript, tel, email,admin) values (?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement= database.getPreparedStatement(sql);
        int flag = 0;
        try {
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,sex);
            preparedStatement.setInt(3,age);
            preparedStatement.setString(4,address);
            preparedStatement.setString(5,descript);
            preparedStatement.setString(6,tel);
            preparedStatement.setString(7,email);
            preparedStatement.setString(8,admin);
            flag = preparedStatement.executeUpdate();
            if(flag>0){
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
            throw new SQLException(e);
        }
        return false;
    }
    public boolean delete(People people) throws SQLException{
        String username = people.getUsername();

        if(username == null) return false;

        String sql = "delete from manager where username = ?";
        PreparedStatement preparedStatement = database.getPreparedStatement(sql);
        int flag = 0;
        try {
            preparedStatement.setString(1, username);
            flag = preparedStatement.executeUpdate();
            if(flag>0){
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
            throw new SQLException(e);
        }
        return false;
    }
    public People query(People people){
        String username = people.getUsername();
        if(username == null){
            return  null;
        }

        String sql = "select * from manager where username = ?";
        PreparedStatement preparedStatement = database.getPreparedStatement(sql);
        ResultSet resultSet = null;
        try{
            preparedStatement.setString(1,username);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){

                int age = resultSet.getInt("age");
                String sex = resultSet.getString("sex");
                String address = resultSet.getString("address");
                String email = resultSet.getString("email");
                String tel = resultSet.getString("tel");
                String descript = resultSet.getString("descript");

                people.setAddress(address);
                people.setAge(age);
                people.setSex(sex);
                people.setDescript(descript);
                people.setEmail(email);
                people.setTel(tel);

            }else{
                people =null;
            }

        }catch (SQLException e){
            e.printStackTrace();
            people= null;
        }
        if(resultSet!=null){
            database.close(resultSet);
        }
        return people;
    }
    public List<People> query(int page, int limit){
        int index = (page-1)*limit;
        String sql = "select * from manager limit ?,?";
        PreparedStatement preparedStatement= database.getPreparedStatement(sql);
        List<People> res = new ArrayList<People>();
        ResultSet resultSet = null;
        try{
            preparedStatement.setInt(1, index);
            preparedStatement.setInt(2,limit);
            resultSet = preparedStatement.executeQuery();
            People people = null;
            while (resultSet.next()){
                String username = resultSet.getString("username");
                String sex = resultSet.getString("sex");
                String address = resultSet.getString("address");
                String tel = resultSet.getString("tel");
                String email = resultSet.getString("email");
                String descript = resultSet.getString("descript");
                String admin = resultSet.getString("admin");
                int age = resultSet.getInt("age");
                people = new People(username,sex,tel,email,age,address,descript,admin);
                res.add(people);
            }
        }catch (SQLException e){
            e.printStackTrace();

            res = null;
        }
        if(resultSet!=null){
            database.close(resultSet);
        }
        return  res;
    }
    public List<People> query(int page){
        return  query(page,10);
    }

    public boolean edit(People people) throws SQLException{
        String username = people.getUsername();
        String sex  = people.getSex();
        String email = people.getEmail();
        String address = people.getAddress();
        String tel = people.getTel();
        String descript = people.getDescript();
        Integer age = people.getAge();
        String admin =people.getAdmin();
        if(username == null||sex==null||age == null||tel == null||email==null||address==null){
            return false;
        }
        String sql = "update manager set sex =?, age = ?, email = ?, tel=?,address=?,descript=?,admin=?where username=?";
        PreparedStatement preparedStatement = database.getPreparedStatement(sql);
        int flag = 0;
        try{
            preparedStatement.setString(1, sex);
            preparedStatement.setInt(2,age);
            preparedStatement.setString(3,email);
            preparedStatement.setString(4,tel);
            preparedStatement.setString(5,address);
            preparedStatement.setString(6,descript);
            preparedStatement.setString(8,username);
            preparedStatement.setString(7,admin);
            flag = preparedStatement.executeUpdate();
            preparedStatement.close();
            if(flag>0){

                return true;
            }
            return false;
        }catch (SQLException e){
            e.printStackTrace();
            throw new SQLException(e);
        }
    }

}
