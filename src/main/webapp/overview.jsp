<%@ page import="java.util.ArrayList" %>
<%@ page import="be.ucll.vansteelandttim.domain.model.Game" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Tim
  Date: 27/02/2022
  Time: 23:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Overview</title>
</head>
<body>
    <header>
        <h1>Overview</h1>
        <%@  include file="navigation.jspf" %>
    </header>

    <table>
        <form action="servlet" method="get" novalidate>
            <input type="text" name="getGameScore" required>
            <input type="submit" value="search">
            <input type="hidden" name="action" value="getGameScore">
        </form>
        <thead>
            <tr>
                <th>Naam</th>
                <th>Genre</th>
                <th>Score</th>
                <th>Change</th>
                <th>Remove</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="game" items="${games}">
            <tr>
                <td>${game.name}</td>
                <td>${game.genre}</td>
                <td>${game.score}</td>
                <td>
                    <form action="servlet" method="post" novalidate>
                        <input type="submit" value="change" >
                        <input type="hidden" name="gameID" value=${game.id}>
                        <input type="hidden" name="action" value="changeGame">
                    </form>
                </td>
                <td>
                    <form action="servlet" method="post" novalidate>
                        <input type="submit" value="Remove" >
                        <input type="hidden" name="gameID" value=${game.id}>
                        <input type="hidden" name="action" value="removeGame">
                    </form>
                </td>
            </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
