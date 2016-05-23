<%-- 
    Document   : GamePage
    Created on : 02.05.2016, 11:17:24
    Author     : chanta
--%>


<%@page import="team5.datamodel.card.Color"%>
<%@page import="java.util.ArrayList"%>
<%@page import="team5.datamodel.card.Colors"%>
<%@page import="team5.server.actions.SelectRoom"%>
<%@page import="team5.server.actions.Game"%>
<%@page import="team5.datamodel.card.Card"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Game game = SelectRoom.getSelectRoom().getGames((int) session.getAttribute("room"));
    System.out.println(game);
    Colors colors = new Colors();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"><!--;text/html; charset=UTF-8-->
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
                    <!--todo вставить пользователей-->
                    <% for (int i = 0; i < game.getRoom().countGamers(); i++) {%>
                    <tr>
                        <td><%=game.getRoom().getGamer(i).getGamerLogin()%></td>
                    </tr>                        
                    <%}%>
                </table>
            </form>
        </div>
        <div class="clear">  
        </div>

        <div id="cardPanel">
            <form name="tableCard" action="gamePage.jsp">
                <%if (game.getLastCard() != null) {%>
                <input type="image" name="card" value="<%=game.getLastCard().getCardId()%>" src="src/main/webapp/styles/uno_cards/<%=game.getLastCard().getIconId()%>-<%=colors.getColorById(game.getLastCard().getColorId())%>.jpg">
                <%}%>
            </form>
        </div>
        <div id="cardsHandPanel">
            <form name="handCards" action="gamePage.jsp">
                <%
                    if (!game.getFirstDistrib(game.getRoom().getGamerNumber(game.getRoom().getGamer((String) session.getAttribute("login"))))) {
                        game.distribCard((String) session.getAttribute("login"));
                    }
                    ArrayList<Card> cards = game.getRoom().getGamer((String) session.getAttribute("login")).getHandscards();
                    for (int i = 0; i < cards.size(); i++) {%>
                <input type="image" name="card"  alt="cards" src="/styles/uno_cards/2-yellow.jpg"  height='50' weight= "40"><!--<%=cards.get(i).getIconId()%>-<%=colors.getColorById(cards.get(i).getColorId())%>  value="<%=cards.get(i).getCardId()%>"-->
                <%}%>
                <!--            вставить карты-->
            </form> 
            <%String[] values = null;
                values = request.getParameterValues("card");
                if (values != null) {
                    for (int i = 0; i < values.length; i++) {
                        if (values[i] != null) {
                            String s = game.gameProcess((String) session.getAttribute("login"), Integer.getInteger(values[i]));
                            if (s.equals("Wrong card")) {%>
            <!--вывод сообщения о неправильной карте-->
            <%}
                if (s.equals("Is not your turn")) {%>
            <!--вывод сообщения о том, что не твой ход-->
            <%}
                        }
                    }
                }
            %>
        </div>

        <div id="cardsDeskPanel">
            <form name="takeCard" action="gamePage.jsp">
                <input type="submit" name="takeCard" value="TakeCard">
            </form>
            <%
                if (request.getParameter("takeCard") != null) {
                    if (request.getParameter("takeCard").equals("TakeCard")) {
                        if (!game.takeCard((String) session.getAttribute("login"))) {
                            // вывод сообщения о невозможности взять карту
                        }

                    }
                }
            %>
        </div>

        <%@ include file="footer.jsp" %>
