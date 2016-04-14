<%-- 
    Document   : loginPage
    Created on : 07.04.2016, 14:08:03
    Author     : андрей
--%>

<%@page import="team5.server.actions.SignIn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String login = request.getParameter("string");
    String password = request.getParameter("string2");
    SignIn in = new SignIn();
    boolean b = in.sign(login, password);
    if(b){
        
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="styles/loginPage.css"/>
        <title>template</title>
    </head>
    <body>
        <div id="header">
            <div id="header_wrapper">
                <!--                //todo вставить ссылку на возврат на стартовую страницу-->
                <!--<a href="/" title="Home Page"> <h1>UNO</h1></a><br />-->
                <h1>UNO</h1>
                <h2>Uno is an American card game</h2>  
            </div>
        </div>
        <div id="container-shadows"> 
            <div id="container">
                <div id="main" class="tile show">
                    <ul>
                        <li><a href="loginPage.jsp" >login</a></li>
                        <li><a  href="registrationPage.jsp" >registration</a></li>
                    </ul>
                </div>
                <!--<div class="content"><a href="//todo вставить ссылку на регистрацию" title="registration">registration</a>-->

                <!--<input type="button" value="BACK" name="backButton" id="backButton">-->
                <input type="button" value="view rules" name="rulesButton" id="rulesButton">

                <div id="work-zone"> 
                    <div id="container-work-zone">
                        <form name="signInForm" action="loginPage.jsp">
                            <input type="text" name="login" placeholder="login" id="loginText" value=${param.string}>
                            <input type="password" name="password" placeholder="password" id="passwordText" value=${param.string2}>
                            <input type="submit" name="signIN" value="Sign in" id="signInButtom">
                        </form>
                    </div>
                </div>

                <input type="button" value="BACK" name="backButton" id="backButton">
                <div id="footer">
                    <div id="copyright">2015-2016 &copy; TEAM5</div>
                </div>

            </div>     <!-- ...container -->
        </div><!-- ...container-shadows -->

    </body>
</html>
