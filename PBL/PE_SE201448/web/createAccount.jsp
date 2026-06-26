<%-- 
    Document   : createAccount
    Created on : Jun 26, 2026, 7:45:17 AM
    Author     : TGDD-MSI
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Account</title>
    </head>
    <body>
        <h1>Create New Account</h1>
        <form action="MainController" method="POST">
            <c:set var="errors" value="${requestScope.CREATE_ERRORS}" />
            Username* <input type="text" name="txtUsername" value="${param.txtUsername}" /> (e.g. 6 - 30 characters) <br/>
            <c:if test="${not empty errors.usernameLengthErr}">
                <font color="red">
                    ${errors.usernameLengthErr}
                    </font> <br/>
            </c:if>
            <c:if test="${not empty errors.usernameExisted}">
                <font color="red">
                    ${errors.usernameExisted}
                    </font> <br/>
            </c:if>
            Password* <input type="password" name="txtPassword" value="" /> (e.g. 6 - 20 characters) <br/>
            <c:if test="${not empty errors.passwordLengthErr}">
                <font color="red">
                    ${errors.passwordLengthErr}
                    </font> <br/>
            </c:if>
            Confirm* <input type="password" name="txtConfirm" value="" /> <br/>
            <c:if test="${not empty errors.confirmNotMatched}">
                <font color="red">
                    ${errors.confirmNotMatched}
                    </font> <br/>
            </c:if>
                    Full name* <input type="text" name="txtFullname" value="${param.txtFullname}" /> (e.g. 2 - 50 characters) <br/>
            <c:if test="${not empty errors.fullNameLengthErr}">
                <font color="red">
                    ${errors.fullNameLengthErr}
                    </font> <br/>
            </c:if>
            <input type="submit" value="Create New Account" name="action" />
            <input type="reset" value="Reset" />
        </form>
    </body>
</html>
