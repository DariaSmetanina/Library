<?xml version="1.0" encoding="UTF-8"?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>


<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-32">
    <title>Вход в систему</title>
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
    <script src="js/CheckUser.js"></script>
</head>

<body bgcolor="#add8e6">

<br>
<form id="LogForm" action="" method="post">
    <center>
        <table border="0" width="30%" cellpadding="3" bgcolor="#e0ffff">
            <thead>
            <tr>
                <th colspan="2">Библиотека.Онлайн</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>Логин</td>
                <td><input type="text" id="login" name="login" value="" /></td>
            </tr>
            <tr>
                <td>Пароль</td>
                <td><input type="password" id="password" name="password" value="" /></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="button" id="btn" value="Войти" onclick="sendAjaxForm('result_form', 'LogForm', 'CheckUser')" /></td>
            </tr>
            </tbody>
        </table>
    </center>
</form>
<center><span color="red" id="result_form"></span></center>
<br>
</body>
</html>