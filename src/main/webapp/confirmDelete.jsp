<%--
  Created by IntelliJ IDEA.
  User: Tim
  Date: 30/05/2022
  Time: 11:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>confirm</title>
</head>
<body>
    <p>are you sure you want to remove "${Game.name}" from the database?</p>
    <form action="servlet" method="post" novalidate>
        <input type="submit"value="remove game">
        <input type="hidden" name="gameID" value=${Game.id}>
        <input type="hidden" name="action" value="confirmRemoveGame">
    </form>
    <form action="servlet" method="post" novalidate>
        <input type="submit" value="cancel">
        <input type="hidden" name="action" value="cancelRemoveGame">
    </form>
</body>
</html>
