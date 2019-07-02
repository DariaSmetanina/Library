<%@ page import="model.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.sql.Connection" %>
<?xml version="1.0" encoding="UTF-8"?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<head>
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
    <script src="js/updBook.js"></script>
    <%  String url = "jdbc:mysql://127.0.0.1:3306/library19?serverTimezone=Europe/Moscow&useSSL=false";
        String user = "root";
        String password = "@WSX2wsx";

        ResultSet rs;

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, user, password);
        //  getting Statement object to execute query
        Statement stmt = con.createStatement();
        System.out.print("correct ");

        int id=Integer.parseInt(request.getAttribute("idBook").toString());

        String query ="SELECT * FROM book WHERE (`idBook` = '"+id+"');";


        rs = stmt.executeQuery(query);

        if(rs.next()){

            request.setAttribute("title",rs.getString(3));
            request.setAttribute("autor",rs.getString(2));
        }
        rs.close();%>

              <meta http-equiv="Content-Type" content="text/html; charset=UTF-32">
      <title><%=request.getAttribute("title").toString()%></title>
      </head>
<body bgcolor="#add8e6">
<br>

<form id="BookPageForm" method="post">
      <center>
              <table border="0" width="30%" cellpadding="3" bgcolor="#e0ffff">
                      
                      <tbody>
                                  <tr>
                                          <td><%=request.getAttribute("title").toString()%></td>
                                  </tr>
                                  <tr>
                                          <td><%=request.getAttribute("autor").toString()%></td>
                                  </tr>
                                  <tr>
                                    <td><button<%=request.getAttribute("actbtn")%> id="bttn" onclick="sendForm('result_form', '<%=request.getAttribute("todo")%>');this.disabled=true"><%=request.getAttribute("btn")%></button></td>
                                  </tr>
                      </tbody>
              </table>
          </center>
    </form>
    <center><span id="result_form"></span></center>
</body>
</html>
