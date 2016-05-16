<%-- 
    Document   : footer
    Created on : 07.04.2016, 22:31:35
    Author     : chanta
--%>



 <%@page import="java.io.IOException"%>
<%@page import="java.io.FileNotFoundException"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.BufferedReader"%>
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
                            //todo ??????? ?????????? ????????? log.debug(ex.getMessage());
                        } catch (IOException ex) {
                            //todo ??????? ?????????? ????????? log.debug(ex.getMessage());
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

