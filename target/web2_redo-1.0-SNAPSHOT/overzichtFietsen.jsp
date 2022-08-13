<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bekijk alle fietsen</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<div id="container">

    <header>
        <h1>Bekijk alle fietsen</h1>

        <%@  include file="navigation.jspf" %>
    </header>

    <main>
        <form action="servlet" method="get" novalidate>
            <input type="text" name="fietsMerk" value="zoek op merk">
            <input type="submit" id="search" value="zoek">
            <input type="hidden" name="action" value="searchFietsen">
        </form>

        <c:if test="${not empty verwijderdeFiets}">
            <p>U heeft eerder de fiets met naam ${verwijderdeFiets.naam} verwijderd.</p>
            <form action="servlet" method="post" novalidate>
                <input type="submit" value="verwijderen ongedaan maken">
                <input type="hidden" name="action" value="undeleteFiets">
            </form>
        </c:if>

        <c:if test="${not empty cookie.laatstAangepast.value or not empty lAangepast}">
            <p>U heeft voor het laatst de fiets met id
            <c:if test="${empty lAangepast}">
                ${cookie.laatstAangepast.value}
            </c:if>
            <c:if test="${not empty lAangepast}">
                ${lAangepast}
            </c:if>
            aangepast</p>
            <c:if test="${laatstAangepastBestaat == false}">
                <p>De fiets met dit id bestaat niet meer</p>
            </c:if>
        </c:if>

        <c:if test="${not empty gezochtMerk}">
            <c:if test="${not empty errors}">
                <p>Wij kunnen geen fietsen tonen om volgende reden</p>
                <ul>
                    <c:forEach items="${errors}" var="error">
                        <li>${error}</li>
                    </c:forEach>
                </ul>
                <form action="servlet" method="get" novalidate>
                    <input type="submit" value="toon alle fietsen">
                    <input type="hidden" name="action" value="getOverzichtFietsen">
                </form>
            </c:if>
            <c:if test="${empty errors}">
                <p>Enkel fietsen van het merk '${gezochtMerk}' worden nu getoont</p>
                <form action="servlet" method="get" novalidate>
                    <input type="submit" value="toon alle">
                    <input type="hidden" name="action" value="getOverzichtFietsen">
                </form>
            </c:if>
        </c:if>

        <c:if test="${empty gezochtMerk}">
            <c:if test="${empty fietsen}">
                <p>Er zijn geen fietsen.</p>
            </c:if>
        </c:if>

        <c:if test="${not empty fietsen}">
            <table>
                <thead>
                <tr>
                    <th>Id</th>
                    <th>Naam fiets</th>
                    <th>Merk</th>
                    <th>Bouwjaar</th>
                    <th>Aantal km</th>
                    <th>Update kilometers</th>
                    <th>Verwijder fiets</th>
                </thead>
                <tbody>
                <c:forEach var="fiets" items="${fietsen}">
                    <tr>
                        <td>${fiets.id}</td>
                        <td>${fiets.naam}</td>
                        <td>${fiets.merk}</td>
                        <td>${fiets.bouwjaar}</td>
                        <td>${fiets.kilometers}</td>
                        <td>
                            <form action="servlet" method="get" novalidate>
                                <input type="submit" class="removeButton" value="Update" >
                                <input type="hidden" name="fietsID" value=${fiets.id}>
                                <input type="hidden" name="action" value="fietsKilometerUpdater">
                            </form>
                        </td>
                        <td>
                            <form action="servlet" method="get" novalidate>
                                <input type="submit" class="removeButton" value="Verwijder" >
                                <input type="hidden" name="fietsID" value=${fiets.id}>
                                <input type="hidden" name="action" value="removeFiets">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:if>

    </main>
</div>
</body>
</html>