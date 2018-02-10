<%-- 
    Document   : home
    Created on : Jan 15, 2018, 6:07:55 PM
    Author     : denis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Fuel orders</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js" type="text/javascript" defer></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-form-validator/2.3.26/jquery.form-validator.min.js" type="text/javascript" defer></script>
        <script src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js" type="text/javascript" defer></script>
        <script src="js/home.js" type="text/javascript" defer></script>
        <link rel="stylesheet" href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.min.css">
        <link rel="stylesheet" href="css/styles.css">
    </head>
    <body>
        <a href="${pageContext.servletContext.contextPath}">Home page</a>
        <h1>Fuel orders</h1>

        <form id="orderForm" action="createOrderAjax" method="POST">
            <table border="0">
                <tr>
                    <td>Company name :</td>
                    <td> <select id="company" name="companyId">
                            <option value="" disabled selected>Select company</option>
                            <c:forEach items="${companies}" var="company">
                                <option value="${company.id}">${company.name}</option>
                            </c:forEach>
                        </select> 
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td class="smallFont"><a href="createCompany">Add company...</a></td>
                </tr>
                <tr>
                    <td>Filling station:</td>
                    <td><select id="fillingStation" name="fillingStationId">
                            <option value="" disabled selected>Select filling station</option>
                        </select> </td>
                </tr>
                <tr>
                    <td></td>
                    <td class="smallFont"><a href="createFillingStation">Add filling station...</a></td>
                </tr>
                <tr>
                    <td>Order date :</td>
                    <td><input type="text" name="orderDate" 
                               data-validation="date" data-validation-format="dd.mm.yyyy" 
                               maxlength="10" value="01.02.2018" /></td>
                </tr>
                <tr>
                    <td></td>
                    <td class="smallFont">mm.dd.yyyy</td>
                </tr>
                <tr>
                    <td>Fuel type :</td>
                    <td> <select id="fuelType" name="fuelType">
                            <option value="" disabled selected>Select fuel type</option>
                            <c:forEach items="${fuelType}" var="type">
                                <option>${type.description}</option>
                            </c:forEach>
                        </select> 
                </tr>
                <tr>
                    <td>Cost per liter :</td>
                    <td><input id="numericField" type="text" name="costPerLiter" 
                               data-validation="number" data-validation-allowing="float" 
                               maxlength="7" value="39.90" /></td>
                </tr>
                <tr>
                    <td>Discount :</td>
                    <td><input type="text" name="discount" 
                               data-validation="number" data-validation-allowing="float" 
                               maxlength="7" value="0.04" /></td>
                </tr>
                <tr>
                    <td>Amount liters :</td>
                    <td><input type="text" name="amountLiters" 
                               data-validation="number" data-validation-allowing="float" 
                               maxlength="7" value="37.595" /></td>
                </tr>
                <tr>
                    <td>Total cost :</td>
                    <td><input type="text" name="totalCost" 
                               data-validation="number" data-validation-allowing="float" 
                               maxlength="10" value="1500.00" /></td>
                </tr>
            </table>
            <input id="btnSubmit" type="submit" value="Create order"/>
        </form>

        <hr/>
        <div id="newOrder"></div>
        <hr/>
        <table id="ordersTable" class="display" cellspacing="0" width="100%">
            <caption><h3>All orders</h3></caption>
            <thead>
                <tr>
                    <th>order id</th>
                    <th>company</th>
                    <th>station code</th>
                    <th>station address</th>
                    <th>order date</th>
                    <th>fuel type</th>
                    <th>cost per liter</th>
                    <th>discount</th>
                    <th>amount liters</th>
                    <th>total cost</th>
                </tr>
            </thead>
            <tfoot>
                <tr>
                    <th>order id</th>
                    <th>company</th>
                    <th>station code</th>
                    <th>station address</th>
                    <th>order date</th>
                    <th>fuel type</th>
                    <th>cost per liter</th>
                    <th>discount</th>
                    <th>amount liters</th>
                    <th>total cost</th>
                </tr>
            </tfoot>
        </table>
        <input id="updateTableBtn" type="button" value="Update table"/><br/><br/>
        <a href="deleteAllCompanies" class="smallFont">Delete all tables from DB</a>
    </body>
</html>