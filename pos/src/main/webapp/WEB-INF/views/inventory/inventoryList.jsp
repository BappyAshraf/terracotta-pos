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
			<td>${list.inventoryDate}</td>
			<td>${list.productName}</td>
			<td>${list.unitName}</td>
			<td>${list.unitPrice}</td>
			<td>${list.quantity}</td>
			<td>${list.price}</td>
			<td>${list.employeeName}</td>
			<td><a href="#"
				onclick="getProductInfo('${list.encInventoryId}')"><button
						class="btn btn-xs btn-primary" type="button">
						<i class="fa fa-pencil"></i>
					</button></a></td>
		</tr>
	</c:forEach>

</c:if>

<c:if test="${! empty inventoryListNotFound}">
	<tr>
		<td colspan="9"><p>${inventoryListNotFound}</p></td>
		<td style="display: none;"></td>
		<td style="display: none;"></td>
		<td style="display: none;"></td>
		<td style="display: none;"></td>
		<td style="display: none;"></td>
		<td style="display: none;"></td>
		<td style="display: none;"></td>
		<td style="display: none;"></td>
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
			<td>${list.inventoryDate}</td>
			<td>${list.productName}</td>
			<td>${list.unitName}</td>
			<td>${list.unitPrice}</td>
			<td>${list.quantity}</td>
			<td>${list.price}</td>
			<td>${list.advanceAmount}</td>
			<td>${list.supplierName}</td>
			<td><a href="#"
				onclick="getProductInfoSupplier('${list.encInventoryId}')"><button
						class="btn btn-xs btn-primary" type="button">
						<i class="fa fa-pencil"></i>
					</button></a></td>
		</tr>
	</c:forEach>

</c:if>

<c:if test="${! empty supplierInventoryListNotFound}">
	<tr>
		<td colspan="10"><p>${supplierInventoryListNotFound}</p></td>
		<td style="display: none;"></td>
		<td style="display: none;"></td>
		<td style="display: none;"></td>
		<td style="display: none;"></td>
		<td style="display: none;"></td>
		<td style="display: none;"></td>
		<td style="display: none;"></td>
		<td style="display: none;"></td>
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


<c:if test="${!empty storeInventoryList}">
	<%
		int i = 1;
	%>
	<c:forEach items="${storeInventoryList}" var="list">

		<tr style="cursor: pointer">
			<td>
				<%
					out.print(i);
							i++;
				%>
			</td>
			<td>${list.storeDate}</td>
			<td>${list.inventoryTypeName}</td>
			<td>${list.productName}</td>
			<td>${list.unitName}</td>
			<td>${list.quantity}</td>
			<%-- <td>${list.updateBy}</td> --%>
			<td><a href="#"
				onclick="getStoredIngredientsInfo('${list.encStoreId}')"><button
						class="btn btn-xs btn-primary" type="button">
						<i class="fa fa-pencil"></i>
					</button></a></td>
		</tr>
	</c:forEach>

</c:if>

<c:if test="${! empty storeInventoryListNotFound}">
	<tr>
		<td colspan="9"><p>${storeInventoryListNotFound}</p></td>
		<td style="display: none;"></td>
		<td style="display: none;"></td>
		<td style="display: none;"></td>
		<td style="display: none;"></td>
		<td style="display: none;"></td>
		<td style="display: none;"></td>
		<td style="display: none;"></td>
		<td style="display: none;"></td>
	</tr>
</c:if>


<c:if test="${!empty storeHistoryList}">
	<%
		int i = 1;
	%>
	<c:forEach items="${storeHistoryList}" var="list">

		<tr style="cursor: pointer">
			<td>
				<%
					out.print(i);
							i++;
				%>
			</td>
			<td>${list.storeDate}</td>
			<%-- <td>${list.inventoryTypeName}</td> --%>
			<td>${list.productName}</td>
			<td>${list.unitName}</td>
			<td>${list.quantity}</td>
		</tr>
	</c:forEach>

</c:if>

<c:if test="${! empty storeHistoryListNotFound}">
	<tr>
		<td colspan="9"><p>${storeHistoryListNotFound}</p></td>
		<td style="display: none;"></td>
		<td style="display: none;"></td>
		<td style="display: none;"></td>
		<td style="display: none;"></td>
		<td style="display: none;"></td>
		<td style="display: none;"></td>
		<td style="display: none;"></td>
		<td style="display: none;"></td>
	</tr>
</c:if>