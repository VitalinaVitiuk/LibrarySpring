<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<>
<head>
    <title></title>
</head>
<jsp:include page="header.jsp"/>
<br>


<sf:form cssClass="table dataForm" modelAttribute="book" action="add" method="post">

    <div class="info">
        <s:message code="form.required"/>
    </div>
    <br>

    <sf:hidden path="id"/>
    <sf:hidden path="status"/>

    <div class="tableRow">
        <span>*</span><s:message code="book.author"/>
        <sf:input class="rightBlock" path="author"/>
        <sf:errors path="author" cssClass="error"/>
        </br>
    </div>

    <br>

    <div class="tableRow">
        <span>*</span><s:message code="book.title"/>
        <sf:input class="rightBlock" path="title"/>
        <sf:errors path="title" cssClass="error"/>
        </br>
    </div>

    <br>

    <div class="tableRow">
        <span>*</span><s:message code="book.year"/>
        <sf:input type="number" class="rightBlock" path="year"/>
        <sf:errors path="year" cssClass="error"/>
        </br>
    </div>

    <br>

    <div class="tableRow">
        <span>*</span><s:message code="book.language"/>
        <sf:input class="rightBlock" path="language"/>
        <sf:errors path="language" cssClass="error"/>
        </br>
    </div>

    <br>

    <div class="tableRow">
        <span>*</span><s:message code="book.typeOfIssuance"/>
        <form:select path="issuanceType">
            <form:option value="TWO_WEEKS" label="TWO_WEEKS"/>
            <form:option value="READING_HALL" label="READING_HALL"/>
            <form:option value="THREE_MONTHS" label="THREE_MONTHS"/>
        </form:select>
        </br>
    </div>
    <br>

    <button type="submit"><s:message code="book.save"/></button>


    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

</sf:form>

</body>
</html>

