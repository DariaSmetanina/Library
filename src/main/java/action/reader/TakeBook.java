package action.reader;

import action.Dispatcher;
import db.DB;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;


public class TakeBook extends Dispatcher {
    public String getServletInfo(){
        return "Registration servlet";
    }

    public void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        DB db = new DB();
        response.setContentType("text/html;charset=UTF-8;");
        response.setCharacterEncoding("UTF-8");

        int id = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("title",db.getBookInfo(id).getKey());
        request.setAttribute("autor",db.getBookInfo(id).getValue());
        String pl="Зал №"+db.getBookPlace(id).getKey().getKey()+" шкаф № "+db.getBookPlace(id).getKey().getValue()+" полка № "+db.getBookPlace(id).getValue().getKey()+"  место № "+db.getBookPlace(id).getValue().getValue();
        request.setAttribute("place",pl);


        int usid=Integer.parseInt(request.getSession().getAttribute("idUser").toString());

        String content = "взята";
        response.setContentType("text/plain");

        OutputStream outStream = response.getOutputStream();
        outStream.write(content.getBytes("UTF-8"));
        outStream.flush();
        outStream.close();


        db.takeBook(id,usid);
        db.exit();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

    }
}