<%-- 
    Document   : waitingRoomPage
    Created on : 19.04.2016, 16:29:38
    Author     : chanta
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="styles/templatePage.css"/>
        <title>waitingRoom</title>
    </head>
    <body>

        <%@ include file="header.jsp" %>

        <h3>players in the room</h3>
        <!--<script src="http://megatimer.ru/s/11cdec0a13c575e609ad5f360e305dc7.js"></script> таймер-->
        <div id="table_users">
            <form name="userForm">
                <table>
                    <tr>
                        <td>name 1</td>
                    </tr>
                    <tr>
                        <td>name 2</td>
                    </tr>
                    <tr>
                        <td>name 3</td>
                    </tr>
                    <tr>
                        <td>name 4</td>
                    </tr>
                    
                </table>
            </form>
        </div>
        <div id="selectLink">         
            <a href="/homePage.jsp" >play</a></div>
        <%@ include file="footer.jsp" %>