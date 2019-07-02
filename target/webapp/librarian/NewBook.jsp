<?xml version="1.0" encoding="UTF-8"?>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
      <head>
       
              <meta http-equiv="Content-Type" content="text/html; charset=UTF-32">
              <title>Добавить книгу</title>
       
          </head>
      <title>Добавить книгу</title>
   
   </head>

<body bgcolor="#add8e6">
<br>
<form action="../MainPage.jsp" method="post">
      <center>
              <table border="0" width="30%" cellpadding="3" bgcolor="#e0ffff">
                      <thead>
                      <tr>
                              <th colspan="2">b добавлена</th>
                          </tr>
                      </thead>
                      <tbody>
                      <tr>
                              <td>Название</td>
                              <td><%=request.getParameter("title")%></td>
                          </tr>
                      <tr>
                              <td>Автор</td>
                              <td><%=request.getParameter("autor")%></td>
                          </tr>
                      <tr>
                              <td>Жанр</td>
                              <td><%=request.getParameter("genre[]")%></td>
                          </tr>
                      <tr>
                              <td><input type="submit" value="На главную страницу" /></td>
                          </tr>
                  </table>
          </center>
   </form>
<br>
</body>
</html>
