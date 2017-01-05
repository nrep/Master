package Service;

import Database.*;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by YocyTang on 2017/1/3.
 */
public class Delete extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        response.setHeader("Access-Control-Allow-Origin","*");
        PrintWriter out = response.getWriter();
        BufferedReader bufferedReader = new BufferedReader(request.getReader());
        String data = bufferedReader.readLine();
        if(data==null||data.length() == 0){
            response.sendError(400);
        }
        Database database = new Database("cute");
        Gson gson = new Gson();
        People people = gson.fromJson(data, People.class);
        String sql = "delete from manager where username = ?";
        PreparedStatement preparedStatement = database.getPreparedStatement(sql);
        int flag  = 0;
        try{
            preparedStatement.setString(1,people.getUsername());
            flag = preparedStatement.executeUpdate();
            if(flag>0){
                return;
            }
        }catch (SQLException e){
            response.sendError(500);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
