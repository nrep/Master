package Servlet;

import Bean.User;
import Service.RegisterService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by YocyTang on 2017/1/10.
 */
public class Register extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader bufferedReader = request.getReader();
        Gson gson = new Gson();
        String data = bufferedReader.readLine();
        User  user = gson.fromJson(data, User.class);
        if(user.getUsername()==null|| user.getUsername()==""||user.getPassword() == null||user.getPassword() ==""||user.getEmail()==null||user.getEmail()==""){
            response.sendError(400);
            return;
        }
        RegisterService  registerService = new RegisterService(user);
        boolean flag = registerService.add();
        if(flag){
            response.setStatus(200);
            return;
        }
        response.sendError(500);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
        BufferedReader bufferedReader = req.getReader();
        String data = bufferedReader.readLine();
        Gson gson = new Gson();
        User user = gson.fromJson(data, User.class);
        RegisterService registerService = new RegisterService(user);
        
    }
}
