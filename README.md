# fuel-orders-web
Servlets JSP JPA Jquery AJAX MVC web application for adding orders from filling stations.

This application is for creating orders from filling stations and storing them to the database.
Web application is based on MVC pattern and includes the folowing technologies:

data representation: 
- JSP, JavaScript, Jquery, AJAX;
- for validation input data on the client side used jquery.form-validator.min.js library;
- for rendering table data used dataTables.min.js lib; 
- application uses AJAX strategy for populating drop down comboboxes from database in /WEB-INF/view/home.jsp and for output data about all orders in home.jsp. 
- web pages uses CDN for load scripts.

data persistence: 
- JPA, through eclipse-link persistence provider. 
- persistence.xml includes two persistence units: for Java DB(Derby), and for MySql databases. 
- class that handle each operation with database: com.fuelorders.dao.FuelOrdersManager, it's a data acess object. It serves persistence of the following entities: com.fuelorders.entity.Company, com.fuelorders.entity.FillingStation and com.fuelorders.entity.FuelOrder. One Company has many filling station and one filling station has many orders;

controller: 
- com.fuelorders.servlet.ContollerServlet class handles all requests and responses to/from application and then uses com.fuelorders.dao.FuelOrdersManager class for retrieve or saving data from/to database. 

other helper classes:
- com.fuelorders.util.RequestParamHelper class uses org.apache.commons.beanutils library for populating DTO objects with requests parameters;
- com.fuelorders.servlet.FuelOrdersWebListener class is used for initialize FuelOrdersManager data acess object and place it in servlet context scope;
- com.fuelorders.servletRequestAttribFilter class filter all dispatched requests and add 'companies' attribute in request scope. This attribute stores list of all companies in database, which used for populating 'Company name' dropdown list in home.jsp and createFillingStation.jsp; 
- com.fuelorders.util.DateConverter class is responsible for date conversion operations in application.
