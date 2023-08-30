<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${!empty itemConfigList}">
	<%
		int i = 1;
	%>
	<c:forEach items="${itemConfigList}" var="list">

		<tr style="cursor: pointer">
			<td>
				<%
					out.print(i);
							i++;
				%>


			</td>

			<td>
			
			<input type="hidden" id="encItemId_${list.itemId}_${list.productId}"
				value="${list.encItemId}" /> 
				
				<select style="display: none;"
				data-plugin-selectTwo class="form-control"
				id="productId_${list.itemId}_${list.productId}" required>
					<option>Select One</option>
					<c:forEach items="${productList}" var="listProduct">
						<option
							<c:if test="${listProduct.productId == list.productId}">selected = "selected" </c:if>
							value="${listProduct.productId}">${listProduct.productName}</option>
					</c:forEach>
				</select>

				<p id="dataProductId_${list.itemId}_${list.productId}">
					${list.productName}</p>
					
			</td>

			<td>
			
			<input style="display: none;" value="${list.quantity}"
				class="form-control" id="quantity_${list.itemId}_${list.productId}"
				onkeydown="return isNumberKey(event)" />
				
				<p id="dataQuantity_${list.itemId}_${list.productId}">${list.quantity}</p>
				
			</td>

			<td>
				
				<select style="display: none;"
				data-plugin-selectTwo class="form-control"
				id="unitId_${list.itemId}_${list.productId}" required>
					<option>Select One</option>
					<c:forEach items="${unitList}" var="listUnit">
						<option
							<c:if test="${listUnit.unitId == list.unitId}">selected = "selected" </c:if>
							value="${listUnit.unitId}">${listUnit.unitName}</option>
					</c:forEach>
				</select>
			
				<p id="dataUnitId_${list.itemId}_${list.productId}">
					${list.unitName}</p>
					
			</td>

			<td>
			<a
				onclick="editOldData('${list.itemId}','${list.productId}')"
				id="edit_${list.itemId}_${list.productId}">
					<button class="btn btn-primary" type="button" style="width: 75px;">
						<span> <i class="fa fa-pencil"></i> Edit
						</span>
					</button>

			</a> 
			
			<a id="update_${list.itemId}_${list.productId}"
				style="display: none;" onclick="saveUpdateData('${list.itemId}','${list.productId}')">
					<button class="btn btn-primary" type="button" style="width: 75px;">
						<span> <i class="fa fa-save"></i> Update
						</span>
					</button>

			</a>
			
			 <a id="clear_${list.itemId}_${list.productId}" style="display: none;"
				onclick="cancelEditOldData('${list.itemId}','${list.productId}')">
					<button class="btn btn-default" type="button" style="width: 75px;">
						<span> <i class="fa fa-refresh"></i> Cancel
						</span>
					</button>
			</a>
			
			</td>
			
		</tr>
	</c:forEach>

</c:if>

<c:if test="${! empty itemConfigListNotFound}">
	<tr>
		<td colspan="10"><p>${itemConfigListNotFound}</p></td>
	</tr>
</c:if>



<c:if test="${!empty itemList}">
	<c:forEach items="${itemList}" var="list">
		<tr style="cursor: pointer"
			onclick="selectItemList('${list.encItemId}','${list.itemName}','${list.itemPrice}')">
			<td style="border-style: inset;">${list.itemCode}</td>
			<td style="border-style: inset;">${list.itemName}</td>
			<td style="border-style: inset;">${list.itemPrice}</td>
		</tr>
	</c:forEach>
</c:if>
<c:if test="${! empty itemListNotFound}">
	<tr>
		<td colspan="3"><p>${itemListNotFound}</p></td>
	</tr>
</c:if>