<%@ page import="com.questwebgame.GameServlet" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Артем
  Date: 15.06.2023
  Time: 18:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Game Homeless</title>
    <%@ page contentType="text/html;charset=UTF-8" %>
    <% if (GameServlet.getQuestionNumber() == 0) { %>
    <link rel="stylesheet" type="text/css" href="/css/firstQuestion.css">
    <% }
        if (GameServlet.getQuestionNumber() == 1) { %>
    <link rel="stylesheet" type="text/css" href="/css/secondQuestion.css">
    <% }
        if (GameServlet.getQuestionNumber() == 2) { %>
    <link rel="stylesheet" type="text/css" href="/css/thirdQuestion.css">
    <% }
        if (GameServlet.getQuestionNumber() == 3) { %>
    <link rel="stylesheet" type="text/css" href="/css/fourthQuestion.css">
    <% }
        if (GameServlet.getQuestionNumber() == 4) { %>
    <link rel="stylesheet" type="text/css" href="/css/fifthQuestion.css">
    <% } %>
</head>


<body>
<%
    String ipAddress = request.getHeader("X-FORWARDED-FOR");
    if (ipAddress == null) {
        ipAddress = request.getRemoteAddr();
    }
%>
<div>
    <h3>${question}</h3>

    <form id="answersForm" method="post" action="${pageContext.request.contextPath}/ProjectQuestWebGame/game">
        <c:forEach var="answer" items="${answers}">
            <input type="radio" name="answer" value="${answer}">
            <label>${answer}</label>
            <br>
        </c:forEach>
        <br>
        <input type="submit" value="Дальше" style="display: block; margin: 0 auto;">
    </form>
</div>
<div id="stats">
    <span>Статистика:</span>
    <span>IP-address: <%=ipAddress%></span>
    <span>Количество сыгранных игр: <%=GameServlet.getPlayedGames() %></span>
</div>
</body>

</html>
