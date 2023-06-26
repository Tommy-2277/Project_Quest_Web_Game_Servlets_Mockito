<%--
  Created by IntelliJ IDEA.
  User: Tommy
  Date: 13.06.2023
  Time: 14:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello Page</title>
    <link rel="stylesheet" type="text/css" href="/css/helloPage.css">
</head>

<body>
<h1><br><br>Quest Web Game</h1>
<h1><br><br><br>Welcome to the game!</h1>
<h1>Wish you luck!</h1>
<h1>Press "Start" button to start a game.</h1>
<form action="${pageContext.request.contextPath}/ProjectQuestWebGame/game">
    <button>Начать игру</button>
</form>
</body>
</html>
