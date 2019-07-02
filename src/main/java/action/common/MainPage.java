package action.common;

import action.Dispatcher;
import db.DB;
import model.Book;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class MainPage extends Dispatcher {
        public String getServletInfo(){
            return "Registration servlet";
        }

        public void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
            response.setContentType("text/html;charset=UTF-8;");
            response.setCharacterEncoding("UTF-8");

            boolean status=Boolean.parseBoolean(request.getSession().getAttribute("status").toString());
            request.setAttribute("name",request.getSession().getAttribute("nameUser").toString());
            if(status){
                request.setAttribute("stat","Библиотекарь");
                request.getSession().setAttribute("btn","Добавить книгу");
                request.getSession().setAttribute("frmact","librarian\\addBook.jsp");
            }
            else{
                request.setAttribute("stat","Читатель");
                request.getSession().setAttribute("btn","Мои книги");
                request.getSession().setAttribute("frmact","reader\\MyBook.jsp");
            }

            DB db = new DB();

            ArrayList<Book> books=db.boolList();

            request.setAttribute("books",books);

            RequestDispatcher view=request.getRequestDispatcher("MainPage.jsp");
            view.forward(request, response);

        }
}
