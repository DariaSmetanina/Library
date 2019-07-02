package action.common;

import action.Dispatcher;
import db.DB;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;


public class BookPage extends Dispatcher {
    public String getServletInfo() {
        return "Registration servlet";
    }

    public void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8;");
        response.setCharacterEncoding("UTF-8");
        DB db=new DB();

        int id=Integer.parseInt(request.getParameter("id"));
        request.setAttribute("idBook", id);

        Boolean act=TRUE;

        //request.getSession().getAttribute("idUser");
        try {
            if (Boolean.parseBoolean(request.getSession().getAttribute("status").toString())) {

                if(db.bookTaken(id)!=0) {
                    act=FALSE;
                }
                else{
                    request.setAttribute("todo","DelBook?id="+id);
                }
                request.setAttribute("btn","Удалить книгу");
            }
            else{
                if(db.bookTaken(id)==0){
                    request.setAttribute("btn","Взять книгу");
                    request.setAttribute("todo","TakeBook?id="+id);
                }
                else{
                    if(db.bookTaken(id)==Integer.parseInt((request.getSession().getAttribute("idUser").toString()))){
                        request.setAttribute("btn","Вернуть книгу");
                        request.setAttribute("todo","RetBook?id="+id);
                    }
                    else{
                        request.setAttribute("btn","Взять книгу");
                        act=FALSE;
                    }
                }
            }
        }
        catch (Exception e){}

        if(!act){
            request.setAttribute("actbtn"," disabled");
        }
        else{
            request.setAttribute("actbtn","");
        }

        db.exit();
        RequestDispatcher view=request.getRequestDispatcher("BookPage.jsp");
        view.forward(request, response);
    }
}
