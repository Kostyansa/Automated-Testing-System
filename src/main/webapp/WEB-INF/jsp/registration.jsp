<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title></title>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href="../css/loginreg.css">

</head>

<div class="header">
    <h1>Зарегистрировать пользователя</h1>

  </div>

<div class="topnav">
   <a href="/main">Главная</a>
   <a href="/logout" style="float:right">Выход</a>
</div>

<body>
    <div class="center">
            <form action="/registration" method="POST">
                <div class="field">idUser*:  <input type="number" name="idUser" required></div>
                <div class="field">name*:   <input type="text" name="name" required></div>
                <div class="field">password*:  <input type="password" name="password" required></div>
                <div class="field">email*: <input type="text" name="email"></div>
            <div><input type="submit" value="Вход"></div></form>
            <label>${errorMsg}</label>
            <c:forEach var="error" items="${errors}">
			${error.defaultMessage}<br>
            </c:forEach>

    </div>
</body>
</html>