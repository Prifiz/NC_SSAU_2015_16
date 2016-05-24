<%-- 
    Document   : selecRoomPage
    Created on : 11.04.2016, 13:20:58
    Author     : андрей
--%>
<%@page import="team5.server.actions.SelectRoom"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="styles/templatePage.css"/>
        <title>template</title>
        <script type="text/javascript">
            function wait(){
                alert('Ожидание других игроков < 30 секунд');
            }
        </script>
    </head>
    <body>

        <%@ include file="header.jsp" %>

        <h3>WELCOME TO THE GAME</h3>
        <p></p>
        <h4>Good luck! We wish you success ☘</h4>
        <div id="table_rooms">
            <ul>

                <form name="selectRoomForm" action="selectRoomPage.jsp">
                    <table>
                        <tr>
                            <td>
                                <input type="submit" onclick="wait()" name="Room1" value="Room 1">
                            </td>
                        <tr>
                            <td>
                                <input type="submit" onclick="wait()"  name="Room2" value="Room 2">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <input type="submit" onclick="wait()"  name="Room3" value="Room 3">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <input type="submit" onclick="wait()"  name="Room4" value="Room 4">
                            </td>
                        </tr>

                        <%
                            System.out.println(session.getAttribute("login"));
                            String room1 = request.getParameter("Room1");
                            String room2 = request.getParameter("Room2");
                            String room3 = request.getParameter("Room3");
                            String room4 = request.getParameter("Room4");
                            int room = 0;
                            SelectRoom selectRoom = SelectRoom.getSelectRoom();
                            boolean b = false;
                            if ((room1 != null) && (b == false)) {
                                b = selectRoom.select(0);
                                room = 0;
                            } else if ((room2 != null) && (b == false)) {
                                b = selectRoom.select(1);
                                room = 1;
                            } else if ((room3 != null) && (b == false)) {
                                b = selectRoom.select(2);
                                room = 2;
                            } else if ((room4 != null) && (b == false)) {
                                b = selectRoom.select(3);
                                room = 3;
                            }
                            if (b == true) {
                        %>
                        <!--<tr>
                            <td> 
                                Ожидайте <%=30 - selectRoom.getWaitTime(room)%> секунд других игроков
                            </td>
                        </tr>-->
                        
                            
                        <%
                            boolean game = selectRoom.waitGamers(room, (String) session.getAttribute("login"));
                            System.out.println(game);
                            //while (selectRoom.getWaitTime(room) != 30) {
                            //}
                            if (game) {
                                session.setAttribute("room", room);
                                response.sendRedirect("gamePage.jsp");
                        %>

                        <!--<jsp:forward page="gamePage.jsp"/>-->
                        <%} else {
                            b = false;
                        %>
                       <!--<tr>
                            <td> 
                                Ожидайте <%=30 - selectRoom.getWaitTime(room)%> секунд других игроков
                            </td>
                        </tr>-->  
                        <%
//сообщение о том. что в комнату зайти нельзя
                            }
                        } else {
                        %>
                        <!--<tr>
                            <td> 
                                Ожидайте <%=30 - selectRoom.getWaitTime(room)%> секунд других игроков
                            </td>
                        </tr>-->  
                        <%
                                }
                                b = false;
//сообщение о том. что в комнату зайти нельзя
                            
                        %>

                    </table>
                </form>

            </ul>

        </div>



        <%@ include file="footer.jsp" %>