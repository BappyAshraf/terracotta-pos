<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${!empty orderInfoList}">
	<%
		int i = 1;
	%>
	<c:forEach items="${orderInfoList}" var="list">

		<tr style="cursor: pointer">
			<td>
				<%
					out.print(i);
							i++;
				%>
			</td>
			<td>${list.itemName}</td>
			<td>${list.quantity}</td>
			<td>${list.itemPrice}</td>

		</tr>
	</c:forEach>

</c:if>
<c:if test="${! empty orderInfoListNotFound}">
	<tr>
		<td colspan="7"><p>${orderInfoListNotFound}</p></td>
	</tr>
</c:if>

<c:if test="${!empty pendingOrderInfoList}">
	<%
		int i = 1;
	%>
	<c:forEach items="${pendingOrderInfoList}" var="list">

		<tr style="cursor: pointer">
			<td>
				<%
					out.print(i);
							i++;
				%>
			</td>
			<td>${list.itemName}</td>
			<td>${list.quantity}</td>
			<td>${list.itemPrice}</td>
			<td>${list.subTotal}</td>
			<td>${list.orderNote}</td>
		</tr>
	</c:forEach>

</c:if>



<c:if test="${! empty sum_data}">
	<tr>
		<td></td>
		<td></td>
		<td></td>
		<td><p>Net Total</p></td>
		<td><p>${sum_data}</p></td>
		<td></td>
	</tr>
</c:if>

<c:if test="${! empty pendingOrderInfoListNotFound}">
	<tr>
		<td colspan="7"><p>${pendingOrderInfoListNotFound}</p></td>
	</tr>
</c:if>































