<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${!empty venueReseredList}">
	<%
		int i = 1;
	%>

	<c:forEach items="${venueReseredList}" var="list">
		<tr>
			<td style="text-align:center;">
				<%
					out.print(i);
				%>
			</td>

			<td style="text-align:center;"> ${list.venueName}</td>
			<td style="text-align:center;"> ${list.programmeTypeName}</td>
			<td style="text-align:center;"> ${list.reservationDate}</td>
			<td style="text-align:center;"> ${list.startTime}</td>
			<td style="text-align:center;"> ${list.endTime}</td>
			<td style="text-align:center;"> ${list.statusYN}</td>
			<td style="text-align:center;"> ${list.completedYN}</td>
			<td style="text-align:center;"> 
			<c:if test="${list.completedYN == 'NO'}"> 
			<button class="btn btn-primary" onclick="getReservedVenue('${list.encReservationId}')"   type="button"> Edit </button>
			</c:if>  </td>
			

		</tr>
		<%
			i = i + 1;
		%>

	</c:forEach>

</c:if>

<c:if test="${! empty venueReseredListNotFound}">
	<tr>
		<td colspan="9"><p>${venueReseredListNotFound}</p></td>
	</tr>
</c:if>
