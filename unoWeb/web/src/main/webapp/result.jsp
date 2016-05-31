<%-- 
    Document   : result
    Created on : 24.03.2016, 1:05:23
    Author     : Dmitry
--%>

<%@page import="team5.web.DataBaseRequest"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    DataBaseRequest requestWork = (DataBaseRequest) request.getSession().getAttribute("searchRequest");
    if (requestWork == null) {
        response.setHeader("Refresh", "5; URL=index.jsp");
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="styles/style.css"/>
        <title>Test result</title>
    </head>
    <body>
        <h1>Здесь вы можете скачать данные этого бедолаги</h1>
    </body>
    
     <form>  
            <% if (requestWork != null) {%>
            <table>              
                <tr>
                    <td class="string">
                        Результат:
                    </td>
                    <td class="result">
                        <%= requestWork.getResult()%>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" class="string">
                        <a href="index.jsp">На главную страницу</a>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" class="string">
                        <a download href="ResultXmlServlet">XML</a> 
                    </td>
                </tr>
            </table> 
            <% } else {%>
            <table>
                <tr>
                    <td class="errmessage">
                        Ошибка при выполнении!
                    </td>
                </tr>
                <tr>
                    <td class="string">
                        Вы будете автоматически перенаправлены на <a href="index.jsp">index.jsp</a> через 5 секунд
                    </td>
                </tr>
            </table>
            <% }%>
        </form>
</html>
