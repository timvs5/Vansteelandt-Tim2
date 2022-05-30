<%--
  Created by IntelliJ IDEA.
  User: Tim
  Date: 30/05/2022
  Time: 16:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
</head>
<body>
<p>You are now making changes to "${Game.name}" in the database. Press 'confirm' to make changes permanent</p>
<form action="servlet" method="post" novalidate>
    <input type="submit"value="confirm changes">

    <label for="gameName">Naam</label>
    <input type="text" name="gameName" id="gameName" value=${Game.name}>

    <label for="gameGenre">Genre</label>
    <input type="text" name="gameGenre" id="gameGenre" value=${Game.genre}>

    <label for="gameScore">Score</label>
    <input type="text" name="gameScore" id="gameScore" value=${Game.score}>

    <input type="hidden" name="gameID" value=${Game.id}>
    <input type="hidden" name="action" value="confirmChangeGame">
</form>
<form action="servlet" method="post" novalidate>
    <input type="submit" value="cancel">
    <input type="hidden" name="action" value="cancelRemoveGame">
</form>
</body>
</html>
