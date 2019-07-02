package action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogPage extends Dispatcher {
        public String getServletInfo(){
            return "Registration servlet";
        }

        public void service(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
                this.forward("/index.jsp", request, response);
        }
    }

