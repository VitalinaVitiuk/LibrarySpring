<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<html>
<head>
    <title>admin</title>
</head>
<body>

<jsp:include page="header.jsp"/>

<security:authorize access="hasRole('ROLE_USER')">
    <s:message code="admin.warning"/>
    <br/>
</security:authorize>

<security:authorize access="hasRole('ROLE_ADMIN')">
    <div>
    <h1><s:message code="admin.searchIssuancesByUser"/></h1>

    <form name="myForm" action="result" method="get">
        <lable><s:message code="admin.enterUserId"/></lable>
        <input type="number" name="userId">
        <input type="submit">
        <input type="hidden"
               name="${_csrf.parameterName}"
               value="${_csrf.token}"/>
    </form>
    <br/>
    </div>

    <div>
        <form action="searchByDate" method="get">
            <s:message code="search.date"/>
            <input type="date" name="fromDate">
            <input type="date" name="toDate">

            <input type="submit" value="<s:message code="search.search"/>"/>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
    </div>
</security:authorize>

</body>
</html>