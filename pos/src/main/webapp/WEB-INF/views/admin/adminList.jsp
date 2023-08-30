<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${!empty roleList}">
	<%
		int i = 1;
	%>
	<c:forEach items="${roleList}" var="list">
		<tr ondblclick="getRole('${list.encRoleId}')" style="cursor: pointer">
			<td>
				<%
					out.print(i);
				%>
			</td>
			<td>${list.roleName}</td>
			<td>${list.enabledYNName}</td>
			<td><a href="#" onclick="getRole('${list.encRoleId}')"><button
						class="btn btn-xs btn-primary" type="button">
						<i class="fa fa-pencil"></i>
					</button></a> <a class="btn btn-xs btn-danger modal-with-zoom-anim"
				href="#modalDeleteRoleConf" onclick="delRole('${list.encRoleId}')">
					<i class="fa fa-trash-o"></i>
			</a></td>

		</tr>
		<%
			i = i + 1;
		%>
	</c:forEach>

</c:if>


<c:if test="${! empty noRoleFound}">
	<tr>
		<td colspan="3"><p>${noRoleFound}</p></td>
	</tr>
</c:if>

<c:if test="${!empty employeeInfoList}">
	<%
		int i = 1;
	%>
	<c:forEach items="${employeeInfoList}" var="list">
		<tr ondblclick="getEmployeeInfo('${list.encEmployeeId}','${list.employeeName}','${list.designationName}')"
			style="cursor: pointer">
			<td>
				<%
					out.print(i);
							i++;
				%>
			</td>
			<td>${list.employeeName}</td>
			<td>${list.knownAs}</td>
			<td>${list.designationName}</td>
			<td>${list.contactNo}</td>
			<td>${list.enabledYNName}</td>
			<td><a href="#"
				onclick="getEmployeeInfo('${list.encEmployeeId}','${list.employeeName}','${list.designationName}')"><button
						class="btn btn-xs btn-primary" type="button">
						<i class="fa fa-pencil"></i>
					</button></a>
		</tr>
	</c:forEach>

</c:if>

<c:if test="${! empty employeeInfoNotFound}">
	<tr>
		<td colspan="9"><p>${employeeInfoNotFound}</p></td>
	</tr>
</c:if>



<c:if test="${!empty employeeInfoListForSpecial}">
	<c:forEach items="${employeeInfoListForSpecial}" var="list">
		<tr
			ondblclick="getEmployeeInfoForSpecial('${list.encEmployeeId}','${list.employeeName}','${list.fatherName}','${list.designationName}')"
			style="cursor: pointer">
			<td>${list.employeeName}</td>
			<td>${list.fatherName}</td>
			<td>${list.departmentName}</td>
			<td>${list.designationName}</td>
			<td>${list.enabledYNName}</td>
			<td>${list.levelNo}</td>
			<td><a href="#"
				onclick="getEmployeeInfoForSpecial('${list.encEmployeeId}','${list.employeeName}','${list.fatherName}','${list.designationName}')"><button
						class="btn btn-xs btn-primary" type="button">
						<i class="fa fa-pencil"></i>
					</button></a>
		</tr>
	</c:forEach>

</c:if>

<c:if test="${! empty employeeInfoForSpecialNotFound}">
	<tr>
		<td colspan="8"><p>${employeeInfoForSpecialNotFound}</p></td>
	</tr>
</c:if>


<c:if test="${!empty userList}">
	<c:forEach items="${userList}" var="list">
		<tr ondblclick="getUserInfo('${list.encUserId}')"
			style="cursor: pointer">
			<td>${list.employeeName}</td>
			<td>${list.designationName}</td>
			<td>${list.enabledYNName}</td>
			<td><a href="#" onclick="getUserInfo('${list.encUserId}')"><button
						class="btn btn-xs btn-primary" type="button">
						<i class="fa fa-pencil"></i>
					</button></a>
		</tr>
	</c:forEach>

</c:if>

<c:if test="${! empty userNotFound}">
	<tr>
		<td colspan="7"><p>${userNotFound}</p></td>
	</tr>
</c:if>




<%-- <c:if test="${!empty projectList}">
	<c:forEach items="${projectList}" var="list">
		<tr ondblclick="getProjectInfo('${list.encProjectId}')"
			style="cursor: pointer">
			<td>${list.projectName}</td>
			<td>${list.startDate}</td>
			<td>${list.endDate}</td>
			<td>${list.enabledYNName}</td>
			<td><a href="#" onclick="getProjectInfo('${list.encProjectId}')"><button
						class="btn btn-xs btn-primary" type="button">
						<i class="fa fa-pencil"></i>
					</button></a> <a href="#" onclick="deleteProjectInfo('${list.encProjectId}')"><button class="btn btn-xs btn-danger" type="button"><i class="fa fa-trash-o"></i></button></a>	</td>
		</tr>
	</c:forEach>

</c:if>

<c:if test="${! empty projectNotFound}">
	<tr>
		<td colspan="5"><p>${projectNotFound}</p></td>
	</tr>
</c:if> --%>




<%-- <c:if test="${!empty costPurposeInfoList}">
	<c:forEach items="${costPurposeInfoList}" var="list">
		<tr ondblclick="" style="cursor: pointer">
			<td>${list.projectName}</td>
			<td>${list.purposeName}</td>
			<td><a href="#" onclick="getPurposeInfo('${list.encPurposeId}')"><button
						class="btn btn-xs btn-primary" type="button">
						<i class="fa fa-pencil"></i>
					</button></a> <a href="#" onclick="deletePurposeInfo('${list.encPurposeId}')"><button
						class="btn btn-xs btn-danger" type="button">
						<i class="fa fa-trash-o"></i>
					</button></a></td>
		</tr>
	</c:forEach>

</c:if>

<c:if test="${! empty costPurposeInfoNotFound}">
	<tr>
		<td colspan="5"><p>${costPurposeInfoNotFound}</p></td>
	</tr>
</c:if> --%>


<%-- <c:if test="${!empty bankAccInfoList}">
	<c:forEach items="${bankAccInfoList}" var="list">
		<tr ondblclick="getBankAccInfo('${list.encBankAccId}')"
			style="cursor: pointer">
			<td>${list.bankName}</td>
			<td>${list.branchName}</td>
			<td>${list.accName}</td>
			<td>${list.accNo}</td>
			<td>${list.enabledYNName}</td>
			<td><a href="#" onclick="getBankAccInfo('${list.encBankAccId}')"><button
						class="btn btn-xs btn-primary" type="button">
						<i class="fa fa-pencil"></i>
					</button></a>
		</tr>
	</c:forEach>

</c:if>

<c:if test="${! empty bankAccInfoNotFound}">
	<tr>
		<td colspan="6"><p>${bankAccInfoNotFound}</p></td>
	</tr>
</c:if> --%>



<%-- <c:if test="${!empty bankMasterList}">
	<c:forEach items="${bankMasterList}" var="list">
		<tr ondblclick="getBankInfo('${list.encBankId}')"
			style="cursor: pointer">
			<td>${list.bankName}</td>
			<td>${list.hqAddress}</td>
			<td>${list.enabledYNName}</td>
			<td><a href="#" onclick="getBankInfo('${list.encBankId}')"><button
						class="btn btn-xs btn-primary" type="button">
						<i class="fa fa-pencil"></i>
					</button></a>
		</tr>
	</c:forEach>

</c:if>

<c:if test="${! empty bankMasterNotFound}">
	<tr>
		<td colspan="5"><p>${bankMasterNotFound}</p></td>
	</tr>
</c:if>
 --%>


<%-- <c:if test="${!empty bankBranchList}">
	<c:forEach items="${bankBranchList}" var="list">
		<tr ondblclick="getBankBranch('${list.encBankBranchId}')"
			style="cursor: pointer">
			<td>${list.bankName}</td>
			<td>${list.branchName}</td>
			<td>${list.branchAddress}</td>
			<td>${list.districtName}</td>
			<td>${list.divisionName}</td>
			<td>${list.bankAdviceTo}</td>
			<td>${list.bankAdviceAddress}</td>
			<td>${list.enabledYNName}</td>
			<td><a href="#"
				onclick="getBankBranch('${list.encBankBranchId}')"><button
						class="btn btn-xs btn-primary" type="button">
						<i class="fa fa-pencil"></i>
					</button></a>
		</tr>
	</c:forEach>

</c:if>

<c:if test="${! empty bankBranchNotFound}">
	<tr>
		<td colspan="7"><p>${bankBranchNotFound}</p></td>
	</tr>
</c:if> --%>




<%-- <c:if test="${!empty cashRecipientList}">
	<c:forEach items="${cashRecipientList}" var="list">
		<tr ondblclick="getCashRecipient('${list.encRecipientId}')"
			style="cursor: pointer">
			<td>${list.employeeName}</td>
			<td>${list.externalCashReceiveYN}</td>
			<td>${list.enabledYNName}</td>
			<td><a href="#"
				onclick="getCashRecipient('${list.encRecipientId}')"><button
						class="btn btn-xs btn-primary" type="button">
						<i class="fa fa-pencil"></i>
					</button></a>
		</tr>
	</c:forEach>

</c:if>

<c:if test="${! empty cashRecipientNotFound}">
	<tr>
		<td colspan="4"><p>${cashRecipientNotFound}</p></td>
	</tr>
</c:if> --%>




<%-- <c:if test="${!empty cashChequeRecipientList}">
	<c:forEach items="${cashChequeRecipientList}" var="list">
		<tr
			ondblclick="getCashChequeRecipient('${list.encCashChequeRecipientId}', '${list.recipientName}')"
			style="cursor: pointer">
			<td>${list.recipientName}</td>
			<td>${list.recipientAddress}</td>
			<td>${list.recipientTypeName}</td>
			<td>${list.enabledYNName}</td>
			<td><a href="#"
				onclick="getCashChequeRecipient('${list.encCashChequeRecipientId}', '${list.recipientName}')"><button
						class="btn btn-xs btn-primary" type="button">
						<i class="fa fa-pencil"></i>
					</button></a>
		</tr>
	</c:forEach>

</c:if>

<c:if test="${! empty cashChequeRecipientNotFound}">
	<tr>
		<td colspan="5"><p>${cashChequeRecipientNotFound}</p></td>
	</tr>
</c:if> --%>





<%-- <c:if test="${!empty otherRecipientList}">
	<c:forEach items="${otherRecipientList}" var="list">
		<tr ondblclick="getOtherRecipient('${list.encOtherRecipientId}')"
			style="cursor: pointer">
			<td>${list.recipientName}</td>
			<td>${list.recipientAddress}</td>
			<td>${list.projectName}</td>
			<td>${list.enabledYNName}</td>
			<td><a href="#"
				onclick="getOtherRecipient('${list.encOtherRecipientId}')"><button
						class="btn btn-xs btn-primary" type="button">
						<i class="fa fa-pencil"></i>
					</button></a>
		</tr>
	</c:forEach>

</c:if>

<c:if test="${! empty otherRecipientNotFound}">
	<tr>
		<td colspan="5"><p>${otherRecipientNotFound}</p></td>
	</tr>
</c:if> --%>




<%-- <c:if test="${!empty chequeCancelList}">
	<c:forEach items="${chequeCancelList}" var="list">
		<tr style="cursor: pointer">
			<td>${list.bankName}</td>
			<td>${list.bankAccountNo}</td>
			<td>${list.chequeNo}</td>
			<td>${list.cancelDate}</td>
		</tr>
	</c:forEach>

</c:if>

<c:if test="${! empty chequeCancelNotFound}">
	<tr>
		<td colspan="4"><p>${chequeCancelNotFound}</p></td>
	</tr>
</c:if> --%>




<%-- <c:if test="${!empty chequeBookInfoList}">
	<c:forEach items="${chequeBookInfoList}" var="list">
		<tr ondblclick="getChequeBookInfo('${list.encChequeBookId}')"
			style="cursor: pointer">
			<td>${list.bankName}</td>
			<td>${list.bankAccountNo}</td>
			<td>${list.bankAccountName}</td>
			<td>${list.startingLeaf}</td>
			<td>${list.endingLeaf}</td>
			<td>${list.currentYNName}</td>
			<td><a href="#"
				onclick="getChequeBookInfo('${list.encChequeBookId}')"><button
						class="btn btn-xs btn-primary" type="button">
						<i class="fa fa-pencil"></i>
					</button></a>
		</tr>
	</c:forEach>

</c:if>

<c:if test="${! empty chequeBookInfoNotFound}">
	<tr>
		<td colspan="7"><p>${chequeBookInfoNotFound}</p></td>
	</tr>
</c:if> --%>

<%-- <c:if test="${!empty cashBookRegisterList}">
	<c:forEach items="${cashBookRegisterList}" var="list">
		<tr
			<c:if test="${chequeEditPermission == 'Y' && list.paymentAmount != '0.00'}">ondblclick="getCashBookRegister('${list.encRegisterId}')"</c:if>
			style="cursor: pointer">
			<td>${list.issueDate}</td>
			<td>${list.projectName}</td>
			<td>${list.fullRecipientName}</td>
			<td>${list.purposeName} ${list.remarks}</td>
			<td>${list.remarks}</td>
			<td style="text-align: right">${list.receiptAmount}</td>
			<td style="text-align: right">${list.paymentAmount}</td>
			<c:if
				test="${chequeEditPermission == 'Y' && list.paymentAmount != '0.00'}">
				<td><a href="#"
					onclick="getCashBookRegister('${list.encRegisterId}')"><button
							class="btn btn-xs btn-primary" type="button">
							<i class="fa fa-pencil"></i>
						</button></a></td>
			</c:if>
			<c:if
				test="${cashEditPermission == 'Y' && list.paymentAmount == '0.00'}">
				<td></td>
			</c:if>

		</tr>
	</c:forEach>

</c:if> --%>


<%-- <c:if test="${!empty cashBookRegisterTotal}">

	<c:forEach items="${cashBookRegisterTotal}" var="total">
		<tr style="cursor: pointer">
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td style="color: blue; text-align: right"><strong>Total</strong></td>
			<td style="color: red; text-align: right">${total.receiptAmount}</td>
			<td style="color: red; text-align: right">${total.paymentAmount}</td>
			<c:if test="${cashEditPermission == 'Y'}">
				<td></td>
			</c:if>

		</tr>
	</c:forEach>


</c:if>


<c:if test="${! empty cashBookRegisterNotFound}">
	<tr>
		<td
			<c:choose><c:when test="${cashEditPermission == 'Y'}">colspan="7"</c:when><c:otherwise>colspan="6"</c:otherwise></c:choose>><p>${cashBookRegisterNotFound}</p></td>
	</tr>
</c:if> --%>





<%-- <c:if test="${!empty pettyCashRegisterList}">
	<c:forEach items="${pettyCashRegisterList}" var="list">
		<tr
			<c:if test="${cashEditPermission == 'Y' && list.refRegisterId == ''}">ondblclick="getPettyCashRegister('${list.encRegisterId}')"</c:if>
			style="cursor: pointer">
			<td>${list.issueDate}</td>
			<td>${list.projectName}</td>
			<td>${list.fullRecipientName}</td>
			<td>${list.purposeName}</td>
			<td style="text-align: right">${list.receiptAmount}</td>
			<td style="text-align: right">${list.paymentAmount}</td>
			<c:if
				test="${cashEditPermission == 'Y' && list.refRegisterId == '' }">
				<td><a href="#"
					onclick="getPettyCashRegister('${list.encRegisterId}')"><button
							class="btn btn-xs btn-primary" type="button">
							<i class="fa fa-pencil"></i>
						</button></a></td>
			</c:if>
			<c:if test="${cashEditPermission == 'Y' && list.refRegisterId != ''}">
				<td></td>
			</c:if>

		</tr>
	</c:forEach>

</c:if> --%>


<%-- <c:if test="${!empty pettyCashRegisterTotal}">
	<c:forEach items="${pettyCashRegisterTotal}" var="total">
		<tr style="cursor: pointer">
			<td></td>
			<td></td>
			<td></td>
			<td style="color: blue; text-align: right"><strong>Total</strong></td>
			<td style="color: red; text-align: right">${total.receiptAmount}</td>
			<td style="color: red; text-align: right">${total.paymentAmount}</td>
			<c:if test="${cashEditPermission == 'Y'}">
				<td></td>
			</c:if>

		</tr>
	</c:forEach>

</c:if>


<c:if test="${! empty pettyCashRegisterNotFound}">
	<tr>
		<td
			<c:choose><c:when test="${cashEditPermission == 'Y'}">colspan="7"</c:when><c:otherwise>colspan="6"</c:otherwise></c:choose>><p>${pettyCashRegisterNotFound}</p></td>
	</tr>
</c:if> --%>





<%-- <c:if test="${!empty chequeBookRegisterList}">
	<%
		int i = 1;
	%>
	<c:forEach items="${chequeBookRegisterList}" var="list">

		<tr
			<c:if test="${chequeEditPermission == 'Y'}">ondblclick="getChequeBookregister('${list.encRegisterId}')"</c:if>
			style="cursor: pointer">
			<td>
				<%
					out.print(i);
				%>
			</td>
			<td>${list.bankName}</td>
			<td>${list.bankAccountName}</td>
			<td>${list.projectName}</td>
			<td>${list.purposeName}</td>
			<td>${list.issuedBy}</td>
			<td>${list.chequeRecipientName}</td>
			<td>${list.chequeNo}</td>
			<td>${list.givenDate}</td>
			<td>${list.chequeDate}</td>
			<td style="text-align: right">${list.chequeAmount}</td>
			<c:if test="${chequeEditPermission == 'Y'}">
				<td><a href="#"
					onclick="getChequeBookregister('${list.encRegisterId}')"><button
							class="btn btn-xs btn-primary" type="button">
							<i class="fa fa-pencil"></i>
						</button></a></td>
			</c:if>

		</tr>
		<%
			i = i + 1;
		%>
	</c:forEach>

</c:if>


<c:if test="${!empty chequeBookRegisterTotal}">
	<c:forEach items="${chequeBookRegisterTotal}" var="total">
		<tr style="cursor: pointer">
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td style="color: blue; text-align: right">Total</td>
			<td style="color: red; text-align: right">${total.chequeAmount}</td>
			<c:if test="${chequeEditPermission == 'Y'}">
				<td></td>
			</c:if>

		</tr>
	</c:forEach>

</c:if>


<c:if test="${! empty chequeBookRegisterNotFound}">
	<tr>
		<td
			<c:choose><c:when test="${chequeEditPermission == 'Y'}">colspan="12"</c:when><c:otherwise>colspan="11"</c:otherwise></c:choose>><p>${chequeBookRegisterNotFound}</p></td>
	</tr>
</c:if>
 --%>






<%-- <c:if test="${!empty bankTransferList}">
	<c:forEach items="${bankTransferList}" var="list">
		<tr style="cursor: pointer">
			<td>${list.transferDate}</td>
			<td>${list.registerDate}</td>
			<td>${list.bankFromName}</td>
			<td>${list.bankAccFrom}</td>
			<td>${list.bankToName}</td>
			<td>${list.bankAccTo}</td>
			<td style="text-align: right">${list.transferAmount}</td>
			<td>${list.particulars}</td>

		</tr>
	</c:forEach>

</c:if>


<c:if test="${! empty bankTransferNotFound}">
	<tr>
		<td colspan="8"><p>${bankTransferNotFound}</p></td>
	</tr>
</c:if> --%>






<%-- <c:if test="${!empty vehicleRequisitionList}">
	<%
		int sl = 0;
	%>

	<c:forEach items="${vehicleRequisitionList}" var="list">
		<%
			sl = sl + 1;
		%>
		<tr style="cursor: pointer">
			<td>
				<%
					out.print(sl);
				%>
			</td>
			<td>${list.requisitionByName}</td>
			<td>${list.requisitionPurpose}</td>
			<td>${list.requisitionDate}</td>
			<td>${list.requisitionTimeFrom}</td>
			<td>${list.requisitionTimeTo}</td>
			<td>${list.passengerNo}</td>

			<td><select class="form-control"
				id="vehicleId_<%out.print(sl);%>">
					<option value="">Please Select One</option>
					<c:forEach items="${registrationNoList}" var="vehiclelist">
						<option value="${vehiclelist.id}">${vehiclelist.name}</option>
					</c:forEach>
			</select></td>
			<td><select class="form-control"
				id="driverId_<%out.print(sl);%>">
					<option value="">Please Select One</option>
					<c:forEach items="${driverList}" var="driverlist">
						<option value="${driverlist.id}">${driverlist.name}</option>
					</c:forEach>
			</select></td>



			<td><a href="#"
				onclick="assignRequisition('${list.encRequisitionId}',<%out.print(sl);%>)"><button
						class="btn btn-xs btn-primary" type="button">Assign</button></a></td>
			<td><a href="#"
				onclick="rejectRequisition('${list.encRequisitionId}')"><button
						class="btn btn-xs btn-primary" type="button">Reject</button></a></td>

		</tr>
	</c:forEach>

</c:if>



<c:if test="${! empty vehicleRequisitionListNotFound}">
	<tr>
		<td colspan="10"><p>${vehicleRequisitionListNotFound}</p></td>
	</tr>
</c:if>
 --%>


<%-- <c:if test="${!empty cashAdvanceList}">
	<c:forEach items="${cashAdvanceList}" var="list">
		<tr style="cursor: pointer">
			<td>${list.registerDate}</td>
			<td>${list.advanceGivenByName}</td>
			<td>${list.cashChequeRecipientName}</td>
			<td>${list.givenDate}</td>
			<td style="text-align: right">${list.advanceAmount}</td>
			<td style="text-align: right">${list.disbursementAmount}</td>
			<td style="text-align: right">${list.refundAmount}</td>
			<td style="text-align: right">${list.balance}</td>
			<td>${list.remarks}</td>

		</tr>
	</c:forEach>

</c:if>



<c:if test="${! empty cashAdvanceNotFound}">
	<tr>
		<td colspan="9"><p>${cashAdvanceNotFound}</p></td>
	</tr>
</c:if> --%>


<%-- <c:if test="${!empty cashAdvanceForRefundList}">

	<%
		int i = 0;
	%>
	<c:forEach items="${cashAdvanceForRefundList}" var="list">
		<%
			i = i + 1;
		%>
		<tr style="cursor: pointer">
			<td>${list.registerDate}</td>
			<td>${list.advanceGivenByName}</td>
			<td>${list.cashChequeRecipientName}</td>
			<td>${list.givenDate}</td>
			<td style="text-align: right">${list.advanceAmount}</td>
			<td><input style="text-align: right" type="text"
				class="form-control" id="disburs_<%out.print(i);%>"
				onkeypress="return isNumber(event)" /></td>
			<td><input style="text-align: right" type="text"
				class="form-control" id="refund_<%out.print(i);%>"
				onkeypress="return isNumber(event)" /></td>
			<td><div class="input-group">
					<span class="input-group-addon"> <i class="fa fa-calendar"></i>
					</span><input type="text" data-plugin-datepicker class="form-control"
						id="refundDate_<%out.print(i);%>"
						onclick="showRefundDatepicker(<%out.print(i);%>)" />
				</div></td>
			<td><a href="#"
				onclick="cashRefund('${list.encRegisterId}',<%out.print(i);%>)"><button
						class="btn btn-xs btn-primary" type="button">Refund</button></a></td>

		</tr>
	</c:forEach>

</c:if>


<c:if test="${! empty cashAdvanceForRefundNotFound}">
	<tr>
		<td colspan="9"><p>${cashAdvanceForRefundNotFound}</p></td>
	</tr>
</c:if>
 --%>


<%-- <c:if test="${!empty VehicleInformationList}">
	<c:forEach items="${VehicleInformationList}" var="list">
		<tr ondblclick="getVehicleInfornation('${list.encVehicleId}')"
			style="cursor: pointer">
			<td>${list.registrationNumber}</td>
			<td>${list.vehicleAllies}</td>
			<td>${list.chassisNo}</td>
			<td>${list.engineNo}</td>
			<td>${list.colorName}</td>
			<td>${list.enableYnName}</td>
			<td><a href="#"
				onclick="getVehicleInfornation('${list.encVehicleId}')"><button
						class="btn btn-xs btn-primary" type="button">
						<i class="fa fa-pencil"></i>
					</button></a></td>

		</tr>
	</c:forEach>

</c:if>


<c:if test="${! empty vehicleInformationListNotFound}">
	<tr>
		<td colspan="7"><p>${vehicleInformationListNotFound}</p></td>
	</tr>
</c:if>
 --%>

<%-- <c:if test="${!empty purposeList}">


	<%
		int i = 0;
	%>
	<c:forEach items="${purposeList}" var="list">
		<%
			i = i + 1;
		%>
		<tr style="cursor: pointer">
			<td>${list.glHead}</td>
			<td><div class="checkbox-custom chekbox-primary input-group">
					<input value="Y" type="checkbox" id="enabledYN_<%out.print(i);%>"
						name="enabledYN"
						<c:if test="${list.enabledYN == 'Y'}">checked</c:if> /><label
						for="enabledYN_<%out.print(i);%>"></label>
				</div></td>
			<td><a href="#"
				onclick="save('${list.encGlHeadId}',<%out.print(i);%>)"><button
						class="btn btn-xs btn-primary" type="button">Save</button></a></td>

		</tr>
	</c:forEach>
</c:if>



<c:if test="${! empty purposeNotFound}">
	<tr>
		<td colspan="3"><p>${purposeNotFound}</p></td>
	</tr>
</c:if>
 --%>
 
 <%-- 
<c:if test="${!empty FuelRefillingList}">
	<c:forEach items="${FuelRefillingList}" var="list">
		<tr ondblclick="getFuelRefillingInfo('${list.encFuelRefillingId}')"
			style="cursor: pointer">
			<td>${list.registrationNumber}</td>
			<td>${list.refillingDate}</td>
			<td>${list.fuelTypeName}</td>
			<td>${list.fuelQuantity}</td>
			<td>${list.measurmentUnitName}</td>
			<td>${list.meterReading}</td>
			<td>${list.fuelTakenBy}</td>
			<td><a href="#"
				onclick="getFuelRefillingInfo('${list.encFuelRefillingId}')"><button
						class="btn btn-xs btn-primary" type="button">
						<i class="fa fa-pencil"></i>
					</button></a></td>

		</tr>
	</c:forEach>

</c:if>





<c:if test="${! empty FuelRefillingListNotFound}">
	<tr>
		<td colspan="8"><p>${FuelRefillingListNotFound}</p></td>
	</tr>
</c:if>
 --%>



<%-- 
<c:if test="${!empty VehicleRequisitionList}">
	<c:forEach items="${VehicleRequisitionList}" var="list">
		<tr ondblclick="VeRequisitionInfo('${list.encRequisitionId}')"
			style="cursor: pointer">
			<td>${list.requisitionByName}</td>
			<td>${list.requisitionPurpose}</td>
			<td>${list.requisitionDate}</td>
			<td>${list.requisitionTimeFrom}</td>
			<td>${list.requisitionTimeTo}</td>
			<td>${list.passengerNo}</td>
			<td>${list.requisitionStatusId}</td>

			<td><a href="#"
				onclick="VeRequisitionInfo('${list.encRequisitionId}')"><button
						class="btn btn-xs btn-primary" type="button">
						<i class="fa fa-pencil"></i>
					</button></a></td>

		</tr>
	</c:forEach>

</c:if>


<c:if test="${! empty VehicleRequisitionListNotFound}">
	<tr>
		<td colspan="8"><p>${VehicleRequisitionListNotFound}</p></td>
	</tr>
</c:if>
 --%>



<%-- 
<c:if test="${!empty allowanceDeductionList}">
	<c:forEach items="${allowanceDeductionList}" var="list">
		<tr ondblclick="getAllowanceDeductionInfo('${list.encAdCategoryId}')"
			style="cursor: pointer">
			<td>${list.earningDeductionTypeId}</td>
			<td>${list.categoryName}</td>
			<td><a href="#"
				onclick="getAllowanceDeductionInfo('${list.encAdCategoryId}')"><button
						class="btn btn-xs btn-primary" type="button">
						<i class="fa fa-pencil"></i>
					</button></a>
		</tr>
	</c:forEach>

</c:if>


<c:if test="${! empty allowanceDeductionListNotFound}">
	<tr>
		<td colspan="8"><p>${allowanceDeductionListNotFound}</p></td>
	</tr>
</c:if>

 --%>


<%-- 
<c:if test="${!empty categoryList}">
	<input type="hidden" id="trIndex" value="${categoryListSize}">
	<%
		int i = 1;
	%>

	<c:forEach items="${categoryList}" var="list" varStatus="status">
		<tr style="cursor: pointer">
			<td>
				<%
					out.print(i);
							i++;
				%>
			</td>

			<td><input type="hidden" id="edTypeId_${list.adCategoryId}"
				value="${list.edTypeId}"> <input class="flat" type="hidden"
				id="catId_${list.adCategoryId}"
				name="adCategoryIdList[${status.index}].adCategoryId"
				value="${list.adCategoryId}"> ${list.adCategoryName}</td>

			<td><input style="text-align: right" class="flat" type="text"
				id="fixedAmount_${list.adCategoryId}"
				onkeyup="disPerOfBasic(${list.adCategoryId})"
				onblur="disPerOfBasic(${list.adCategoryId})"
				name="adCategoryIdList[${status.index}].fixedAmount"
				onkeypress="return isNumberKey(event)" value="${list.fixedAmount}"
				<c:if test="${list.perOfBasic != '' }">disabled</c:if>></td>

			<td><c:if test="${list.adCategoryId != 0}">
					<c:if test="${list.adCategoryId != 1}">
						<input style="text-align: right" class="flat" type="text"
							id="perOfBasic_${list.adCategoryId}"
							onkeyup="disfixedAmount(${list.adCategoryId})"
							onkeypress="return isNumberKey(event)"
							name="adCategoryIdList[${status.index}].perOfBasic"
							value="${list.perOfBasic}"
							<c:if test="${list.fixedAmount != '' }">disabled</c:if>>


					</c:if>
				</c:if> <span class="amount" style="float: right; padding-right: 10px;"
				id="calcAmount_${list.adCategoryId}">${list.calcAmount}</span></td>
			<td><c:if test="${list.adCategoryId != 0}">
					<c:if test="${list.adCategoryId != 1}">
						<button class="btn-primary" type="button" onclick="removetr(this)">&times;</button>
					</c:if>
				</c:if></td>
		</tr>
	</c:forEach>

</c:if>

<c:if test="${! empty categoryListNotFound}">
	<tr>
		<td colspan="8"><p>${categoryListNotFound}</p></td>
	</tr>
</c:if>
 --%>




<%-- <c:if test="${!empty salaryAlterationListNew}">
	<%
		int i = 1;
	%>
	<c:forEach items="${salaryAlterationListNew}" var="list"
		varStatus="status">
		<tr style="cursor: pointer">
			<td>
				<%
					out.print(i);
							i++;
				%>
			</td>

			<td><input class="flat" type="hidden"
				id="employeeId_${list.employeeId}"
				name="salaryAlterationList[${status.index}].employeeId"
				value="${list.employeeId}"> ${list.employeeName}</td>


			<td><input class="flat" type="text"
				id="deduction_${list.employeeId}"
				name="salaryAlterationList[${status.index}].deduction"
				onkeypress="return isNumberKey(event)" value="${list.deduction}"
				onkeyup="disDeductionDays(${list.employeeId})"></td>

			<td><input class="flat" type="text"
				id="addition_${list.employeeId}"
				name="salaryAlterationList[${status.index}].addition"
				onkeypress="return isNumberKey(event)" value="${list.addition}"
				onkeyup="disAdditionDays(${list.employeeId})"></td>

			<td><input class="flat" type="checkbox"
				id="postponed_${list.employeeId}"
				onclick="disPostponed(${list.employeeId})"
				name="salaryAlterationList[${status.index}].postponed"
				<c:if test="${list.postponed == 'Y'}">checked</c:if> value="Y">
			</td>

			<!-- <td>
				<button class="btn btn-primary" type="button" onclick="saveSalaryAlterationList(id)"
					role="button">
					<i class="fa  fa-save"></i> Save
				</button>
			</td>  -->

		</tr>
	</c:forEach>

</c:if>

<c:if test="${! empty salaryAlterationListNewNotFound}">
	<tr>
		<td colspan="8"><p>${salaryAlterationListNewNotFound}</p></td>
	</tr>
</c:if>
 --%>


<%-- <c:if test="${!empty salaryAlterationList}">
	<%
		int i = 1;
	%>
	<c:forEach items="${salaryAlterationList}" var="list"
		varStatus="status">
		<tr style="cursor: pointer">
			<td>
				<%
					out.print(i);
							i++;
				%>
			</td>

			<td><input class="flat" type="hidden"
				id="employeeId_${list.employeeId}"
				name="salaryAlterationList[${status.index}].employeeId"
				value="${list.employeeId}"> ${list.employeeName}</td>


			<td><input class="flat" type="text"
				id="deduction_${list.employeeId}"
				name="salaryAlterationList[${status.index}].deduction"
				onkeypress="return isNumberKey(event)" value="${list.deduction}"
				onkeyup="disDeductionDays(${list.employeeId})"
				<c:if test="${list.addition != '' }">disabled</c:if>
				<c:if test="${list.postponed == 'Y' }">disabled</c:if>></td>

			<td><input class="flat" type="text"
				id="addition_${list.employeeId}"
				name="salaryAlterationList[${status.index}].addition"
				onkeypress="return isNumberKey(event)" value="${list.addition}"
				onkeyup="disAdditionDays(${list.employeeId})"
				<c:if test="${list.deduction != '' }">disabled</c:if>
				<c:if test="${list.postponed == 'Y' }">disabled</c:if>></td>

			<td><input class="flat" type="checkbox"
				id="postponed_${list.employeeId}"
				onclick="disPostponed(${list.employeeId})"
				name="salaryAlterationList[${status.index}].postponed" value="Y"
				<c:if test="${list.postponed == 'Y'}">checked</c:if>
				<c:if test="${list.deduction != '' }">disabled</c:if>
				<c:if test="${list.addition != '' }">disabled</c:if>></td>

			<td>
				<button class="btn btn-primary" type="button"
					onclick="saveSalaryAlterationList(${list.employeeId})"
					role="button">
					<i class="fa  fa-save"></i> Save
				</button>
			</td>

		</tr>
	</c:forEach>

</c:if>

<c:if test="${! empty salaryAlterationListNotFound}">
	<tr>
		<td colspan="8"><p>${salaryAlterationListNotFound}</p></td>
	</tr>
</c:if>
 --%>

<%-- <c:if test="${!empty payrollProcessList}">
	<%
		int i = 1;
	%>
	<c:forEach items="${payrollProcessList}" var="list">
		<tr ondblclick="VeRequisitionInfo('${list.encEmployeeId}')"
			style="cursor: pointer">

			<td>
				<%
					out.print(i);
							i++;
				%>
			</td>
			<td nowrap>${list.departmentName}</td>
			<td nowrap>${list.designationName}</td>
			<td nowrap>${list.employeeName}</td>
			<td style="text-align: right;">${list.basicPay}</td>
			<td style="text-align: right;">${list.houseRentAllowance}</td>
			<td style="text-align: right;">${list.medicalAllowance}</td>
			<td style="text-align: right;">${list.conveyanceAllowance}</td>
			<td style="text-align: right;">${list.totalGrossPay}</td>
			<td
				style="<c:if test="${list.providentFundDeduction !='0'}"> color : red; </c:if> text-align: right;">
				${list.providentFundDeduction}</td>
			<td
				style="<c:if test="${list.incomeTaxDeduction !='0'}"> color : red; </c:if> text-align: right;">${list.incomeTaxDeduction}</td>
			<td
				style="<c:if test="${list.loanDeduction !='0'}"> color : red; </c:if> text-align: right;">${list.loanDeduction}</td>
			<td
				style="<c:if test="${list.totalDeduction !='0'}"> color : red; </c:if> text-align: right;">${list.payDeduction}</td>
			<td
				style="<c:if test="${list.totalDeduction !='0'}"> color : red; </c:if> text-align: right;">${list.totalDeduction}</td>
			<td style="text-align: right;">${list.attendanceIncentive}</td>
			<td style="text-align: right;">${list.netPayable}</td>



			<td><a href="#"
														onclick="VeRequisitionInfo('${list.encEmployeeId}')"><button
																class="btn btn-xs btn-primary" type="button">
																<i class="fa fa-pencil"></i>
															</button></a></td>

		</tr>
	</c:forEach>

</c:if>

<c:if test="${!empty payrollProcessTotal}">
	<c:forEach items="${payrollProcessTotal}" var="total">
		<tr style="cursor: pointer">
			<td>${payrollProcessList.size() +1}</td>
			<td></td>
			<td></td>
			<td style="text-align: right; color: blue;">Total</td>
			<td style="text-align: right;">${total.basicPay}</td>
			<td style="text-align: right;">${total.houseRentAllowance}</td>
			<td style="text-align: right;">${total.medicalAllowance}</td>
			<td style="text-align: right;">${total.conveyanceAllowance}</td>
			<td style="text-align: right;">${total.totalGrossPay}</td>
			<td
				style="<c:if test="${total.providentFundDeduction !='0'}"> color : red; </c:if> text-align: right;">
				${total.providentFundDeduction}</td>
			<td
				style="<c:if test="${total.incomeTaxDeduction !='0'}"> color : red; </c:if> text-align: right;">${total.incomeTaxDeduction}</td>
			<td
				style="<c:if test="${total.loanDeduction !='0'}"> color : red; </c:if> text-align: right;">${total.loanDeduction}</td>
			<td
				style="<c:if test="${total.totalDeduction !='0'}"> color : red; </c:if> text-align: right;">${total.payDeduction}</td>
			<td
				style="<c:if test="${total.totalDeduction !='0'}"> color : red; </c:if> text-align: right;">${total.totalDeduction}</td>
			<td style="text-align: right;">${total.attendanceIncentive}</td>
			<td style="text-align: right;">${total.netPayable}</td>
		</tr>
	</c:forEach>
</c:if>


<c:if test="${! empty payrollProcessListNotFound}">
	<tr>
		<td colspan="20"><p>${payrollProcessListNotFound}</p></td>
	</tr>
</c:if>
 --%>

<!-- Start Payroll Pending list -->

<%-- <c:if test="${!empty payrollPendingList}">
	<%
		int i = 1;
	%>
	
	<input type="hidden" id="payrollPendingCount" value="${payrollPendingList.size()}">
	
	<c:forEach items="${payrollPendingList}" var="list" varStatus="status">
		<tr style="cursor: pointer">

			<td>
			
				<%
					out.print(i);
							i++;
				%>
			</td>

			<td><div class="checkbox-custom chekbox-primary input-group">
					<input class="to-labelauty" type="checkbox"
						id="selectYN_${list.payrollId}" value="${list.encPayrollId}"
						onclick="checkIndividual(this,'${list.payrollId}')"
						name="payrollPendingListDetails[${status.index}].encPayrollId" />
					<label for="selectYN_${list.payrollId}"></label>
				</div></td>

			<td nowrap>${list.employeeName}</td>
			<td nowrap>${list.departmentName}</td>
			<td nowrap>${list.designationName}</td>

			<td style="text-align: right;">${list.basicPay}</td>
			<td style="text-align: right;">${list.houseRentAllowance}</td>
			<td style="text-align: right;">${list.medicalAllowance}</td>
			<td style="text-align: right;">${list.conveyanceAllowance}</td>
			<td style="text-align: right;">${list.totalGrossPay}</td>
			<td
				style=" <c:if test="${list.providentFundDeduction !='0'}"> color : red;  </c:if> text-align: right;">
				${list.providentFundDeduction}</td>
			<td
				style="<c:if test="${list.incomeTaxDeduction !='0'}"> color :  </c:if> red; text-align: right;">${list.incomeTaxDeduction}</td>
			<td
				style="<c:if test="${list.loanDeduction !='0'}"> color : red;  </c:if> text-align: right;">${list.loanDeduction}</td>
			<td nowrap
				style="<c:if test="${list.payDeduction !='0'}"> color : red;  </c:if> text-align: right;">

				<c:if test="${list.payDeduction !='0'}">
					<a onclick="basicPopup('${list.encPayrollId}');return false">
						Details </a>
				</c:if> ${list.payDeduction}

			</td>

			<td><input class="form-control" type="text"
				id="miscellaneousDeduction_${list.payrollId}" style="color: red;"
				disabled onkeypress="return isNumberKey(event)"
				name="payrollPendingListDetails[${status.index}].miscellaneousDeduction"
				value="${list.miscellaneousDeduction}"></td>

			<td
				style="<c:if test="${list.totalDeduction !='0'}"> color : red;  </c:if> text-align: right;">${list.totalDeduction}</td>

			<td><input class="form-control" type="text"
				id="incentiveBonus_${list.payrollId}" disabled
				onkeypress="return isNumberKey(event)"
				name="payrollPendingListDetails[${status.index}].incentiveBonus"
				value="${list.incentiveBonus}"></td>
			<td style="text-align: right;"><input class="form-control"
				type="text" id="attendanceIncentive_${list.payrollId}" disabled
				onkeypress="return isNumberKey(event)"
				name="payrollPendingListDetails[${status.index}].attendanceIncentive"
				value="${list.attendanceIncentive}"></td>
			<td style="text-align: right;">${list.netPayable}</td>
		</tr>
	</c:forEach>
</c:if>

<c:if test="${!empty payrollPendingTotal}">
	<c:forEach items="${payrollPendingTotal}" var="total">
		<tr style="cursor: pointer">

			<td>${payrollPendingList.size()+1}</td>
			<td></td>
			<td></td>
			<td></td>
			<td colspan="5" style="text-align: right; color: blue;">Total</td>
			<td style="text-align: right;">${total.basicPay}</td>
			<td style="text-align: right;">${total.houseRentAllowance}</td>
			<td style="text-align: right;">${total.medicalAllowance}</td>
			<td style="text-align: right;">${total.conveyanceAllowance}</td>
			<td style="text-align: right;">${total.totalGrossPay}</td>
			<td
				style=" <c:if test="${total.providentFundDeduction !='0'}"> color : red;  </c:if> text-align: right;">${total.providentFundDeduction}</td>
			<td
				style="<c:if test="${total.incomeTaxDeduction !='0'}"> color :  </c:if> red; text-align: right;">${total.incomeTaxDeduction}</td>
			<td
				style="<c:if test="${total.loanDeduction !='0'}"> color : red;  </c:if> text-align: right;">${total.loanDeduction}</td>
			<td
				style="<c:if test="${total.payDeduction !='0'}"> color : red;  </c:if> text-align: right;">${total.payDeduction}</td>
			<td
				style="<c:if test="${total.miscellaneousDeduction !='0'}"> color : red;  </c:if> text-align: right;">${total.miscellaneousDeduction}</td>
			<td
				style="<c:if test="${total.totalDeduction !='0'}"> color : red;  </c:if> text-align: right;">${total.totalDeduction}</td>
			<td style="text-align: right;">${total.incentiveBonus}</td>
			<td style="text-align: right;">${total.attendanceIncentive}</td>
			<td style="text-align: right;">${total.netPayable}</td>
		</tr>
	</c:forEach>
</c:if>



<c:if test="${! empty payrollPendingListNotFound}">
	<tr>
		<td colspan="20"><p>${payrollPendingListNotFound}</p></td>
	</tr>
</c:if>
 --%>

<%-- 
							<table 
								class="table table-striped table-bordered table-condensed table-hover mb-none datatable3333">
								<thead>
									<tr>
										<th>SL No.</th>
										<th nowrap>Name</th>
										<th nowrap>Department / Project</th>
										<th nowrap>Designation</th>
										<th>Basic Pay</th>
										<th>House Rent</th>
										<th>Medical</th>
										<th>Conveyance</th>
										<th>Total Gross Pay</th>
										<th>Provident Fund Deduction</th>
										<th>Income Tax Deduction</th>
										<th>Loan Deduction</th>
										<th>Total Deduction</th>
										<th>Net Payable</th>
										<th>Incentive Bonus</th>
										<th>Miscellaneous Deduction</th>
										<th><div
												class="checkbox-custom chekbox-primary input-group">
												<input class="to-labelauty" type="checkbox" id="selectYN_"
													onclick="checkAll(this)" /> <label for="selectYN_"><strong>Select</strong></label>
											</div></th>
									</tr>
								</thead>
								<tbody id="payrollPendingList">
<div style="overflow: scroll; height:200px;">
									<c:if test="${!empty payrollPendingList}">
										<%
											int i = 1;
										%>
										<c:forEach items="${payrollPendingList}" var="list"
											varStatus="status">
											
											<tr style="cursor: pointer">
												<td>
													<%
														out.print(i);
																i++;
													%>
												</td>
												<td>${list.employeeName}</td>
												<td>${list.departmentName}</td>
												<td>${list.designationName}</td>
												<td>${list.basicPay}</td>
												<td>${list.houseRentAllowance}</td>
												<td>${list.medicalAllowance}</td>
												<td>${list.conveyanceAllowance}</td>
												<td>${list.totalGrossPay}</td>
												<td>${list.providentFundDeduction}</td>
												<td>${list.incomeTaxDeduction}</td>
												<td>${list.loanDeduction}</td>
												<td>${list.totalDeduction}</td>
												<td>${list.netPayable}</td>
												<td> <input class="form-control" type="text" id="incentiveBonus_${list.payrollId}"
												name="payrollPendingListDetails[${status.index}].incentiveBonus" value="${list.incentiveBonus}"> </td>
												<td> <input class="form-control" type="text" id="miscellaneousDeduction_${list.payrollId}"
												name="payrollPendingListDetails[${status.index}].miscellaneousDeduction" value="${list.miscellaneousDeduction}"> </td> 
												<td><div
														class="checkbox-custom chekbox-primary input-group">
														<input class="to-labelauty" type="checkbox"
															id="selectYN_${list.payrollId}"
															value="${list.encPayrollId}"
															name="payrollPendingListDetails[${status.index}].encPayrollId" />
														<label for="selectYN_${list.payrollId}"></label>
													</div></td>
											</tr>
											
										</c:forEach>
										</div>
									</c:if>
									
								</tbody>
							</table> --%>

<!-- End Payroll Pending list -->

<c:if test="${!empty roleAllMenuList}">
	<%
		int i = 1;
	%>
	<c:forEach items="${roleAllMenuList}" var="list">
		<tr style="cursor: pointer">
			<td>
				<%
					out.print(i);
							i++;
				%>
			</td>
			<td>${list.mainMenuName}</td>
			<td>${list.menuName}</td>
			<td>${list.insert}</td>
			<td>${list.delete}</td>
		</tr>
	</c:forEach>
</c:if>

<c:if test="${! empty roleAllMenuNotFound}">
	<tr>
		<td colspan="5"><p>${roleAllMenuNotFound}</p></td>
	</tr>
</c:if>


<c:if test="${!empty roleMenuMappingList}">

	<c:forEach items="${roleMenuMappingList}" var="list" varStatus="status">
		<tr style="cursor: pointer">
			<td>
				<div class="checkbox-custom chekbox-primary input-group">
					<input type="hidden"
						name="roleMenuDetails[${status.index}].encMenuId"
						id="encMenuId_${list.encMenuId}" value="${list.encMenuId}">

					<input class="to-labelauty" type="checkbox"
						id="allowYN_${list.encMenuId}"
						name="roleMenuDetails[${status.index}].allowYN" value="Y"
						<c:if test="${list.allowYN == 'Y' && !empty list.encMenuId}">checked</c:if>>

					<label for="allowYN_${list.encMenuId}">${list.menuName}</label>
				</div>
			</td>



			<td>
				<div class="checkbox-custom chekbox-primary input-group">
					<input class="to-labelauty" type="checkbox"
						name="roleMenuDetails[${status.index}].insertYN"
						id="insertYN_${list.encMenuId}" value="Y"
						<c:if test="${list.insert == 'Y'}">checked</c:if>> <label
						for="insertYN_${list.encMenuId}">${list.insertYN}</label>
				</div>
			</td>


			<td>
				<div class="checkbox-custom chekbox-primary input-group">
					<input class="to-labelauty" type="checkbox"
						name="roleMenuDetails[${status.index}].deleteYN"
						id="deleteYN_${list.encMenuId}" value="Y"
						<c:if test="${list.delete == 'Y'}">checked</c:if>> <label
						for="deleteYN_${list.encMenuId}">${list.deleteYN}</label>
				</div>
			</td>


		</tr>
	</c:forEach>
</c:if>

<c:if test="${! empty roleMenuNotFound}">
	<tr>
		<td colspan="7"><p>${roleMenuNotFound}</p></td>
	</tr>
</c:if>


<%-- <c:if test="${!empty loanInfoList}">
	<c:forEach items="${loanInfoList}" var="list">
		<tr ondblclick="getLoanInfo('${list.encEmployeeId}')"
			style="cursor: pointer">
			<td>${list.loanCategoryName}</td>
			<td>${list.employeeName}</td>
			<td>${list.loanSanctionDate}</td>
			<td>${list.loanAmount}</td>
			<td>${list.recoveryAmount}</td>
			<td>${list.dueAmount}</td>
			<td>${list.requisitionStatusId}</td>

			<td><a href="#" onclick="getLoanInfo('${list.encEmployeeId}')"><button
						class="btn btn-xs btn-primary" type="button">
						<i class="fa fa-pencil"></i>
					</button></a></td>

		</tr>
	</c:forEach>

</c:if>


<c:if test="${! empty loanInfoListNotFound}">
	<tr>
		<td colspan="8"><p>${loanInfoListNotFound}</p></td>
	</tr>
</c:if>
 --%>


<%-- <c:if test="${!empty postingInfoList}">
	<c:forEach items="${postingInfoList}" var="list">
		<tr
			ondblclick="getPostingInfo('${list.encPostingId}','${list.encEmployeeId}')"
			style="cursor: pointer">
			<td><input type="hidden" value="${list.encEmployeeId}"></input>${list.employeeName}</td>
			<td>${list.designationId}</td>
			<td>${list.employeeNameOrderBy}</td>
			<td>${list.orderDate}</td>
			<td>${list.effectiveDate}</td>
			<td>${list.postingTo}</td>
			<td><a href="#"
				onclick="getPostingInfo('${list.encPostingId}','${list.encEmployeeId}')"><button
						class="btn btn-xs btn-primary" type="button">
						<i class="fa fa-pencil"></i>
					</button></a></td>

		</tr>
	</c:forEach>

</c:if>

<c:if test="${! empty postingInfoListNotFound}">
	<tr>
		<td colspan="8"><p>${postingInfoListNotFound}</p></td>
	</tr>
</c:if>
 --%>


<%-- <c:if test="${!empty leaveInfoList}">
	<c:forEach items="${leaveInfoList}" var="list">
		<tr style="cursor: pointer">
			<td><input type="hidden" value="${list.encEmployeeId}"></input>${list.employeeName}</td>
			<td>${list.leaveTypeId}</td>
			<td>${list.fromDate}</td>
			<td>${list.toDate}</td>
			<td>${list.alterEmpName}</td>
			<td>${list.emergencyContactNo}</td>
			<td>${list.leaveDescription}</td>
			<td><c:if test="${list.editableYN == 'Y'}">
					<a href="#"
						onclick="getLeaveInfo('${list.encLeaveId}','${list.encEmployeeId}','${list.editableYN}')">
						<button class="btn btn-xs btn-primary" type="button">
							<i class="fa fa-pencil"></i>
						</button>
					</a>
				</c:if></td>

		</tr>
	</c:forEach>

</c:if>

<c:if test="${! empty leaveInfoListNotFound}">
	<tr>
		<td colspan="8"><p>${leaveInfoListNotFound}</p></td>
	</tr>
</c:if>
 --%>
 <%-- 
<c:if test="${!empty absentInfoList}">
	<c:forEach items="${absentInfoList}" var="list">
		<tr style="cursor: pointer">
			<td><input type="hidden" value="${list.encEmployeeId}"></input>${list.employeeName}</td>
			<td>${list.absentType}</td>
			<td>${list.absentDate}</td>
			<td>${list.emergencyContactNo}</td>
			<td>${list.leaveDescription}</td>
			<td><c:if test="${list.editableYN == 'Y'}">
					<a href="#"
						onclick="getAbsentInfo('${list.encAbsentId}','${list.encEmployeeId}','${list.editableYN}')"><button
							class="btn btn-xs btn-primary" type="button">
							<i class="fa fa-pencil"></i>
						</button></a>
				</c:if></td>

		</tr>
	</c:forEach>

</c:if>

<c:if test="${! empty absentInfoListNotFound}">
	<tr>
		<td colspan="8"><p>${absentInfoListNotFound}</p></td>
	</tr>
</c:if>
 --%>
 <%-- 
<c:if test="${!empty onDutyInfoList}">
	<c:forEach items="${onDutyInfoList}" var="list">
		<tr style="cursor: pointer">
			<td><input type="hidden" value="${list.encEmployeeId}"></input>${list.employeeName}</td>
			<td>${list.fromDate}</td>
			<td>${list.toDate}</td>
			<td>${list.alterEmpName}</td>
			<td>${list.emergencyContactNo}</td>

			<td><c:if test="${list.editableYN == 'Y'}">
					<a href="#"
						onclick="getOnDutyInfo('${list.encOnDutyId}','${list.encEmployeeId}','${list.editableYN}')"><button
							class="btn btn-xs btn-primary" type="button">
							<i class="fa fa-pencil"></i>
						</button></a>
				</c:if></td>

		</tr>
	</c:forEach>

</c:if>

<c:if test="${! empty onDutyInfoListNotFound}">
	<tr>
		<td colspan="8"><p>${onDutyInfoListNotFound}</p></td>
	</tr>
</c:if>
 --%>
 <%-- 
<c:if test="${!empty holidayInfoList}">
	<c:forEach items="${holidayInfoList}" var="list">
		<tr style="cursor: pointer">
			<td><input type="hidden" value="${list.encHolidayId}" />${list.fromDate}</td>
			<td>${list.holidayDate}</td>
			<td>${list.toDate}</td>
			<td>${list.holidayDescription}</td>
			<td><a href="#" onclick="getHolidayInfo('${list.encHolidayId}')"><button
						class="btn btn-xs btn-primary" type="button">
						<i class="fa fa-pencil"></i>
					</button></a></td>

		</tr>
	</c:forEach>

</c:if>

<c:if test="${! empty holidayInfoListNotFound}">
	<tr>
		<td colspan="8"><p>${holidayInfoListNotFound}</p></td>
	</tr>
</c:if> --%>


<%-- <c:if test="${!empty lateAttendenceInfoList}">
	<c:forEach items="${lateAttendenceInfoList}" var="list">
		<tr
			ondblclick="getLateAttendenceInfo('${list.encLateAttendenceId}','${list.encEmployeeId}')"
			style="cursor: pointer">
			<td><input type="hidden" value="${list.encEmployeeId}" /><input
				type="hidden" value="${list.encLateAttendenceId}" />${list.employeeName}</td>
			<td>${list.designationName}</td>
			<td>${list.lateAttendenceDate}</td>
			<td>${list.lateAttendenceDescription}</td>
			<td><input type="hidden" value="${list.departmentId}"/>${list.departmentName}</td>
			<td><a href="#"
				onclick="getLateAttendenceInfo('${list.encLateAttendenceId}','${list.encEmployeeId}')"><button
						class="btn btn-xs btn-primary" type="button">
						<i class="fa fa-pencil"></i>
					</button></a></td>

		</tr>
	</c:forEach>

</c:if>

<c:if test="${! empty lateAttendenceInfoListNotFound}">
	<tr>
		<td colspan="8"><p>${lateAttendenceInfoListNotFound}</p></td>
	</tr>
</c:if>
 --%>

<%-- <c:if test="${!empty taskTrackingInfoList}">
	<c:forEach items="${taskTrackingInfoList}" var="list">

		<tr ondblclick="getTaskTrackingInfo('${list.encEffortTrackingId}')"
			style="cursor: pointer" id="oldDataRow_${list.effortTrackingId}">

			<td><input type="hidden"
				id="encEffortTrackingId_${list.effortTrackingId}"
				value="${list.encEffortTrackingId}" /> <select
				style="display: none; width: 190px;" data-plugin-selectTwo
				class="form-control" id="projectId_${list.effortTrackingId}"
				required>
					<option>Select One</option>
					<c:forEach items="${projectsList}" var="listProject">
						<option
							<c:if test="${listProject.id == list.projectId}">selected = "selected" </c:if>
							value="${listProject.id}">${listProject.name}</option>
					</c:forEach>
			</select>
				<p id="dataProjectId_${list.effortTrackingId}">
					${list.projectName}</p></td>

			<td><select style="display: none; width: 120px;"
				data-plugin-selectTwo class="form-control"
				id="taskTypeId_${list.effortTrackingId}" required>
					<option value="">Please Select One</option>
					<c:forEach items="${taskTypeList}" var="listTaskType">
						<option
							<c:if test="${listTaskType.id == list.taskTypeId}">selected = "selected" </c:if>
							value="${listTaskType.id}">${listTaskType.name}</option>
					</c:forEach>
			</select>
				<p id="dataTaskTypeId_${list.effortTrackingId}">
					${list.taskTypeName}</p></td>

			<td><textarea style="display: none; width: 110px;"
					id="taskDesc_${list.effortTrackingId}" class="form-control"
					rows="1" cols="33">${list.taskDesc}</textarea>
				<p id="dataTaskDesc_${list.effortTrackingId}">${list.taskDesc}</p></td>

			<td><input style="display: none; width: 85px;"
				value="${list.startTime}" class="form-control"
				id="startTime_${list.effortTrackingId}"
				onchange="getTimeDiffEdit('${list.effortTrackingId}')"
				data-plugin-timepicker data-plugin-options='{ "minuteStep": 5 }' />
				<p id="dataStartTime_${list.effortTrackingId}">
					${list.startTime}</p></td>

			<td><input style="display: none; width: 85px;"
				value="${list.endTime}" class="form-control"
				id="endTime_${list.effortTrackingId}"
				onchange="getTimeDiffEdit('${list.effortTrackingId}')"
				data-plugin-timepicker data-plugin-options='{ "minuteStep": 5 }' />
				<p id="dataEndTime_${list.effortTrackingId}">${list.endTime}</p></td>

			<td><input style="display: none; width: 65px;"
				value="${list.totalHour}" id="totalHour_${list.effortTrackingId}"
				class="form-control" readonly />
				<p id="dataTotalHour_${list.effortTrackingId}">
					${list.totalHour}</p></td>

			<td><input style="display: none; width: 65px;"
				value="${list.noOfDefect}" class="form-control"
				id="noOfDefect_${list.effortTrackingId}"
				onkeydown="return isNumberKey(event)" />
				<p id="dataNoOfDefect_${list.effortTrackingId}">${list.noOfDefect}</p>
			</td>

			<td><textarea style="display: none; width: 100px;"
					id="defectDesc_${list.effortTrackingId}" class="form-control"
					rows="1" cols="33">${list.defectDesc}</textarea>
				<p id="dataDefectDesc_${list.effortTrackingId}">${list.defectDesc}</p>
			</td>

			<td><c:if test="${list.editableYN == 'Y'}">
					<a onclick="editOldData('${list.effortTrackingId}')"
						id="edit_${list.effortTrackingId}">
						<button class="btn btn-primary" type="button" style="width: 75px;">
							<span> <i class="fa fa-pencil"></i> Edit
							</span>
						</button>

					</a>

					<a id="update_${list.effortTrackingId}" style="display: none;"
						onclick="saveUpdateData('${list.effortTrackingId}')">
						<button class="btn btn-primary" type="button" style="width: 75px;">
							<span> <i class="fa fa-save"></i> Update
							</span>
						</button>

					</a>

					<a id="clear_${list.effortTrackingId}" style="display: none;"
						onclick="cancelEditOldData('${list.effortTrackingId}')">
						<button class="btn btn-default" type="button" style="width: 75px;">
							<span> <i class="fa fa-refresh"></i> Cancel
							</span>
						</button>
					</a>

				</c:if></td>

		</tr>
	</c:forEach>

</c:if>

<c:if test="${! empty taskTrackingInfoListNotFound}">
	<tr>
		<td colspan="10"><p>${taskTrackingInfoListNotFound}</p></td>
	</tr>
</c:if>
 --%>

<%-- <c:if test="${!empty taskTrackingCorrectionList}">
	<c:forEach items="${taskTrackingCorrectionList}" var="list">

		<tr ondblclick="getTaskTrackingInfo('${list.encEffortTrackingId}')"
			style="cursor: pointer" id="oldDataRow_${list.effortTrackingId}">

			<td><input type="hidden"
				id="encEffortTrackingId_${list.effortTrackingId}"
				value="${list.encEffortTrackingId}" /> <select
				style="display: none; width: 190px;" data-plugin-selectTwo
				class="form-control" id="projectId_${list.effortTrackingId}"
				required>
					<option>Select One</option>
					<c:forEach items="${projectsList}" var="listProject">
						<option
							<c:if test="${listProject.id == list.projectId}">selected = "selected" </c:if>
							value="${listProject.id}">${listProject.name}</option>
					</c:forEach>
			</select>
				<p id="dataProjectId_${list.effortTrackingId}">
					${list.projectName}</p></td>

			<td><select style="display: none; width: 120px;"
				data-plugin-selectTwo class="form-control"
				id="taskTypeId_${list.effortTrackingId}" required>
					<option value="">Please Select One</option>
					<c:forEach items="${taskTypeList}" var="listTaskType">
						<option
							<c:if test="${listTaskType.id == list.taskTypeId}">selected = "selected" </c:if>
							value="${listTaskType.id}">${listTaskType.name}</option>
					</c:forEach>
			</select>
				<p id="dataTaskTypeId_${list.effortTrackingId}">
					${list.taskTypeName}</p></td>

			<td><textarea style="display: none; width: 110px;"
					id="taskDesc_${list.effortTrackingId}" class="form-control"
					rows="1" cols="33">${list.taskDesc}</textarea>
				<p id="dataTaskDesc_${list.effortTrackingId}">${list.taskDesc}</p></td>

			<td><input style="display: none; width: 85px;"
				value="${list.startTime}" class="form-control"
				id="startTime_${list.effortTrackingId}"
				onchange="getTimeDiffEdit('${list.effortTrackingId}')"
				data-plugin-timepicker data-plugin-options='{ "minuteStep": 5 }' />
				<p id="dataStartTime_${list.effortTrackingId}">
					${list.startTime}</p></td>

			<td><input style="display: none; width: 85px;"
				value="${list.endTime}" class="form-control"
				id="endTime_${list.effortTrackingId}"
				onchange="getTimeDiffEdit('${list.effortTrackingId}')"
				data-plugin-timepicker data-plugin-options='{ "minuteStep": 5 }' />
				<p id="dataEndTime_${list.effortTrackingId}">${list.endTime}</p></td>

			<td><input style="display: none; width: 65px;"
				value="${list.totalHour}" id="totalHour_${list.effortTrackingId}"
				class="form-control" readonly />
				<p id="dataTotalHour_${list.effortTrackingId}">
					${list.totalHour}</p></td>

			<td><input style="display: none; width: 65px;"
				value="${list.noOfDefect}" class="form-control"
				id="noOfDefect_${list.effortTrackingId}"
				onkeydown="return isNumberKey(event)" />
				<p id="dataNoOfDefect_${list.effortTrackingId}">${list.noOfDefect}</p>
			</td>

			<td><textarea style="display: none; width: 100px;"
					id="defectDesc_${list.effortTrackingId}" class="form-control"
					rows="1" cols="33">${list.defectDesc}</textarea>
				<p id="dataDefectDesc_${list.effortTrackingId}">${list.defectDesc}</p>
			</td>

			<td><a onclick="editOldData('${list.effortTrackingId}')"
				id="edit_${list.effortTrackingId}">
					<button class="btn btn-primary" type="button" style="width: 75px;">
						<span> <i class="fa fa-pencil"></i> Edit
						</span>
					</button>

			</a> <a data-confirm="Are you sure you want to delete the effort"
				id="delete_${list.effortTrackingId}">
					<button class="btn btn-danger" type="button" style="width: 75px;"
						onclick="deleteOldData('${list.encEffortTrackingId}')">
						<span> <i class="fa fa-trash-o"></i> Delete
						</span>
					</button>
			</a> <a id="update_${list.effortTrackingId}" style="display: none;"
				onclick="saveUpdateData('${list.effortTrackingId}')">
					<button class="btn btn-primary" type="button" style="width: 75px;">
						<span> <i class="fa fa-save"></i> Update
						</span>
					</button>

			</a> <a id="clear_${list.effortTrackingId}" style="display: none;"
				onclick="cancelEditOldData('${list.effortTrackingId}')">
					<button class="btn btn-default" type="button" style="width: 75px;">
						<span> <i class="fa fa-refresh"></i> Cancel
						</span>
					</button>
			</a></td>

		</tr>
	</c:forEach>

</c:if>

<c:if test="${! empty taskTrackingCorrectionListNotFound}">
	<tr>
		<td colspan="10"><p>${taskTrackingCorrectionListNotFound}</p></td>
	</tr>
</c:if>
 --%>


<!-- Start Project Role Assignment -->

<%-- <c:if test="${!empty projectRoleAssignList}">
	<%
		int i = 1;
	%>

	<c:forEach items="${projectRoleAssignList}" var="list">
		<tr
			ondblclick="getProjectRoleInfo('${list.encAssignmentId}','${list.encEmployeeId}')"
			style="cursor: pointer">
			<td>
				<%
					out.print(i);
				%>
			</td>

			<td><input type="hidden" value="${list.encEmployeeId}" /><input
				type="hidden" value="${list.encAssignmentId}" />${list.employeeName}</td>
			<td>${list.projectName}</td>
			<td>${list.projectRoleName}</td>
			<td>${list.startDate}</td>
			<td>${list.endDate}</td>
			<td>${list.enabledYN}</td>
			<td>${list.remarks}</td>
			<td><a href="#"
				onclick="getProjectRoleInfo('${list.encAssignmentId}','${list.encEmployeeId}')"><button
						class="btn btn-xs btn-primary" type="button">
						<i class="fa fa-pencil"></i>
					</button></a></td>

		</tr>
		<%
			i = i + 1;
		%>

	</c:forEach>

</c:if>

<c:if test="${! empty projectRoleAssignListNotFound}">
	<tr>
		<td colspan="10"><p>${projectRoleAssignListNotFound}</p></td>
	</tr>
</c:if>
 --%>
<!-- End Project Role Assignment -->

<!-- Start effect Edit Permission List -->

<%-- <c:if test="${!empty effectEditPermissionList}">
	<%
		int i = 1;
	%>

	<c:forEach items="${effectEditPermissionList}" var="list">
		<tr
			ondblclick="getEffectEditPerInfo('${list.encEmployeeId}','${list.encDepartmentId}')"
			style="cursor: pointer">
			<td>
				<%
					out.print(i);
				%>
			</td>

			<td><input type="hidden" value="${list.encEmployeeId}" />${list.employeeName}</td>
			<td>${list.designationName}</td>
			<td><input type="hidden" value="${list.encDepartmentId}" />${list.departmentName}</td>
			<td>${list.enabledYN}</td>
			<td>${list.remarks}</td>
			<td><a href="#"
				onclick="getEffectEditPerInfo('${list.encEmployeeId}','${list.encDepartmentId}')"><button
						class="btn btn-xs btn-primary" type="button">
						<i class="fa fa-pencil"></i>
					</button></a></td>

		</tr>
		<%
			i = i + 1;
		%>

	</c:forEach>

</c:if>

<c:if test="${! empty effectEditPermissionListNotFound}">
	<tr>
		<td colspan="10"><p>${effectEditPermissionListNotFound}</p></td>
	</tr>
</c:if>
 --%>
<!-- End effect Edit Permission List -->


<%-- 
<c:if test="${!empty rosterEmpAttendaceUploadList}">
	<%
		int i = 1;
	%>

	<c:forEach items="${rosterEmpAttendaceUploadList}" var="list">
		<tr>
			<td style="text-align: center;">
				<%
					out.print(i);
				%>
			</td>

			<td style="text-align: center;">${list.employeeId}</td>
			<td style="text-align: center;">${list.employeeName}</td>
			<td style="text-align: center;">${list.enrollmentId}</td>
			<td style="text-align: center;">${list.payrollYear}</td>
			<td style="text-align: center;">${list.payrollMonth}</td>
			<td style="text-align: center;">${list.totalAbsent}</td>
			<td style="text-align: center;">${list.totalLeave}</td>
			<td style="text-align: center;">${list.totalLate}</td>
			<td style="text-align: center;">${list.totalOffDayWork}</td>
			<td style="text-align: center;">${list.totalLeaveWithoutPay}</td>

		</tr>
		<%
			i = i + 1;
		%>

	</c:forEach>

</c:if>

<c:if test="${! empty rosterEmpAttendaceUploadListNotFound}">
	<tr>
		<td colspan="11"><p>${rosterEmpAttendaceUploadListNotFound}</p></td>
	</tr>
</c:if>
 --%>

<!-- Start Payroll Edit list -->

<%-- <c:if test="${!empty payrollEditList}">
	<%
		int i = 1;
	%>

	 <input type="hidden" id="payrollEditListCount" value="${payrollEditList.size()}"> 

	<c:forEach items="${payrollEditList}" var="list" varStatus="status">
		<tr style="cursor: pointer">
			<td>
				<%
					out.print(i);
							i++;
				%>
			</td>
			<td><div class="checkbox-custom chekbox-primary input-group">
					<input class="to-labelauty" type="checkbox"
						id="selectYN_${list.payrollId}" value="${list.encPayrollId}"
						onclick="checkIndividual(this,'${list.payrollId}')"
						name="payrollPendingListDetails[${status.index}].encPayrollId" />
					<label for="selectYN_${list.payrollId}"></label>
				</div></td>
			<td nowrap>${list.employeeName}</td>
			<td nowrap>${list.departmentName}</td>
			<td nowrap>${list.designationName}</td>

			<td style="text-align: right;">${list.basicPay}</td>
			<td style="text-align: right;">${list.houseRentAllowance}</td>
			<td style="text-align: right;">${list.medicalAllowance}</td>
			<td style="text-align: right;">${list.conveyanceAllowance}</td>
			<td style="text-align: right;">${list.totalGrossPay}</td>
			<td
				style=" <c:if test="${list.providentFundDeduction !='0'}"> color : red;  </c:if> text-align: right;">
				${list.providentFundDeduction}</td>
			<td style="<c:if test="${list.incomeTaxDeduction !='0'}"> color :  </c:if> red; text-align: right;">${list.incomeTaxDeduction}</td>
			<td><input class="form-control" type="text"
				id="incomeTaxDeduction_${list.payrollId}" style="color: red;"
				disabled onkeypress="return isNumberKey(event)"
				name="payrollPendingListDetails[${status.index}].incomeTaxDeduction"
				value="${list.incomeTaxDeduction}"></td>
			<td
				style="<c:if test="${list.loanDeduction !='0'}"> color : red;  </c:if> text-align: right;">${list.loanDeduction}</td>
			<td nowrap
				style="<c:if test="${list.payDeduction !='0'}"> color : red;  </c:if> text-align: right;">

				<c:if test="${list.payDeduction !='0'}"> <a
				onclick="basicPopup('${list.encPayrollId}');return false">
					Details </a> </c:if> ${list.payDeduction}

			</td>

			<td><input class="form-control" type="text"
				id="miscellaneousDeduction_${list.payrollId}" style="color: red;"
				disabled onkeypress="return isNumberKey(event)"
				name="payrollPendingListDetails[${status.index}].miscellaneousDeduction"
				value="${list.miscellaneousDeduction}"></td>

			<td
				style="<c:if test="${list.totalDeduction !='0'}"> color : red;  </c:if> text-align: right;">${list.totalDeduction}</td>

			<td><input class="form-control" type="text"
				id="incentiveBonus_${list.payrollId}" disabled
				onkeypress="return isNumberKey(event)"
				name="payrollPendingListDetails[${status.index}].incentiveBonus"
				value="${list.incentiveBonus}"></td>
			<td style="text-align: right;"><input class="form-control"
				type="text" id="attendanceIncentive_${list.payrollId}" disabled
				onkeypress="return isNumberKey(event)"
				name="payrollPendingListDetails[${status.index}].attendanceIncentive"
				value="${list.attendanceIncentive}"></td>
			<td style="text-align: right;">${list.netPayable}</td>
		</tr>
	</c:forEach>
</c:if>

<c:if test="${!empty payrollEditTotal}">
	<c:forEach items="${payrollEditTotal}" var="total">
		<tr style="cursor: pointer">
			<td>${payrollEditList.size()+1}</td>
			<td></td>
			<td></td>
			<td></td>
			<td colspan="5"  style="text-align: right; color: blue;">Total</td>
			<td style="text-align: right;">${total.basicPay}</td>
			<td style="text-align: right;">${total.houseRentAllowance}</td>
			<td style="text-align: right;">${total.medicalAllowance}</td>
			<td style="text-align: right;">${total.conveyanceAllowance}</td>
			<td style="text-align: right;">${total.totalGrossPay}</td>
			<td
				style=" <c:if test="${total.providentFundDeduction !='0'}"> color : red;  </c:if> text-align: right;">
				${total.providentFundDeduction}</td>
			<td
				style=" <c:if test="${total.incomeTaxDeduction !='0'}"> color : red;  </c:if> text-align: right;">${total.incomeTaxDeduction}</td>
			<td
				style="<c:if test="${total.loanDeduction !='0'}"> color : red;  </c:if> text-align: right;">${total.loanDeduction}</td>
			<td
				style="<c:if test="${total.payDeduction !='0'}"> color : red;  </c:if> text-align: right;">
				${total.payDeduction}</td>
			<td
				style="<c:if test="${total.miscellaneousDeduction !='0'}"> color : red;  </c:if> text-align: right;">
				${total.miscellaneousDeduction}</td>
			<td
				style="<c:if test="${total.totalDeduction !='0'}"> color : red;  </c:if> text-align: right;">${total.totalDeduction}</td>
			<td style="text-align: right;">${total.incentiveBonus}</td>
			<td style="text-align: right;">${total.attendanceIncentive}</td>
			<td style="text-align: right;">${total.netPayable}</td>
		</tr>
	</c:forEach>
</c:if>


<c:if test="${! empty payrollEditListNotFound}">
	<tr>
		<td colspan="20"><p>${payrollEditListNotFound}</p></td>
	</tr>
</c:if>
 --%>

<%--  Owner Consumption info --%>


<c:if test="${!empty ownerConsumptionInfoList}">
	<%
		int i = 1;
	%>

	<c:forEach items="${ownerConsumptionInfoList}" var="list">
		<tr>
			<td style="text-align: center;">
				<%
					out.print(i);
				%>
			</td>
			
			

			<td style="text-align: left;">${list.consumeDate}</td>
			<td style="text-align: left;">${list.itemName}</td>
			<td style="text-align: center;">${list.quantity}</td>
			<td style="text-align: left;">${list.remarks}</td>
			<td style="text-align: left;">${list.employeeName}</td>
			
			<td>
			<c:if test="${list.editable == 'Y'}">
			<a href="#"
				onclick="getOwnerConsumeInfo('${list.encConsumeId}')"><button
						class="btn btn-xs btn-primary" type="button">
						<i class="fa fa-pencil"></i>
					</button></a>
			</c:if>
			</td>

		</tr>
		<%
			i = i + 1;
		%>

	</c:forEach>

</c:if>

<c:if test="${! empty ownerConsumptionInfoListNotFound}">
	<tr>
		<td colspan="7" class="hiddenRow"><div>${ownerConsumptionInfoListNotFound}</div></td>
		<td style="display:none;"> </td>
		<td style="display:none;"> </td>
		<td style="display:none;"> </td>
		<td style="display:none;"> </td>
		<td style="display:none;"> </td>
		<td style="display:none;"> </td>
	</tr>
</c:if>




<c:if test="${!empty membershipList}">
	<%
		int i = 1;
	%>
	<c:forEach items="${membershipList}" var="list">
		<tr  ondblclick="getMemberInfo('${list.encMemberId}')"
			style="cursor: pointer">
			<td>
				<%
					out.print(i);
							i++;
				%>
			</td>
			<td>${list.memberNo}</td>
			<td>${list.memberName}</td>
			<td>${list.knownAs}</td>
			<td>${list.contactNo}</td>
			<td>${list.address}</td>
			
			<td>
			 <a href="#"
				onclick="getMemberInfo('${list.encMemberId}')"> 
				<button
						class="btn btn-xs btn-primary" type="button">
						<i class="fa fa-pencil"></i>
					</button></a>
		  	</td>
		  
		</tr>
	</c:forEach>

</c:if>

<c:if test="${! empty membershipListNotFound}">
	<tr>
		<td colspan="7"><p>${membershipListNotFound}</p></td>
		<td style="display:none;"></td>
		<td style="display:none;"></td>
		<td style="display:none;"></td>
		<td style="display:none;"></td>
		<td style="display:none;"></td>
		<td style="display:none;"></td>
	</tr>
</c:if>
