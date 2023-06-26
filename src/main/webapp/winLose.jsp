<%@ page import="com.questwebgame.GameServlet" %><%--
  Created by IntelliJ IDEA.
  User: Артем
  Date: 26.06.2023
  Time: 13:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%if(GameServlet.getWinValue()) { %>
    <title>WINNER!</title>
    <link rel="stylesheet" type="text/css" href="/css/winPage.css">
    <% } %>
    <%if(!GameServlet.getWinValue()) { %>
    <title>LOSER!</title>
    <link rel="stylesheet" type="text/css" href="/css/losePage.css">
    <% } %>
</head>
<body>
<%if(GameServlet.getWinValue()) { %>
<h1><br><br><br>ПОБЕДА!</h1>
<h1><br><br>Ты встал на верный путь, остается лишь продолжать в том же духе.<br></h1>
<form action="/ProjectQuestWebGame">
    <button>Заново</button>
</form>
<% } %>
<%if(!GameServlet.getWinValue()) { %>
<h1><br><br><br>ТЫ ПРОИГРАЛ!</h1>
<form action="/ProjectQuestWebGame">
    <button>Заново</button>
</form>
<% } %>
</body>
</html>
