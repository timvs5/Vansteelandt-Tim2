<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="javax.servlet.http.Cookie"%>
<!DOCTYPE html>
<html>
<head>
    <title>Add_Game</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <header>
        <h1>Add game</h1>
        <%@  include file="navigation.jspf" %>
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
            <input type="text" id="gameName" name="gameName">
            <br>
            <label for="gameGenre">genre</label>
            <input type="text" id="gameGenre" name="gameGenre">
            <br>
            <label for="gameScore">score</label>
            <input type="text" id="gameScore" name="gameScore">
            <br>
            <input type="hidden" name="action" value="addGame">
            <input type="submit" id="gameSubmit" value="add">
        </form>
    </main>

    <footer>
        <p>U vulde het formulier al
            <c:if test="${not empty tries}">
                ${tries}
            </c:if>
            <c:if test="${empty tries}">
                ${cookie.formulierTries.value}
            </c:if>
            keer fout in.</p>
        <form action="servlet" method="get" novalidate>
            <input type="submit" id="cookieReset" value="reset">
            <input type="hidden" name="action" value="resetCookies">
        </form>
    </footer>
</body>
</html>
