<%-- 
    Document   : registrationPage
    Created on : 07.04.2016, 14:44:31
    Author     : андрей
--%>

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
                            <input type="text" name="login" placeholder="login" id="loginText">                  
                        </td>
                    </tr>
                    <tr>
                        <td>Password</td>
                        <td>
                            <input type="text" name="password" placeholder="password" id="passwordText">  
                        </td>
                    </tr>
                    <tr>
                        <td>Email</td>
                        <td><input type="text" name="email" placeholder="email" id="emailText">
                        </td>
                    </tr>
                    <tr>
                        <td>Name</td>
                        <td><input type="text" name="name" placeholder="name" id="nameText">
                        </td>
                    </tr>
                    <tr>
                        <td>Surname</td>
                        <td><input type="text" name="surname" placeholder="surname" id="surnameText">
                        </td>
                    </tr>
                    <tr>
                        <td>Country</td>
                        <td><input type="text" name="country" placeholder="country" id="countryText">
                        </td>
                    </tr>
                    <tr>
                        <td>City</td>
                        <td><input type="text" name="city" placeholder="city" id="cityText">
                        </td>
                    </tr>
                    <tr>
                        <td><input type="submit" name="OK" value="OK" id="okButtom"></td>
                        <td> <input type="submit" name="cancel" value="Cancel" id="cancelButtom">
                        </td>
                    </tr>
                </table>
                <!--                <form name="signInForm" action="loginPage.jsp">
                                    <input type="text" name="login" placeholder="login" id="loginText">
                                    <input type="text" name="password" placeholder="password" id="passwordText">
                                    <input type="text" name="email" placeholder="email" id="emailText">
                                    <input type="text" name="name" placeholder="name" id="nameText">
                                    <input type="text" name="surname" placeholder="surname" id="surnameText">
                                    <input type="text" name="country" placeholder="country" id="countryText">
                                    <input type="text" name="city" placeholder="city" id="cityText">
                                    <input type="submit" name="OK" value="OK" id="okButtom">
                                    <input type="submit" name="cancel" value="Cancel" id="cancelButtom">-->
            </form>
        </div>
        <%@ include file="footer.jsp" %>