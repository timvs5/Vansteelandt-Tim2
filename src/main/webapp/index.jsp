<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - index</title>
</head>
<body>
    <header>
        <h1>Home</h1>
        <%@  include file="navigation.jspf" %>
    </header>
    <main>
        <p>The game with the highest score is: ${highestScoreGame}</p>
        <br/>
        <!-- <a href="servlet">Servlet</a>
        <p> testje: %=request.getParameter("test")%></p>
        <h3>%= request.getAttribute("Gwert")%></h3>-->
    </main>
</body>
</html>