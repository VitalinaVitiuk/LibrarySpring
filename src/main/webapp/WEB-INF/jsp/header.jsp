<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>

<c:url value="/resources/css/main.css" var="main"/>
<link rel="stylesheet" type="text/css" media="screen" href="${main}"/>

<div class="welcome">
    <label><s:message code="library.welcome"/></label>
    <br/>
</div>
<div class="">
    <ul>

        <security:authorize access="hasRole('ROLE_USER')">
            <li><a href="<c:url value="/books/search" />"><s:message code="user.searchBook"/></a></li>
            <li><a href="<c:url value="/issuances/my" />"><s:message code="user.checkMyIssuances"/></a></li>
        </security:authorize>

        <security:authorize access="hasRole('ROLE_ADMIN')">
            <li><a href="<c:url value="/user/add" />"><s:message code="admin.addNewUser"/></a></li>
            <li><a href="<c:url value="/books/add" />"><s:message code="admin.addNewBook"/></a></li>
            <li><a href="<c:url value="/books/edit" />"><s:message code="admin.editBook"/></a></li>
            <li><a href="<c:url value="/issuances/admin" />"><s:message code="admin.searchIssuancesByUser"/></a></li>

        </security:authorize>
    </ul>
</div>

<div align="right">
    <div>
        <security:authorize access="hasRole('ROLE_ADMIN')">
            <label><s:message code="message.user.role.admin"/></label>
        </security:authorize>
        <security:authorize access="hasRole('ROLE_USER')">
            <label><s:message code="message.user.role.user"/></label>
        </security:authorize>
    </div>
    <div>
        <c:url var="logoutUrl" value="/logout"/>
        <form action="${logoutUrl}" method="post">
            <input type="submit" value="Log out"/>
            <input type="hidden"
                   name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>
        </form>
    </div>
</div>
