<%-- 
    Document   : registrationPage
    Created on : 07.04.2016, 14:44:31
    Author     : андрей
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="styles/registrationPage.css"/>
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
                <!--<input type="button" value="login" name="loginButton" id="loginButton">-->
                <!--<input type="button" value="register" name="registerButton" id="registerButton">-->
                <!--<input type="button" value="to the home page" name="homeButton" id="homeButton">-->

                <div id="work-zone"> 
                    <div id="container-work-zone">
                        <form name="signInForm" action="loginPage.jsp">
                            <input type="text" name="login" placeholder="login" id="loginText">
                            <input type="text" name="password" placeholder="password" id="passwordText">
                            <input type="text" name="email" placeholder="email" id="emailText">
                            <input type="text" name="name" placeholder="name" id="nameText">
                            <input type="text" name="surname" placeholder="surname" id="surnameText">
                            <input type="text" name="country" placeholder="country" id="countryText">
                            <input type="text" name="city" placeholder="city" id="cityText">
                            <input type="submit" name="OK" value="OK" id="okButtom">
                            <input type="submit" name="cancel" value="Cancel" id="cancelButtom">
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