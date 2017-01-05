package Service;

import Database.Database;
import Database.People;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by YocyTang on 2017/1/3.
 */
public class Edit extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Access-Control-Allow-Origin","*");
        BufferedReader bufferedReader = new BufferedReader(request.getReader());
        String data = bufferedReader.readLine();
        if(data == null|| data.length() == 0){
            response.sendError(400);
            return;
        }
        Gson gson = new Gson();
        People people = gson.fromJson(data,People.class);
        Database database  = new Database("cute");
        String sql = "update manager set sex=? , age= ?, tel=?, address=?,email=?,descript=? where username=?";
        PreparedStatement preparedStatement = database.getPreparedStatement(sql);
        int flag= 0;
        try {
            preparedStatement.setString(1,people.getSex());
            preparedStatement.setInt(2,people.getAge());
            preparedStatement.setString(3, people.getTel());
            preparedStatement.setString(4, people.getAddress());
            preparedStatement.setString(5,people.getEmail());
            preparedStatement.setString(6,people.getDescript());
            preparedStatement.setString(7,people.getUsername());
            flag = preparedStatement.executeUpdate();
            if(flag>0){
                return;
            }
        }catch (SQLException e){
            e.printStackTrace();
            response.setStatus(500);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
