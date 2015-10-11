<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<html>
<head>
    <title>Login</title>
</head>

<body>

<c:url value="/resources/css/main.css" var="main"/>
<link rel="stylesheet" type="text/css" media="screen" href="${main}"/>


<div class="welcome">
    <label><s:message code="library.welcome"/></label>
</div>
<jsp:include page="language.jsp"/>
<br/>
<br/>
<br/>

<div class="error">
    <c:choose>
    <c:when test="${error}">
        <h1 align="center"><s:message code="error.login.message"/></h1>
    </c:when>
</c:choose>
</div>

<div class="login">
    <form class="table dataForm" action="login" method="post">
        <div class="tableRow">
            <s:message code="username.username"/>
            <input class="rightBlock" type="text" name="username"/>
        </div>
        <br/>

        <div class="tableRow">
            <s:message code="password.password"/> <input class="rightBlock" type="password" name="password"/>
        </div>
        <br>
        <input type="submit"/>
        <input type="hidden"
               name="${_csrf.parameterName}"
               value="${_csrf.token}"/>
    </form>
</div>
</body>
</html>
