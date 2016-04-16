<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/taglib.jsp"%>
<select name="${name}">
<c:forEach var="item" items="${CityList.list}">
<option value="${item.id}">${item.districtName}</option>
</c:forEach>
</select>