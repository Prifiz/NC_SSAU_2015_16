<%-- 
    Document   : adminPage
    Created on : 18.04.2016, 21:02:33
    Author     : chanta
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="styles/templatePage.css"/>
        <title>AdminPage</title>
    </head>
    <body>
        <%@ include file="header.jsp" %>

        <h3 class="title">Hello, Admin! Welcome!</h3>
        <div id="table_result_admin">
            <form name="adminForm">
                <table>
                    <!--<h4>Users table</h4>-->
                    <tr>
                        <td>Users table</td>
                        <td>
                            <input type="button" name="usersTable" value="select" id="usersTableButtom" id="backButton" onClick='location.href = "userTabletPage.jsp"'>                 
                        </td>
                    </tr>
                </table>
            </form>
        </div>
        <div id="admin_image">
            <input type="image" name="home2"  alt="home2" src="styles/home2.jpg" height='350' width='330' id="home2">
        </div>
        <%@ include file="rules.jsp" %>
        <input type="button" value="BACK" name="backButton" id="backButton" onClick='location.href = "homePage.jsp"'>
        <%@ include file="footer.jsp" %>