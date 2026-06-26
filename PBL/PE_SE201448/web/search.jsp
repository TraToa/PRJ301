<%-- 
    Document   : search
    Created on : Apr 26, 2025, 8:59:02 AM
    Author     : Computing Fundamental - HCM Campus
--%>

<%@page import="pe.model.registration.RegistrationDTO"%>
<%@page import="java.util.List"%> <%-- JSP directives --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
    </head>
    <body>
        <h1>
            Search Page
        </h1>
        <form action="MainController">
            Search Value <input type="text" name="txtSearchValue" value="<%= request.getParameter("txtSearchValue") %>" /> <br/>
            <input type="submit" value="Search" name="action" />
        </form> <br/>
        <%
            String searchValue = request.getParameter("txtSearchValue");
            if (searchValue != null) {
                List<RegistrationDTO> result = (List<RegistrationDTO>) request.getAttribute("SEARCH_RESULT");

                if (result != null) { //Found
                    %>
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>Username</th>
                                <th>Password</th>
                                <th>Full name</th>
                                <th>Role</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                int count = 0;
                                for (RegistrationDTO dto : result) {
                                    %>
                            <tr>
                                <td>
                                    <%= ++count %>
                                .</td>
                                <td>
                                    <%= dto.getUsername() %>
                                </td>
                                <td>
                                    <%= dto.getPassword()%>
                                </td>
                                <td>
                                    <%= dto.getFullname()%>
                                </td>
                                <td>
                                    <%= dto.isRole() %>
                                </td>
                            </tr>
                            <%
                                } //Traverse each dto in result
                            %>
                        </tbody>
                    </table>

        <%
                } else { //Not found
                    %>
                    <h2>
                        <font color="red">
                            No record is matched!!!
                        </font>
                    </h2>
        <%
                }
            }
        %>
    </body>
</html>
