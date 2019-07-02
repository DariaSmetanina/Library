package action.common;

import action.Dispatcher;
import db.DB;
import javafx.util.Pair;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;

public class CheckUser extends Dispatcher {
    public String getServletInfo(){
        return "Registration servlet";
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
            DB db = new DB();
            response.setContentType("text/plain;charset=UTF-8;");
            response.setCharacterEncoding("UTF-8");
            String page="";
            if
            (!db.login(request.getParameter("login"),request.getParameter("password"))){
                System.out.print("err ");

                String content = "";
                response.setContentType("text/plain");

                OutputStream outStream = response.getOutputStream();
                outStream.write(content.getBytes("UTF-8"));
                outStream.flush();
                outStream.close();

                //page="Login\\errorLoginPage.jsp";

            } else {
                //this.forward("/action.common.MainPage", request, response);
                System.out.print("corr ");
                page="Login\\LoginPage.jsp";

                Pair<String, Pair<Integer,Boolean>> p=db.getUserInfo(request.getParameter("login"));

                HttpSession session = request.getSession();
                session.setAttribute("idUser", p.getValue().getKey());
                session.setAttribute("status", p.getValue().getValue());
                session.setAttribute("nameUser", p.getKey());
                if(p.getValue().getValue()){
                    session.setAttribute("textstatus", "Библиотекарь");
                }
                else{
                    session.setAttribute("textstatus", "Читатель");
                }

                String content = "/webapp_war_exploded/MainPage";
                response.setContentType("text/plain");

                OutputStream outStream = response.getOutputStream();
                outStream.write(content.getBytes("UTF-8"));
                outStream.flush();
                outStream.close();

    }
        db.exit();
        RequestDispatcher view=request.getRequestDispatcher(page);
        view.forward(request, response);
    }
}