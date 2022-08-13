<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Verwijder bevestiging</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <header>
        <h1>Verwijder fiets uit mijn collectie</h1>

        <%@  include file="navigation.jspf" %>
    </header>


    <main>

        <p>Wil je de fiets met naam ${fiets.naam} en merk ${fiets.merk} uit de lijst verwijderen?</p>

        <form action="servlet" method="post" novalidate>
            <input type="submit" id="confirm" value="ja">
            <input type="hidden" name="fietsID" value=${fiets.id}>
            <input type="hidden" name="action" value="confirmRemoveFiets">
        </form>
        <form action="servlet" method="get" novalidate>
            <input type="submit" id="cancel" value="nee">
            <input type="hidden" name="action" value="cancelRemoveFiets">
        </form>

    </main>

</body>
</html>
