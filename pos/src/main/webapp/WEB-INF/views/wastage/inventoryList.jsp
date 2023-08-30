<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${!empty inventoryList}">
	<%
		int i = 1;
	%>
	<c:forEach items="${inventoryList}" var="list">

		<tr style="cursor: pointer">
			<td>
				<%
					out.print(i);
							i++;
				%>
			</td>
			<td>${list.productName}</td>
			<td>${list.quantity}</td>
			<td>${list.unitName}</td>
			<td>${list.price}</td>
			<td>${list.employeeName}</td>
			<td>${list.inventoryDate}</td>

		</tr>
	</c:forEach>

</c:if>

<c:if test="${! empty inventoryListNotFound}">
	<tr>
		<td colspan="10"><p>${inventoryListNotFound}</p></td>
	</tr>
</c:if>



<c:if test="${!empty supplierInventoryList}">
	<%
		int i = 1;
	%>
	<c:forEach items="${supplierInventoryList}" var="list">

		<tr style="cursor: pointer">
			<td>
				<%
					out.print(i);
							i++;
				%>
			</td>
			<td>${list.productName}</td>
			<td>${list.quantity}</td>
			<td>${list.unitName}</td>
			<td>${list.price}</td>
			<td>${list.advanceAmount}</td>
			<td>${list.supplierName}</td>
			<td>${list.inventoryDate}</td>

		</tr>
	</c:forEach>

</c:if>

<c:if test="${! empty supplierInventoryListNotFound}">
	<tr>
		<td colspan="10"><p>${supplierInventoryListNotFound}</p></td>
	</tr>
</c:if>



<c:if test="${!empty productViewList}">
	<%
		int i = 1;
	%>
	<c:forEach items="${productViewList}" var="list">

		<tr style="cursor: pointer">
			<td>
				<%
					out.print(i);
							i++;
				%>
			</td>
			<td>${list.inventoryTypeName}</td>
			<td>${list.productId}</td>
			<td>${list.employeeId}</td>
			<td>${list.inventoryTypeId}</td>

		</tr>
	</c:forEach>

</c:if>

<c:if test="${! empty productViewListNotFound}">
	<tr>
		<td colspan="10"><p>${productViewListNotFound}</p></td>
	</tr>
</c:if>
