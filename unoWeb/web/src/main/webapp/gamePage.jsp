<%-- 
    Document   : GamePage
    Created on : 02.05.2016, 11:17:24
    Author     : chanta
--%>

<%@page import="team5.server.actions.SelectRoom"%>
<%@page import="team5.server.actions.Game"%>
<%@page import="team5.datamodel.card.Card"%>
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
        <%--<%
            Game game = SelectRoom.getSelectRoom().getGames((int) session.getAttribute("room"));
            Card[] cards = game.distribCard();
        %>--%>
        <div id="cardPanel">
            <!--            вставить карту-->
        </div>
        <div id="cardsHandPanel">
            <form name="handCards" action="gamePage.jsp">
                <%--<%
                    for (int i = 0; i < cards.length; i++) {%>
                    
                    <%}
                %>--%>
                <input type="image" src="src/main/webapp/styles/uno_cards/1-yellow.jpg">
                <!--            вставить карты-->
            </form>
        </div>

        <div id="cardsDeskPanel">
            <!--            вставить карты-->
        </div>
        <%@ include file="footer.jsp" %>
