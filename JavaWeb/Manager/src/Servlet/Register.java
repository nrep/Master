package Servlet;

import Bean.ErrorInfo;
import Bean.User;
import Service.RegisterService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by YocyTang on 2017/1/10.
 */
public class Register extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

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
        }else{
            PrintWriter out = response.getWriter();
            ErrorInfo errorInfo = new ErrorInfo("用户名已经被注册");
            String json = gson.toJson(errorInfo);
            out.print(json);
        }

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
