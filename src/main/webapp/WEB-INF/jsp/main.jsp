<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Новости</title>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href="../css/Common.css">
</head>

<div class="header">
    <h1>Главная</h1>
    
  </div>
  
  <div class="topnav">
    <a href="/main">Главная</a>
    <c:if test="${user.userMode == 'user'}" >
      <a href="/submit">Сдать</a>
    </c:if>

    <c:if test="${user.userMode == 'admin'}" >
      <a href="/registration">Зарег. Поль.</a>
      <a href="/setExercise"> Выдать задание</a>
      <a href="/newExercise"> Добавить задание</a>
      <a href="/checkPrograms"> Проверить задание</a>

    </c:if>
    
    <a href="/logout" style="float:right">Выход</a>
  </div>
  
</html>