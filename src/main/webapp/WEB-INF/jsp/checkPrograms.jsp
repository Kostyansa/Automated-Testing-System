<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>

<head>
  <title></title>
  <meta charset="utf-8">
  <link rel="stylesheet" type="text/css" href="../css/Common.css">

</head>

<div class="header">
  <h1>Проверка программ</h1>
</div>

<div class="topnav">
  <a href="/main">Главная</a>

  <c:if test="${user.role == 'admin'}">
    <a href="/registration">Зарег. Поль.</a>
  </c:if>

  <a href="/logout" style="float:right">Выход</a>
</div>

<div class="row">
  <div class="column middle">
    <form action="/checkPrograms" method="POST">
      <div class="field"><label>Номер задачи:</label>  <input type="number" name="idExercise" value="${idExercise}" required></div>
      <div class="card">
        <table>
          <c:forEach var="program" items="${programs}">
            <tr>
              <td>
                <label>${program.idUser} </label>
                <label>${program.username}</label>
                <label>${program.result}</label>
              </td>
            </tr>
          </c:forEach>
        </table>
        <input type="submit" value="Проверить">
    </form>
    <label style="color: red">${errorMsg}</label>
  </div>
</div>
</html>