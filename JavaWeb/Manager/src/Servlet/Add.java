package Servlet;
import Bean.People;
import Dao.PeopleDao;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;

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
        boolean flag = false;
        PeopleDao dao = new PeopleDao();
        try {
            flag= dao.add(people);
        }catch (SQLException e){
            response.sendError(500);
        }
        if(flag == false){
            response.sendError(400);
        }else {
            response.setStatus(200);
        }

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
