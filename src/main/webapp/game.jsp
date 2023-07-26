<%@ page import="com.questwebgame.GameServlet" %>
<%@ page import="extra.classes.Styles" %>
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
    <link rel="stylesheet" type="text/css" href="" id="headStyle">
    <%String style = Styles.getStyle(GameServlet.getQuestionNumber());%>
    <script>document.getElementById("headStyle").setAttribute("href", "<%= style %>")</script>
</head>


<body>
<%
    String ipAddress = request.getHeader("X-FORWARDED-FOR");
    if (ipAddress == null) {
        ipAddress = request.getRemoteAddr();
    }
%>
<div id="container">
    <div><h3>${question}</h3></div>
<%--    <form id="answersForm" method="post" action="${pageContext.request.contextPath}/ProjectQuestWebGame/game">--%>
    <div>
    <form method="post" action="${pageContext.request.contextPath}/ProjectQuestWebGame/game">
        <c:forEach var="answer" items="${answers}">
            <input type="radio" name="answer" value="${answer}">
            <label>${answer}</label>
            <br>
        </c:forEach>
        <br>
        <input type="submit" value="Дальше" style="display: block; margin: 0 auto;">
    </form>
    </div>
</div>

<div id="stats">
    <span>Stats:</span>
    <span>IP-address: <%=ipAddress%></span>
    <span>Played games: <%=GameServlet.getPlayedGames() %></span>
</div>
</body>

</html>
