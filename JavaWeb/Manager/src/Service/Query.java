package Service;


import Database.People;
import Database.Database;
import com.google.gson.Gson;
import Error.ErrorInfo;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static Utils.Print.print;

/**
 * Created by YocyTang on 2017/1/3.
 * ajax /manager/query?page=1
 */
public class Query extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Access-Control-Allow-Origin","*");
        PrintWriter writer = response.getWriter();
        int page = (Integer.parseInt(request.getParameter("page"))-1)*10;
        int limit = 10;


        Gson gson = new Gson();
        Database database = new Database("cute");
        String json = null;
        String sql = "select * from manager limit ?, 10";

        PreparedStatement preparedStatement = database.getPreparedStatement(sql);
        List<People>  res = new ArrayList<People>();
        ResultSet resultSet = null;
        try{
            preparedStatement.setInt(1,page);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                String username = resultSet.getString("username");
                String tel = resultSet.getString("tel");
                String sex=resultSet.getString("sex");
                String address= resultSet.getString("address");
                String email = resultSet.getString("email");
                int age = resultSet.getInt("age");
                String descript = resultSet.getString("descript");
                People people = new People(username,sex,tel,email,age,address,descript);
                res.add(people);


            }
            json = gson.toJson(res);
        }catch (SQLException e){
            ErrorInfo errorInfo = new ErrorInfo("query failed");
            json = gson.toJson(errorInfo);
        }
        if(json == null){
            ErrorInfo errorInfo = new ErrorInfo("not foud");
            json = gson.toJson(errorInfo);
        }
        print(json);
        if(resultSet!=null){
            database.close(resultSet);
        }

        writer.print(json);

}
}
