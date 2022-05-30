<%--
  Created by IntelliJ IDEA.
  User: Tim
  Date: 20/03/2022
  Time: 17:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add_Game</title>
</head>
<body>
    <header>
        <h1>Add game</h1>
        <%@  include file="navigation.jspf" %>
    </header>

    <form action="servlet" method="post" novalidate>
        <label for="gameName">Naam</label>
        <input type="text" id="gameName" name="gameName">

        <label for="gameGenre">genre</label>
        <input type="text" id="gameGenre" name="gameGenre">

        <label for="gameScore">score</label>
        <input type="number" id="gameScore" name="gameScore">

        <input type="hidden" name="action" value="addGame">
        <input type="submit" value="add">
    </form>

    <footer>
        <p>U vulde het formulier al ${cookie.formulierTries.value} keer fout in.</p>
        <form action="servlet" method="post" novalidate>
            <input type="submit" value="reset">
            <input type="hidden" name="action" value="resetCookies">
        </form>
    </footer>
</body>
</html>
