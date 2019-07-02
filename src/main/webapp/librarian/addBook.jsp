<?xml version="1.0" encoding="UTF-8"?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>


    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-32">
        <title>Добавить книгу</title>
        <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
        <script src="../js/addBook.js"></script>

    </head>


<body bgcolor="#add8e6">

<br>
<form id="NewForm"  action="" method="post">
    <center>
        <table border="0" width="30%" cellpadding="3" bgcolor="#e0ffff">
            <thead>
            <tr>
                <th colspan="2">Добавление книги</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>Название</td>
                <td><input type="text" id="title" name="title" value="" /></td>
            </tr>
            <tr>
                <td>Автор</td>
                <td><input type="text" id="autor" name="autor" value="" /></td>
            </tr>
            <tr>
                <td>Жанр</td>
                <td><select id="genre" name="genre">
                    <option disabled>Выберите жанр</option>
                    <option selected value="Бизнес">Бизнес</option>
                    <option value="Детектив">Детектив</option>
                    <option value="Драма">Драма</option>
                    <option value="Комедия">Комедия</option>
                    <option value="Пародия">Пародия</option>
                    <option value="Саморазвитие">Саморазвитие</option>
                    <option value="Юмор">Юмор</option>
                </select></td>
            </tr>
            <tr>
                <td><input type="button" id="bttn" value="Добавить" onclick="sendNewBookForm('result_form','NewForm','NewBook')"/></td>
                <td><input type="reset" value="Очистить" /></td>
            </tr>
        </table>
    </center>
</form>
<center><span color="red" id="result_form"></span></center>
<br>
</body>
</html>