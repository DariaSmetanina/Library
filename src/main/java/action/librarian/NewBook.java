package action.librarian;

import action.Dispatcher;
import db.DB;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;


public class NewBook extends Dispatcher {
    public String getServletInfo(){
        return "Registration servlet";
    }

    public void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
            DB db = new DB();
            response.setContentType("text/html;charset=UTF-8;");
            response.setCharacterEncoding("UTF-8");
            request.setCharacterEncoding("UTF-8");

        String content = request.getParameter("autor")+" "+request.getParameter("title");
        response.setContentType("text/plain");

        OutputStream outStream = response.getOutputStream();
        outStream.write(content.getBytes("UTF-8"));
        outStream.flush();
        outStream.close();

        db.addBook(request.getParameter("autor"),request.getParameter("title"),request.getParameter("genre"));
            db.exit();


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        PrintWriter out = resp.getWriter();
        out.print("<h1>Hello Servlet</h1>");

    }
}