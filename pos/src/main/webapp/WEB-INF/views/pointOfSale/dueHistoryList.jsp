<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${!empty dueHistoryList}">
	<%
		int i = 1;
	%>
	<c:forEach items="${dueHistoryList}" var="list">
		<tr style="cursor: pointer">
			<td>
				<%
					out.print(i);
				%>
			</td>
			<td>${list.payDate}</td>
			<td>${list.dueAmount}</td>
			<td>${list.paidAmount}</td>

		</tr>
		<%
			i = i + 1;
		%>
	</c:forEach>

</c:if>


<c:if test="${! empty dueHistoryListNotFound}">
	<tr>
		<td colspan="3"><p>${dueHistoryListNotFound}</p></td>
	</tr>
</c:if>