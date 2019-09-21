<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <meta charset="utf-8">
    <title>Сдача</title>
    <link href="../css/loginreg.css" rel="stylesheet" type="text/css">

</head>

<div class="header">
    <h1>Добавить задачу</h1>

</div>

<div class="topnav">
    <a href="/main">Главная</a>
    <a href="/logout" style="float:right">Выход</a>
</div>

<body>
    <div class="center">
            <form action="/newExercise" method="POST">
                <div class="field"><label>Задача: </label>  <input type="number" name="idExercise" required></div>
                <div class="field"><label>Текст: </label>  <input type="text" name="path" required></div>
                <div><input type="submit" value="Создать"></div>
                </form>
            <p>${errorMsg}</p>
    </div>
</body>
</html>