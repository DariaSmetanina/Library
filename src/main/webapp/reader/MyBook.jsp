<?xml version="1.0" encoding="UTF-8"?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="model.*" %>
<%@ page import="java.util.List" %>
<%@ page import="static java.sql.JDBCType.NULL" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.sql.Connection" %>
<html>
<head bgcolor="#e0ffff">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Мои книги</title>
</head>
<body bgcolor="#e0ffff">
<%
   //List<Book> book=(List)request.getAttribute("books");

    int id;
    id = Integer.parseInt(request.getSession().getAttribute("idUser").toString());

    ArrayList<Book> book = new ArrayList<>();

    String url = "jdbc:mysql://127.0.0.1:3306/library19?serverTimezone=Europe/Moscow&useSSL=false";
    String user = "root";
    String password = "@WSX2wsx";

    ResultSet rs;

    Class.forName("com.mysql.cj.jdbc.Driver");
    Connection con = DriverManager.getConnection(url, user, password);
    //  getting Statement object to execute query
    Statement stmt = con.createStatement();
    System.out.print("correct ");

    String query ="SELECT * FROM book WHERE (`Taken` = '"+id+"');";

        rs = stmt.executeQuery(query);
        while(rs.next()){
            Book bo=new Book(rs.getInt(1),rs.getString(3),rs.getString(2),rs.getString(4),rs.getString(5),rs.getInt(6));
            book.add(bo);
        }
        rs.close();

        Iterator it=book.iterator();

    if(it.hasNext()){
        for (int i = 0; i < book.size(); i++) {
%>
    <p><td><a href="../BookPage?id=<%=book.get(i).getId()%>"><%=book.get(i).toString()%></a></td></tr></p>
<%      }
    }
else{
    %>Пока нет книг<%
} %>
</body>
</html>
