<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/taglib.jsp"%>
<select name="${name}">
    <c:forEach var="item" items="${departList.list}">
        <option value="${item.orgcode}">${item.orgname}</option>
    </c:forEach>
</select>