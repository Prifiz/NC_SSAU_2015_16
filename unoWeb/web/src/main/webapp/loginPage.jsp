<%-- 
    Document   : loginPage
    Created on : 07.04.2016, 14:08:03
    Author     : андрей
--%>

<%@page import="team5.server.actions.SignIn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="styles/templatePage.css"/>
        <title>template</title>
    </head>
    <body>
        <%@ include file="header.jsp" %>
        <div id="table_result">
            <form name="signInForm" action="loginPage.jsp">
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
                            <input type="password" name="password" placeholder="password" id="passwordText" value=${param.password}>
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input type="submit" name="signIN" value="Sign in" id="signInButtom">
                            <%
    String login = request.getParameter("login");
    String password = request.getParameter("password");
    System.out.println(login+" "+password);
    if ((login != null) && (password != null)) {
        SignIn in = new SignIn();
        boolean b = in.sign(login, password);
        if (b) {
            response.sendRedirect("selectRoomPage.jsp");
        }
    }
%>
                        </td>
                    </tr>
                </table>

<!--            <input type="text" name="login" placeholder="login" id="loginText" value=${param.string}>
    <input type="password" name="password" placeholder="password" id="passwordText" value=${param.string2}>
    <input type="submit" name="signIN" value="Sign in" id="signInButtom">-->
            </form>

        </div>
        <%@ include file="footer.jsp" %>