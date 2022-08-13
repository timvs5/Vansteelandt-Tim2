<%--
  Created by IntelliJ IDEA.
  User: Tim
  Date: 28/05/2022
  Time: 19:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>result</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <header>
        <%@  include file="navigation.jspf" %>
    </header>

    <main>
        <h2>Score: </h2>
        <p>${result}</p>

        <a class="back" href="servlet?action=getOverview">terug naar Overview</a>
    </main>

</body>
</html>
