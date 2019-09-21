<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <meta charset="utf-8">
    <title>Вход</title>
    <link href="../css/loginreg.css" rel="stylesheet" type="text/css">

</head>
<body>
    <div class="center">

            <form action="/login" method="POST">

                <div class="field"><label>Логин:</label>  <input type="number" name="idUser" required></div>
                <div class="field"><label>Пароль:</label>  <input type="password" name="password" required></div>
                <div><input type="submit" value="Вход"></div>
                </form>
            <p>${errorMsg}</p>
    </div>
</body>
</html>
