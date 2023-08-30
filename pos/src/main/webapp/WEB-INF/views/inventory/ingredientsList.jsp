<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${!empty ingredientsList}">
	<c:forEach items="${ingredientsList}" var="list">
		<tr style="cursor: pointer"
			onclick="selectProductList('${list.encProductId}','${list.productName}','${list.unitId}')">
			<td style="border-style: inset;">${list.productCode}</td>
			<td style="border-style: inset;">${list.productName}</td>
			<td style="border-style: inset;">${list.unitName}</td>
		</tr>
	</c:forEach>
</c:if>
<c:if test="${! empty ingredientsListNotFound}">
	<tr>
		<td colspan="3"><p>${ingredientsListNotFound}</p></td>
	</tr>
</c:if>
