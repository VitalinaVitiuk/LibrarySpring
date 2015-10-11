<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>

<html>
<head>
    <title>issuances</title>
</head>
<body>

<jsp:include page="header.jsp"/>
<br>

<c:choose>
    <c:when test="${empty issuances}">
        <s:message code="issuances.notfound"/>
    </c:when>
    <c:otherwise>
        <table align="center" border="5">
            <thead>
            <se>
                <security:authorize access="hasRole('ROLE_ADMIN')">
                <th><s:message code="user.id"/></th>
                <th><s:message code="user.firstName"/></th>
                <th><s:message code="user.lastName"/></th>
                <th><s:message code="issuance.book.id"/></th>
                </security:authorize>
                <th><s:message code="book.author"/></th>
                <th><s:message code="book.title"/></th>
                <th><s:message code="book.year"/></th>
                <th><s:message code="issuance.startDate"/></th>
                <th><s:message code="issuance.endDate"/></th>
                <th><s:message code="issuance.issuanceType"/></th>
                <th><s:message code="issuance.status"/></th>
                </tr>
            </thead>

            <c:forEach var="issuance" items="${issuances}">
            <tr>
                <security:authorize access="hasRole('ROLE_ADMIN')">
                <td>${issuance.user.id}</td>
                <td>${issuance.user.firstName}</td>
                <td>${issuance.user.lastName}</td>
                <td>${issuance.book.id}</td>
                </security:authorize>
                <td>${issuance.book.author}</td>
                <td>${issuance.book.title}</td>
                <td>${issuance.book.year}</td>
                <td>${issuance.startDate}</td>
                <td>${issuance.endDate}</td>


                <c:choose>
                <c:when test="${issuance.issuanceType=='TWO_WEEKS'}">
                <td><s:message code="issuance.twoWeeks"/></td>
                </c:when>
                <c:when test="${issuance.issuanceType=='THREE_MONTHS'}">
                <td><s:message code="issuance.threeMonths"/></td>
                </c:when>
                <c:otherwise>
                <td><s:message code="issuance.readingHall"/></td>
                </c:otherwise>
                </c:choose>

                <c:choose>
                <c:when test="${issuance.status=='READY'}">
                <td><s:message code="issuance.ready"/></td>
                </c:when>
                <c:when test="${issuance.status=='PROCESSING'}">
                <td><s:message code="issuance.processing"/></td>
                </c:when>
                <c:when test="${issuance.status=='ACTIVE'}">
                <td><s:message code="issuance.active"/></td>
                </c:when>
                <c:otherwise>
                <td><s:message code="issuance.closed"/></td>
                </c:otherwise>
                </c:choose>


                <security:authorize access="hasRole('ROLE_ADMIN')">

                <c:choose>
                <c:when test="${issuance.status=='PROCESSING'}">
                <td>
                    <form action="fulfil" method="post">
                        <input type="submit" name="command" value="<s:message code="issuance.fulfil"/>"/>
                        <input type="hidden" name="issuanceId" value=${issuance.id}>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </form>
                </td>
                </c:when>
                <c:when test="${issuance.status=='READY'}">
                <td>
                    <form action="issue" method="post">
                        <input type="submit" name="command" value="<s:message code="issuance.issue"/>"/>
                        <input type="hidden" name="issuanceId" value=${issuance.id}>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </form>
                </td>
                </c:when>
                <c:when test="${issuance.status=='ACTIVE'}">
                <td>
                    <form action="close" method="post">
                        <input type="submit" name="command" value="<s:message code="issuance.close"/>"/>
                        <input type="hidden" name="issuanceId" value=${issuance.id}>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </form>
                </td>
                </c:when>
                </c:choose>
                </security:authorize>
                </c:forEach>
        </table>
    </c:otherwise>
</c:choose>

<br>
<br>

</body>
</html>