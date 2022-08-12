<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - index</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <header>
        <h1>Home</h1>
        <%@  include file="navigation.jspf" %>
    </header>
    <main>
        <c:if test="${not empty changes}">
            <p>U heeft volgende aanpassingen gemaakt tijdens uw sessie.</p>
            <ul>
                <c:forEach items="${changes}" var="change">
                    <li>
                        <p>Van  ::  naam= ${change.oldGame.name},  genre= ${change.oldGame.genre},  score= ${change.oldGame.score}</p>
                        <p>Naar ::  naam= ${change.newGame.name},  genre= ${change.newGame.genre},  score= ${change.newGame.score}</p>
                    </li>
                </c:forEach>
            </ul>
        </c:if>
        <!-- <a href="servlet">Controller</a>
        <p> testje: %=request.getParameter("test")%></p>
        <h3>%= request.getAttribute("Gwert")%></h3>-->
    </main>
</body>
</html>