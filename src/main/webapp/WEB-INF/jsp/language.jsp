<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<div class="langstyle">
    <c:choose>
        <c:when test="${param.locale ne null }">
            <c:set var="language" value="${param.locale}"/>
        </c:when>
        <c:otherwise>
            <c:set var="language" value="${cookie.libraryLocaleCookie.value }"/>
        </c:otherwise>
    </c:choose>

        <form class="langstyle">
            <select class="langstyle" name="locale" onchange="submit()">
            <option value="ua" ${fn:containsIgnoreCase(language, 'ua') ? 'selected' : ''}>Українська</option>
            <option value="en" ${fn:containsIgnoreCase(language, 'en') ? 'selected' : ''}>English</option>
        </select>
    </form>
</div>