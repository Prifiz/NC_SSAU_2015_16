<%-- 
    Document   : errorPage
    Created on : 14.04.2016, 17:35:53
    Author     : chanta
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="styles/templatePage.css"/>
        <title>errorPage</title>
    </head>
    <body>

<%@ include file="header.jsp" %>

<h3 class="title">WHOOOOOPS...</h3><div class="tabs"></div>
<h3 class="title">Sorry!:(</h3>
<h3 class="title">The page you were looking for can't be found</h3>
<p></p>
<div id="return"><a href="/homePage.jsp" >RETURN TO THE HOME PAGE</a></div>

<%@ include file="footer.jsp" %>