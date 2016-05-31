<%-- 
    Document   : rules
    Created on : 31.05.2016, 12:07:20
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