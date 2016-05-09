<%-- 
    Document   : templatePage
    Created on : 03.04.2016, 16:06:01
    Author     : chanta
--%>

<%@page import="org.apache.log4j.Logger"%>
<%@page import="java.io.IOException"%>
<%@page import="java.io.FileNotFoundException"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="styles/templatePage.css"/>
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
                        <li><a href="/login" >login</a></li>
                        <li><a href="/registration" >registration</a></li>
                    </ul>
                </div>
                <!--<div class="content"><a href="//todo вставить ссылку на регистрацию" title="registration">registration</a>-->


                <div id="work-zone"> 
                    <div id="container-work-zone">
                    </div>
                </div>
                <%!private String textRule(String fileName) {
                        String text = "";
                        String s;
                        try {
                            BufferedReader br = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(fileName)));
                            while ((s = br.readLine()) != null) {
                                text += s;
                            }
                        } catch (FileNotFoundException ex) {
                            //todo вствить обработчик эксепшена log.debug(ex.getMessage());
                        } catch (IOException ex) {
                            //todo вствить обработчик эксепшена log.debug(ex.getMessage());
                        }
                        return text;

                    }
                %>

                <div id="rules">
                    <h4>Rules of the game UNO </h4>
                    <%=textRule("/textRule.txt")%>
                </div>

                <input type="button" value="BACK" name="backButton" id="backButton">
                <div id="footer">
                    <div id="copyright">2015-2016 &copy; TEAM5</div>
                </div>

            </div>     <!-- ...container -->
        </div><!-- ...container-shadows -->

    </body>
</html>
