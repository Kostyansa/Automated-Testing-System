<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <meta charset="utf-8">
    <title>Вход</title>
    <link href="../css/loginreg.css" rel="stylesheet" type="text/css">

</head>

<div class="header">
    <h1>Выдать задачу</h1>

</div>

<div class="topnav">
    <a href="/main">Главная</a>
    <a href="/logout" style="float:right">Выход</a>
</div>
<body>
    <div class="center">

            <form action="/setExercise" method="POST">

                <div class="field"><label>idUser:</label>  <input type="number" name="idUser" required></div>
                <div class="field"><label>idExercise:</label>  <input type="number" name="idExercise" required></div>
                <div><input type="submit" value="Ок"></div>
                </form>
            <p>${errorMsg}</p>
    </div>
</body>
</html>