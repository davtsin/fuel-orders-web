<%-- 
    Document   : createCompany
    Created on : Jan 16, 2018, 12:07:49 PM
    Author     : avtsin denis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create company</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js" type="text/javascript" defer></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-form-validator/2.3.26/jquery.form-validator.min.js" type="text/javascript" defer></script>
        <script src="js/validate.js" type="text/javascript" defer></script>
        <link rel="stylesheet" href="css/styles.css">
    </head>
    <body>
        <h1>Create company</h1>
        <p><a href="${pageContext.servletContext.contextPath}">&larr; Go back</a></p>
        <form id="companyForm" method="POST" action="">
            <table>
                <tr>
                    <td>Company name :</td>
                </tr>
                <tr>
                    <td><input type="text" name="companyName" 
                               data-validation="required" size="31" 
                               maxlength="30" value="АО &quot;РН-Москва&quot;" /></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Create company"/></td>
                </tr>
            </table>
        </form>
        <br/>
        <hr/>
        <div id="newCompany">
            <c:if test = "${company != null}">
                <p>Company <i>${company.name}</i> with <i>id=${company.id}</i> 
                    was successfully added to the database</p>
            </c:if>
        </div>
        <hr/>
        <table id="companiesTable" border="1">
            <caption><h3>All companies</h3></caption>
            <tr><th>Company id</th><th>Company name</th></tr>
                    <c:forEach items="${companies}" var="company">
                <tr><td>${company.id}</td><td>${company.name}</td></tr>
            </c:forEach>
        </table>
    </body>
</html>