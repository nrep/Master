package Servlet;

import Bean.ErrorInfo;
import Bean.JsonToken;
import Bean.User;
import Service.LoginService;
import Utils.Token;
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
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        BufferedReader bufferedReader = request.getReader();
        Gson gson = new Gson();
        String data = bufferedReader.readLine();
        User user = gson.fromJson(data, User.class);
        if(user.getUsername() == null||user.getPassword()==null){
            response.sendError(400);
        }

        LoginService loginService = new LoginService(user);
        boolean isValidUsername = loginService.query();
        PrintWriter out = response.getWriter();
        if(!isValidUsername){

            ErrorInfo errorInfo = new ErrorInfo("用户名不存在");
            String json = gson.toJson(errorInfo);
            out.print(json);
            return;
        }
        String password = user.getPassword();
        boolean isValidPassword = loginService.isValidPassword(password);
        if(!isValidPassword){
            ErrorInfo errorInfo = new ErrorInfo("密码错误");
            String json = gson.toJson(errorInfo);
            out.print(json);
            return;
        }
        Token token = new Token(user.getUsername());
        String strToken = token.getToken();
        JsonToken jsonToken = new JsonToken(strToken);
        String json = gson.toJson(jsonToken);
        out.print(json);
        return;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
