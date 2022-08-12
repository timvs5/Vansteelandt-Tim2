<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Nieuw aantal kilometers</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>

<div id="container">
    <main>
        <h2>Pas de afgelegde kilometers aan</h2>

        <%@  include file="navigation.jspf" %>

        <c:if test="${not empty errors}">
            <p>The following errors occured while trying to commit changes.</p>
            <ul>
                <c:forEach items="${errors}" var="error">
                    <li>${error}</li>
                </c:forEach>
            </ul>
        </c:if>

        <form class="formulier" action="servlet" method="post" novalidate>
            <label for="aantal">Nieuw aantal: </label>
            <input id="aantal" type="text" name="km" value=${fiets.kilometers}>
            <input type="hidden" name="action" value="updateKilometers">
            <input type="hidden" name="fietsID" value=${fiets.id}>
            <input type="submit" value="Pas  aan">
        </form>
    </main>

</div>

</body>
</html>
