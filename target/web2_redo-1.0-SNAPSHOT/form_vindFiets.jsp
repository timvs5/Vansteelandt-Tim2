<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Mijn fietsen</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>

    <header>
        <h2>Toon de fiest met merk</h2>

        <%@  include file="navigation.jspf" %>
    </header>

    <main>
        <c:if test="${not empty errors}">
            <p>Er waren volgende problemen waardoor we de fiets niet konden vinden</p>
            <ul>
                <c:forEach items="${errors}" var="error">
                    <li>${error}</li>
                </c:forEach>
            </ul>
        </c:if>

        <form novalidate>
            <p><label for="merk">Merk:</label><input type="text" id="merk"></p>
            <p><input type="submit" value="Zoek"></p>
        </form>


        <p>Indien gezocht en geen resultaat: zinnetje met tekst "niets gevonden".</p>

        <p>Indien geldige zoekopdracht met resultaat: tabel.</p>
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>Naam fiets</th>
                <th>Merk</th>
                <th>Bouwjaar</th>
                <th>prijs (in €)</th>
                <th>Verwijder fiets</th>
            </thead>
            <tbody>
            <tr>
                <td>1</td>
                <td>naam van fiets 1</td>
                <td>merk</td>
                <td>bouwjaar</td>
                <td>prijs</td>
                <td>Verwijderen</td>
            </tr>
            </tbody>
        </table>

    </main>
</body>
</html>