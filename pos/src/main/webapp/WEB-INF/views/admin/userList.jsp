<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${!empty userList}">
						<c:forEach items="${userList}" var="list">
							<tr style="cursor: pointer">
								<td>${list.employeeName}</td>
								<td >${list.fatherName}</td>
								<td class="">${list.knownAs}</td>
								<td class="">${list.designationName}</td>
								<td>
								
								<a href="#" onclick="getUserInfo('${list.encEmployeeId}')">Select</a>	</td>
							<tr>
						</c:forEach>

	</c:if>
	
	<c:if test="${! empty userNotFound}">
						<tr>
							<td colspan="5"><p>${userNotFound}</p></td>
						</tr>
	</c:if>