package Servlet;

import Bean.People;
import Dao.PeopleDao;
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

        BufferedReader bufferedReader = new BufferedReader(request.getReader());
        String data = bufferedReader.readLine();
        if(data==null||data.length() == 0){
            response.sendError(400);
        }

        Gson gson = new Gson();
        People people = gson.fromJson(data, People.class);
        boolean flag = false;
        PeopleDao dao = new PeopleDao();
        try{
            flag = dao.delete(people);
        }catch (SQLException e){
            response.sendError(500);
        }
        if(!flag){
            response.sendError(400);
        }else {
            response.setStatus(200);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
