<%-- 
    Document   : createFillingStation
    Created on : Jan 16, 2018, 12:09:22 PM
    Author     : denis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create filling station</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js" type="text/javascript"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-form-validator/2.3.26/jquery.form-validator.min.js"></script>
        <script src="js/validate.js" type="text/javascript" defer></script>
        <link rel="stylesheet" href="css/styles.css">
    </head>
    <body>
        <h1>Create filling station</h1>
        <p><a href="${pageContext.servletContext.contextPath}" >&larr; Go back</a></p>
        <form id="fsForm" method="POST" action="">
            <table border="0">
                <tr>
                    <td>Company name :</td>
                    <td> <select name="companyId" data-validation="required">
                            <option value="" disabled selected>Select company</option>
                            <c:forEach items="${companies}" var="company">
                                <option value="${company.id}">${company.name}</option>
                            </c:forEach>
                        </select> </td>
                </tr>
                <tr>
                    <td>Filling station code :</td>
                    <td><input type="text" name="fillingStationCode" 
                               data-validation="required" maxlength="20" value="MR041" /></td>
                </tr>
                <tr>
                    <td>Filling station address :</td>
                    <td><input type="text" name="fillingStationAddress" 
                               data-validation="required" 
                               size="51" maxlength="50" value="г.Москва, пр-т Мира, д.142" /></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Create filling station"/></td>
                    <td></td>
                </tr>
            </table>
        </form>
        <br/>
        <hr/>
        <div id="newFillingStation">
            <c:if test = "${fillingStation != null}">
                <p>Filling station <i>${fillingStation.code} : ${fillingStation.address}</i> 
                    with <i>id=${fillingStation.id}</i> for company <i>${company.name}</i> 
                    was successfully added to the database</p>
                </c:if>
        </div>
        <hr/>

        <table id="fsTable" border="1">
            <caption><h3>All filling stations</h3></caption>
            <tr><th>id</th><th>Code</th><th>Address</th><th>Company</th></tr>
                    <c:forEach items="${companiesAndFillingStations}" var="companiesAndFs">
                <tr>
                    <td>${companiesAndFs.id}</td><td>${companiesAndFs.code}</td>
                    <td>${companiesAndFs.address}</td><td>${companiesAndFs.companyName}</td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
