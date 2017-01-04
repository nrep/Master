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

import static Utils.Print.print;

/**
 * Created by YocyTang on 2017/1/3.
 */
public class Query extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Access-Control-Allow-Origin","*");
        PrintWriter writer = response.getWriter();
        int page = (Integer.parseInt(request.getParameter("page"))-1)*10;
        int limit = 10;
        print("json");

        Gson gosn = new Gson();
        Database database = new Database("cute");
        String json = null;
        String sql = "select * from manager limit "+page+", 10";
        PreparedStatement preparedStatement = database.getPreparedStatement(sql);
        ResultSet resultSet = null;
        try{
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                String username = resultSet.getString("username");
                String tel = resultSet.getString("tel");
                String sex=resultSet.getString("sex");
                String address= resultSet.getString("address");
                int age = resultSet.getInt("age");
                String descript = resultSet.getString("descript");
                People people = new People(username,sex,tel,age,address,descript);
                json = gosn.toJson(people);

            }
        }catch (SQLException e){
            ErrorInfo errorInfo = new ErrorInfo("query failed");
            json = gosn.toJson(errorInfo);
        }
        if(json == null){
            ErrorInfo errorInfo = new ErrorInfo("not foud");
            json = gosn.toJson(errorInfo);
        }
        print(json);
        if(resultSet!=null){
            database.close(resultSet);
        }

        writer.print(json);

}
}
