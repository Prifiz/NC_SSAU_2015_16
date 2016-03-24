<%-- 
    Document   : index
    Created on : 24.03.2016, 1:05:48
    Author     : Dmitry
--%>
<%@page import="team5.web.DataBaseRequest"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String arg1 = request.getParameter("string");
    boolean isEmpty1 = false;
    if (arg1 != null) {
        isEmpty1 = arg1.isEmpty();
        if (!isEmpty1) {
             session.setAttribute("searchRequest", new DataBaseRequest(arg1));
            response.sendRedirect("SearchServlet");
        }
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="style.css"/>
        <title>Test DataBase</title>
    </head>
    <body>
        <h1>Тестирование работы с БД</h1>
         <form method="post" action="index.jsp">
            <table>
                <tr>
                    <td class="string">
                        Найти данные пользователя
                    </td>
                    <td>
                        <input type="text" name="string" class="<%= isEmpty1 ? "empty" : "stringIn"%>" value="${param.string}"/>
                    </td>
                    <% if (isEmpty1) {%>
                    <td class="errmessage">
                        Пустая строка. Необходимо заполнить поле.
                    </td>
                    <% }%>

                </tr>
              
                <tr>
                    <td class="button">
                        <input type="submit" name="action" id="sendbutton" value="Результат"/>
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>
