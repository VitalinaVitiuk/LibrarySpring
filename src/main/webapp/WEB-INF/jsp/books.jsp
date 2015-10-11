<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>

<c:url value="/resources/css/main.css" var="main"/>
<link rel="stylesheet" type="text/css" media="screen" href="${main}"/>

<html>
<head>
    <title>books</title>
</head>

<body>
<jsp:include page="header.jsp"/>
<br>

<c:choose>
    <c:when test="${empty books}">
        <s:message code="error.book.notfound"/>
    </c:when>
    <c:otherwise>
        <table align="center" border="5">
            <thead>
            <tr>
                <sec:authorize access="hasRole('ADMIN')">
                    <th></th>
                    <th></th>
                    <th><s:message code="book.id"/></th>
                </sec:authorize>
                <th><s:message code="book.author"/></th>
                <th><s:message code="book.title"/></th>
                <th><s:message code="book.language"/></th>
                <th><s:message code="book.year"/></th>
                <th><s:message code="book.typeOfIssuance"/></th>
                <th><s:message code="book.status"/></th>
                <sec:authorize access="hasRole('USER')">
                    <th></th>
                </sec:authorize>
            </tr>
            </thead>

            <c:forEach var="book" items="${books}">
            <tr>

                <sec:authorize access="hasRole('ADMIN')">
                <td>
                    <c:choose>
                        <c:when test="${book.status=='AVAILABLE'}">
                            <form action="disablebook" method="post">
                                <input type="submit" value="<s:message code="book.disable"/>"/>
                                <input type="hidden" name="id" value=${book.id}>
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            </form>
                        </c:when>
                        <c:when test="${book.status == 'NOT_AVAILABLE'}">
                            <form action="enablebook" method="post">
                                <input type="submit" value="<s:message code="book.enable"/>"/>
                                <input type="hidden" name="id" value=${book.id}>
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            </form>
                        </c:when>
                    </c:choose>
                </td>
                <td>
                    <form action="editbook" method="get">
                        <input type="submit" name="command" value="<s:message code="admin.edit"/>"/>
                        <input type="hidden" name="bookId" value=${book.id}>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </form>
                </td>
                <td>${book.id}</td>
                </sec:authorize>

                <td>${book.author}</td>
                <td>${book.title}</td>
                <td>${book.language}</td>
                <td>${book.year}</td>

                <c:choose>
                <c:when test="${book.issuanceType=='TWO_WEEKS'}">
                <td><s:message code="book.twoWeeks"/></td>
                </c:when>
                <c:when test="${book.issuanceType=='THREE_MONTHS'}">
                <td><s:message code="book.threeMonths"/></td>
                </c:when>
                <c:otherwise>
                <td><s:message code="book.readingHall"/></td>
                </c:otherwise>
                </c:choose>


                <c:choose>
                <c:when test="${book.status=='AVAILABLE'}">
                <td><s:message code="book.available"/></td>
                </c:when>
                <c:when test="${book.status=='NOT_AVAILABLE'}">
                <td><s:message code="book.not_available"/></td>
                </c:when>
                <c:otherwise>
                <td><s:message code="book.ordered"/></td>
                </c:otherwise>
                </c:choose>


                <c:choose>
                <c:when test="${book.status=='AVAILABLE'}">
                <sec:authorize access="hasRole('USER')">
                <td>
                    <form action="orderbook" method="post">
                        <input type="submit" name="command" value="<s:message code="book.order"/>"/>
                        <input type="hidden" name="bookId" value=${book.id}>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </form>
                </td>
                </sec:authorize>

                </c:when>
                <c:otherwise>


                <sec:authorize access="hasRole('USER')">
                <td>
                    <form action="orderbook" method="post">
                        <input disabled="disabled" type="submit" name="command"
                               value="<s:message code="book.order"/>"/>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </form>
                </td>
                </sec:authorize>


                </c:otherwise>
                </c:choose>
                </c:forEach>
        </table>

    </c:otherwise>
</c:choose>

<c:if test="${not empty pageCount and pageCount > 1}">
    <c:forEach var="page" begin="0" end="${pageCount - 1}" varStatus="status">
        <li><a class="a<c:if test="${currentPage eq page}"> b</c:if>"
        <c:if test="${not empty language}">
               href="${pageUrl}?language=${language}&page=${page}">${page + 1}</a>
            </c:if>
            <c:if test="${not empty author}">
                href="${pageUrl}?author=${author}&page=${page}">${page + 1}</a>
            </c:if>
            <c:if test="${not empty title}">
                href="${pageUrl}?title=${title}&page=${page}">${page + 1}</a>
            </c:if>
            <c:if test="${not empty year}">
                href="${pageUrl}?year=${year}&page=${page}">${page + 1}</a>
            </c:if>
        </li>
    </c:forEach>
</c:if>

</body>
</html>

