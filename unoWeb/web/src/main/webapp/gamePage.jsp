<%-- 
    Document   : GamePage
    Created on : 02.05.2016, 11:17:24
    Author     : chanta
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="styles/templatePage.css"/>
        <title>Game page</title>
    </head>
    <body>

        <%@ include file="header.jsp" %>
        <div id="infoPanel">
            
        </div>
        <div id="listUsers">
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
        <div class="clear">  
        </div>
        <div id="cardPanel">
<!--            вставить карту-->
        </div>
        <div id="cardsHandPanel">
<!--            вставить карты-->
        </div>
        
        <div id="cardsDeskPanel">
<!--            вставить карты-->
        </div>
        <%@ include file="footer.jsp" %>
