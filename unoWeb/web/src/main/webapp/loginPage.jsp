<%-- 
    Document   : loginPage
    Created on : 07.04.2016, 14:08:03
    Author     : андрей
--%>

<%@page import="team5.server.actions.SignIn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%--
    String login = request.getParameter("string");
    String password = request.getParameter("string2");
    SignIn in = new SignIn();
    boolean b = in.sign(login, password);
    if(b){
        
    }
--%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="styles/loginPage.css"/>
        <title>template</title>
    </head>
    <body>
        <%@ include file="header.jsp" %>
        <form name="signInForm" action="loginPage.jsp">
            <input type="text" name="login" placeholder="login" id="loginText" value=${param.string}>
            <input type="password" name="password" placeholder="password" id="passwordText" value=${param.string2}>
            <input type="submit" name="signIN" value="Sign in" id="signInButtom">
        </form>

        <%@ include file="footer.jsp" %>