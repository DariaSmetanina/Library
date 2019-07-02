<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="model.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.sql.Connection" %>
<?xml version="1.0" encoding="UTF-8"?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>

<head bgcolor="#e0ffff">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Главная страница</title>
</head>
<body bgcolor="#add8e6">
<form action="<%=request.getSession().getAttribute("frmact")%>" method="post">
    <p>
        <table border="0" width="100%" bgcolor="#e0ffff">
            <tbody>
                <tr>
                    <b>
                        <td><%=request.getSession().getAttribute("nameUser")%></td>
                    </b>
                         <right>
                            <td>
                             <input type="submit" value="<%=request.getSession().getAttribute("btn")%>"/>
                            </td>
                         </right>
                </tr>
                <tr>
                    <h2>
                         <td>  <%=request.getSession().getAttribute("textstatus")%></td>
                    </h2>
                </tr>
            </tbody>
        </table>
    </p>

    <%//List<Book> book=(List)request.getAttribute("books");

        String url = "jdbc:mysql://127.0.0.1:3306/library19?serverTimezone=Europe/Moscow&useSSL=false";
        String user = "root";
        String password = "@WSX2wsx";

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, user, password);
        //  getting Statement object to execute query
        Statement stmt = con.createStatement();
        System.out.print("correct ");

        String query ="SELECT * FROM book order by genre;";
        ArrayList<Book> book=new ArrayList<>();

        ResultSet rs = stmt.executeQuery(query);
        while(rs.next()){
               Book bo=new Book(rs.getInt(1),rs.getString(3),rs.getString(2),rs.getString(4),rs.getString(5),rs.getInt(6));
               book.add(bo);
        }
        rs.close();

        String genre=book.get(0).getGenre();%>

    <p> <table border="0" width="100%" bgcolor="#e0ffff">
           <colgroup span="4" width="150">
                    <thead>
                        <tr>
                            <th colspan="0"><%=genre%></th>
                        </tr>
                    </thead>
                <tbody>
                <tr>
    
    <%
        int i=0;
        int nomtd=-1;
        while ( i<book.size()){
           if(book.get(i).getGenre().equals(genre)){
               if(nomtd<3) {
                        %>
                            <td><a href="BookPage?id=<%=book.get(i).getId()%>"><%=book.get(i).toString()%></a></td>
                        <%
                            nomtd++;
                        }
                        else{
                            %>
                            </tr>
                            <tr>
                                <td><a href="BookPage?id=<%=book.get(i).getId()%>"><%=book.get(i).toString()%></a></td>
                            <%
                            nomtd=0;
                        }
                    }
                    else {
                        genre=book.get(i).getGenre();
                        nomtd=0;
                        %>
                            </tr>
                                </table> </p>
                            <p>
                                <table border="0" width="100%" bgcolor="#e0ffff">
                                    <colgroup span="4" width="150">
                                        <thead>
                            <tr>
                                <th colspan="0"><%=genre%></th>
                            </tr>
                                        </thead>
                                        <tbody>
                            <tr>
                                <td><a href="BookPage?id=<%=book.get(i).getId()%>"><%=book.get(i).toString()%></a></td>
                            <%
                        }
                    i++; }%>
                             </tr>
                                        </tbody>
                                  </table>
                             </p>
        </form>
</body>
</html>
