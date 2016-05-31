<%-- 
    Document   : homePage
    Created on : 07.04.2016, 22:27:55
    Author     : chanta
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="styles/templatePage.css"/>
        <title>Home_page_Uno_game</title>
    </head>
    <body>

<%@ include file="header.jsp" %>

<h3 class="title">Welcome to the game Uno</h3><div class="tabs"></div>
<h4>This game was created and designed specifically to enable you to have a good 
    time alone or with friends over a game of Uno
    </h4>
<h4>We wish you enjoy the game and have a good time!</h4>

<input type="image" name="home1"  alt="home1" src="styles/home1.jpg" height='400' width='380' id="home1">
 
<%@ include file="rules.jsp" %>
<%@ include file="footer.jsp" %>
