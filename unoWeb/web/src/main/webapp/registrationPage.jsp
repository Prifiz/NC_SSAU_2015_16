<%-- 
    Document   : registrationPage
    Created on : 07.04.2016, 14:44:31
    Author     : андрей
--%>

<%@page import="team5.server.actions.Registrations"%>
<%@page import="team5.datamodel.user.User"%>
<%@page import="team5.datamodel.user.adress.Address"%>
<%@page import="team5.datamodel.user.ServiceInfo"%>
<%@page import="team5.datamodel.user.PrivateInformation"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="styles/templatePage.css"/>
        <title>registration</title>
    </head>
    <body>
        <%@ include file="header.jsp" %>
        <div id="table_result_registration">
            <form name="registrationForm">
                <table>
                    <tr>
                        <td>Login</td>
                        <td>
                            <input type="text" name="login" placeholder="login" id="loginText" value=${param.login}>                  
                        </td>
                    </tr>
                    <tr>
                        <td>Password</td>
                        <td>
                            <input type="text" name="password" placeholder="password" id="passwordText" value=${param.password}>  
                        </td>
                    </tr>
                    <tr>
                        <td>Email</td>
                        <td><input type="text" name="email" placeholder="email" id="emailText" value=${param.email}>
                        </td>
                    </tr>
                    <tr>
                        <td>Name</td>
                        <td><input type="text" name="name" placeholder="name" id="nameText" value=${param.name}>
                        </td>
                    </tr>
                    <tr>
                        <td>Surname</td>
                        <td><input type="text" name="surname" placeholder="surname" id="surnameText" value=${param.surname}>
                        </td>
                    </tr>
                    <tr>
                        <td>Country</td>
                        <td><input type="text" name="country" placeholder="country" id="countryText" value=${param.country}>
                        </td>
                    </tr>
                    <tr>
                        <td>City</td>
                        <td><input type="text" name="city" placeholder="city" id="cityText" value=${param.city}>
                        </td>
                    </tr>
                    <tr>
                        <td><input type="submit" name="OK" value="OK" id="okButtom"></td>
                        <td>
                            <%
                                String login = request.getParameter("login");
                                String password = request.getParameter("password");
                                String email = request.getParameter("emil");
                                String name = request.getParameter("name");
                                String surname = request.getParameter("surname");
                                String country = request.getParameter("country");
                                String city = request.getParameter("city");
                                if (login != null) {
                                    PrivateInformation privateInf = new PrivateInformation(login, name, surname);
                                    ServiceInfo service = new ServiceInfo(login, password, email);
                                    Address address = new Address(login, city, country);
                                    User user = new User(privateInf, address, service);
                                    Registrations reg = new Registrations();
                                    reg.registrationUser(privateInf, service, address, user);
                                }
                            %>
                            <form action="homePage.jsp">
                                <input type="submit" name="cancel" value="Cancel" id="cancelButtom">
                            </form>
                        </td>
                    </tr>
                </table>

            </form>
        </div>
        <%@ include file="rules.jsp" %>
        <input type="button" value="BACK" name="backButton" id="backButton" onClick='location.href="homePage.jsp"'>
        <%@ include file="footer.jsp" %>