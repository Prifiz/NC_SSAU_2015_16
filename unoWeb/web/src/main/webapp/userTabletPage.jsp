<%-- 
    Document   : userTabletPage
    Created on : 21.04.2016, 20:36:48
    Author     : chanta
--%>

<%@page import="team5.datamodel.user.adress.Address"%>
<%@page import="team5.datamodel.user.ServiceInfo"%>
<%@page import="team5.datamodel.user.PrivateInformation"%>
<%@page import="java.util.ArrayList"%>
<%@page import="team5.datamodel.user.User"%>
<%@page import="team5.datamodel.user.User"%>
<%@page import="team5.datalayer.hibernate.dao.UserDao"%>
<%@page import="team5.datalayer.hibernate.dao.AddressDao"%>
<%@page import="team5.datalayer.hibernate.dao.ServiceInfoDao"%>
<%@page import="team5.datalayer.hibernate.dao.DaoFactory"%>
<%@page import="team5.datalayer.hibernate.dao.PrivateInformationDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="styles/templatePage.css"/>
        <title>AdminPage</title>
    </head>
    <body>
        <%@ include file="header.jsp" %>

        <h3 class="title">User table</h3>
        <div id="userTable" > 
            <table id="scroll-table">
                <%
                    PrivateInformationDao privateInformationDao = DaoFactory.getPrivateInformationDao();
                    ServiceInfoDao serviceInfoDao = new ServiceInfoDao();
                    AddressDao addressDao = new AddressDao();
                    UserDao userDao = new UserDao();
                     ServiceInfo serviceInfo = serviceInfoDao.getServiceInfoByLogin("Bob");
//                    ArrayList<User> users_dao = (ArrayList<User>) userDao.findAll();
//                    ArrayList<User> users = new ArrayList<>();
//                    for (int i = 0; i < users_dao.size(); i++) {
//                        PrivateInformation privateInformation = privateInformationDao.getPrivateInformationByLogin(users.get(i).getLogin());
//                        ServiceInfo serviceInfo = serviceInfoDao.getServiceInfoByLogin(users.get(i).getLogin());
//                        Address address = addressDao.getAddressByLogin(users.get(i).getLogin());
//                        users.set(i, new User(privateInformation, address, serviceInfo));
//
//                    }
                %>

                <tr>
                    <td width="20">
                        //todo
                    </td>
                    <td width="20">
                        //todo
                    </td>
                    <td width="20">
                        //todo
                    </td>
                    <td width="20">
                        //todo
                    </td>
                    <td width="20">
                        //todo
                    </td>
                    <td width="20">
                        //todo
                    </td>
                    <td width="20">
                        //todo
                    </td>
                    <td width="20">
                        //todo
                    </td>
                </tr>
                <tr>
                    <td width="20">
                        //todo
                    </td>
                    <td width="20">
                        //todo
                    </td>
                    <td width="20">
                        //todo
                    </td>
                    <td width="20">
                        //todo
                    </td>
                    <td width="20">
                        //todo
                    </td>
                    <td width="20">
                        //todo
                    </td>
                    <td width="20">
                        //todo
                    </td>
                    <td width="20">
                        //todo
                    </td>
                </tr><tr>
                    <td width="20">
                        //todo
                    </td>
                    <td width="20">
                        //todo
                    </td>
                    <td width="20">
                        //todo
                    </td>
                    <td width="20">
                        //todo
                    </td>
                    <td width="20">
                        //todo
                    </td>
                    <td width="20">
                        //todo
                    </td>
                    <td width="20">
                        //todo
                    </td>
                    <td width="20">
                        //todo
                    </td>
                </tr>
                <tr>
                    <td width="20">
                        //todo
                    </td>
                    <td width="20">
                        //todo
                    </td>
                    <td width="20">
                        //todo
                    </td>
                    <td width="20">
                        //todo
                    </td>
                    <td width="20">
                        //todo
                    </td>
                    <td width="20">
                        //todo
                    </td>
                    <td width="20">
                        //todo
                    </td>
                    <td width="20">
                        //todo
                    </td>
                </tr>
                <tr>
                    <td width="20">
                        //todo
                    </td>
                    <td width="20">
                        //todo
                    </td>
                    <td width="20">
                        //todo
                    </td>
                    <td width="20">
                        //todo
                    </td>
                    <td width="20">
                        //todo
                    </td>
                    <td width="20">
                        //todo
                    </td>
                    <td width="20">
                        //todo
                    </td>
                    <td width="20">
                        //todo
                    </td>
                </tr>
                <tr>
                    <td width="20">
                        //todo
                    </td>
                    <td width="20">
                        //todo
                    </td>
                    <td width="20">
                        //todo
                    </td>
                    <td width="20">
                        //todo
                    </td>
                    <td width="20">
                        //todo
                    </td>
                    <td width="20">
                        //todo
                    </td>
                    <td width="20">
                        //todo
                    </td>
                    <td width="20">
                        //todo
                    </td>
                </tr>
            </table>
        </div>
        <div id="userTableSearch">
            <form name="fuctional">
                <table>
                    <tr>
                        <td>
                            <input type="text" name="SearchUser" placeholder="enter login to search" id="searchText">                  
                        </td>

                        <td> <input type="submit" name="Search" value="Search" id="searchButtom"> 
                        </td>
                    </tr>

                </table>
            </form>
        </div>
        <div id="userTableFunctional">
            <form name="fuctional">
                <table>
                    <tr>
                        <td>Login</td>
                        <td>
                            <input type="text" name="login" placeholder="login" id="loginText">                  
                        </td>

                        <td>Name</td>
                        <td><input type="text" name="name" placeholder="name" id="nameText">
                        </td>
                    </tr>
                    <tr>
                        <td>Password</td>
                        <td>
                            <input type="text" name="password" placeholder="password" id="passwordText">  
                        </td>

                        <td>Surname</td>
                        <td><input type="text" name="surname" placeholder="surname" id="surnameText">
                        </td>
                    </tr>
                    <tr>
                        <td>Country</td>
                        <td><input type="text" name="country" placeholder="country" id="countryText">
                        </td>

                        <td>City</td>
                        <td><input type="text" name="city" placeholder="city" id="cityText">
                        </td>
                    </tr>
                    <tr>
                        <td>Email</td>
                        <td><input type="text" name="email" placeholder="email" id="emailText">
                        </td>
                    </tr>
                    <tr>
                        <td> <input type="submit" name="add" value="add" id="addButtom"> 
                        </td>
                    </tr>

                </table>
            </form>
        </div>
        <%@ include file="footer.jsp" %>