package Servlet;

import Bean.People;
import Dao.PeopleDao;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by YocyTang on 2017/1/23.
 */
public class UserApi extends HttpServlet {
    /**前端POST格式：var data = {"username":"username","tel":"21321", "email":"sa@qe.com".....}
    *             xhr.open("post","manager/user",true)
    *              xhr.send(JSON.stringify(data));
    *              xhr.open("delete","manager/user",true)
     *              xhr.open("put","manager/user",true)
     *              get时参数请携带在URL中
    */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Access-Control-Allow-Origin","*");
        PrintWriter writer = response.getWriter();
        Integer page = null;
        Integer limit = null;
        String username = null;
        if(request.getParameter("page")!=null){
            page = Integer.parseInt(request.getParameter("page"));
        }
        if(request.getParameter("limit")!=null){
            limit = Integer.parseInt(request.getParameter("limit"));
        }

        if(request.getParameter("username")!=null){
            username = (String)request.getParameter("username");
        }

        Gson gson = new Gson();
        String json = null;
        PeopleDao dao = new PeopleDao();
        if(page!=null&&limit!=null&&username==null){
            List<People> res = dao.query(page,limit);
            json = gson.toJson(res);

        }else if(page!=null&&limit == null&&username==null){
            List<People> res= dao.query(page);
            json = gson.toJson(res);


        }else if(username!=null){
            People people = new People(username);
            people = dao.query(people);
            json = gson.toJson(people);

        }else {
            response.sendError(400);
        }

        PrintWriter out = response.getWriter();
        out.print(json);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        PeopleDao dao = new PeopleDao();
        boolean flag = false;
        try{
            flag = dao.edit(people);
        }catch (SQLException e){
            response.sendError(500);
        }
        if(!flag){
            response.sendError(400);
        }else{
            response.setStatus(200);
        }
    }

}
