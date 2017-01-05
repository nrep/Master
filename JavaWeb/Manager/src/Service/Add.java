package Service;

import Database.Database;
import Database.People;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import  Error.*;

import com.google.gson.Gson;



/**
 * Created by YocyTang on 2017/1/3.
 */
public class Add extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //前端POST格式：var data = {"username":"username","tel":"21321", "email":"sa@qe.com".....}
        //              xhr.open("post","manager/add",true)
        //              xhr.send(JSON.stringify(data));
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin","*");
        BufferedReader bufferedReader = request.getReader();
        Gson gson = new Gson();
        String a =bufferedReader.readLine();
        People people= gson.fromJson(a,People.class);
        if(people.getUsername()!=null && people.getUsername()!=""){
            String querySql = "select * from manager where username = ?";

            String sql = "insert into manager (username, sex, tel, email,age, address, descript) values (?,?,?,?,?,?,?)";
            Database database = new Database("cute");

            PreparedStatement preparedStatement = database.getPreparedStatement(querySql);
            ResultSet resultSet = null;
            try{
                preparedStatement.setString(1,people.getUsername());
                resultSet = preparedStatement.executeQuery();
                if(resultSet.next()){
                    throw new SQLException();
                }
            }catch (SQLException e){
                ErrorInfo errorInfo = new ErrorInfo("username has been registed");
                PrintWriter writer = response.getWriter();
                writer.print(gson.toJson(errorInfo));
                response.setStatus(202);
                return;
            }
            preparedStatement = database.getPreparedStatement(sql);
            int flag = 0;
            try {
                preparedStatement.setString(1,people.getUsername());
                preparedStatement.setString(2,people.getSex());
                preparedStatement.setString(3,people.getTel());
                preparedStatement.setString(4,people.getEmail());
                preparedStatement.setInt(5,people.getAge());
                preparedStatement.setString(6,people.getAddress());
                preparedStatement.setString(7, people.getDescript());
                flag= preparedStatement.executeUpdate();
                if(flag>0){
                    response.setStatus(200);
                }
            }catch (SQLException e){
                e.printStackTrace();
                response.sendError(500);
            }

        }else{
            response.sendError(400);
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
