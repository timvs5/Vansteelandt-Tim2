<%--
  Created by IntelliJ IDEA.
  User: Tim
  Date: 3/08/2022
  Time: 3:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Confirm_cookie_reset</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <header>
    <%@  include file="navigation.jspf" %>
    </header>

    <main>
        <p>The cookie has been reset.</p>
        <form action="servlet" method="get" novalidate>
            <input type="submit" id="returnToFormulier" value="return">
            <input type="hidden" name="action" value="getFormulier">
        </form>
    </main>
</body>
</html>
