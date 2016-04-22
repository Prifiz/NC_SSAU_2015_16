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
                    <tr>
                        <td>Users table</td>
                        <td>
                            <input type="submit" name="usersTable" value="select" id="usersTableButtom">                 
                        </td>
                    </tr>

                </table>
            </form>
        </div>
        <%@ include file="footer.jsp" %>