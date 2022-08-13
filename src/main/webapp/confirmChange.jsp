<%--
  Created by IntelliJ IDEA.
  User: Tim
  Date: 30/05/2022
  Time: 16:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Confirm_change</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <header>
        <p>You are now making changes to "${Game.name}" in the database. Press 'confirm' to make changes permanent</p>
    </header>

    <main>
        <c:if test="${not empty errors}">
            <p>The following errors occured while trying to commit changes.</p>
            <ul>
                <c:forEach items="${errors}" var="error">
                    <li>${error}</li>
                </c:forEach>
            </ul>
        </c:if>

        <form class="formulier" action="servlet" method="post" novalidate>
            <label for="gameName">Naam</label>
            <input type="text" name="gameName" id="gameName" value=${Game.name}>
            <br>
            <label for="gameGenre">Genre</label>
            <input type="text" name="gameGenre" id="gameGenre" value=${Game.genre}>
            <br>
            <label for="gameScore">Score</label>
            <input type="text" name="gameScore" id="gameScore" value=${Game.score}>
            <br>
            <input type="hidden" name="gameID" value=${Game.id}>
            <input type="hidden" name="action" value="confirmChangeGame">
            <input type="submit" id="confirmChange" value="confirm changes">
        </form>
        <form action="servlet" method="get" novalidate>
            <input type="submit" value="cancel">
            <input type="hidden" name="action" value="cancelChangeGame">
        </form>
    </main>
</body>
</html>
