<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
    <title>add user</title>
</head>
<body>

<jsp:include page="header.jsp"/>

<br/>
<sf:form cssClass="table dataForm" modelAttribute="newuser" action="add" method="POST">

    <div class="info">
        <s:message code="form.required"/>
    </div>
    <br>

    <div class="tableRow">
        <label><span>*</span><s:message code="user.firstName"/></label>
        <sf:input class="rightBlock" path="firstName"/>
        <sf:errors path="firstName" cssClass="error"/><br/>
    </div>

    <br>

    <div class="tableRow">
        <label><span>*</span><s:message code="user.lastName"/></label>
        <sf:input class="rightBlock" path="lastName"/>
        <sf:errors path="lastName" cssClass="error"/><br/>
    </div>
    <br/>


    <div class="tableRow">
        <label><span>*</span><s:message code="username.username"/></label>
        <sf:input class="rightBlock" path="username"/>
        <sf:errors path="username" cssClass="error"/><br/>
        <br/>
    </div>

    <div class="tableRow">
        <label><span>*</span><s:message code="password.password"/></label>
        <sf:input class="rightBlock" path="password" type="password"/>
        <sf:errors path="password" cssClass="error"/><br/>
    </div>
    <br/>
    <button type="submit"><s:message code="user.save"/></button>
</sf:form>

</body>
</html>


