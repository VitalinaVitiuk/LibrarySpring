<%@ page language="java" pageEncoding="utf8" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<html>

<head>
    <title>Search</title>
</head>
<body>

<jsp:include page="header.jsp"/>

<h1><s:message code="search.choose"/></h1>

<div>
    <form action="searchByAuthor" method="get">
        <s:message code="search.author"/> <input type="text" name="author">
        <input type="submit" name="command" value="<s:message code="search.search"/>"/>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>

    <br/>

    <form action="searchByTitle" method="get">
        <s:message code="search.title"/> <input type="text" name="title">
        <input type="submit" name="command" value="<s:message code="search.search"/>"/>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>

    <br/>

    <form action="searchByLanguage" method="get">
        <s:message code="search.language"/> <input type="text" name="language">
        <input type="submit" value="<s:message code="search.search"/>"/>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>

    <br/>

    <form action="searchByYear" method="get">
        <s:message code="search.year"/> <input type="number" name="year">
        <input type="submit" name="command" value="<s:message code="search.search"/>"/>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>


</div>

</body>
</html>
