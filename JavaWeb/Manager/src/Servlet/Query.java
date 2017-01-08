package Servlet;
import Bean.People;
import Dao.PeopleDao;
import com.google.gson.Gson;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
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
}
