<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<style>
th { white-space: nowrap; }
</style>

<header class="page-header">
	<!-- <h2>Employee Consumption Info</h2> -->

	<div class="left-wrapper pull-left">
		<ol class="breadcrumbs">
			<li><a href="/pos/"> &nbsp; <i class="fa fa-home"></i>
			</a></li>
			<li><span>Accounts &nbsp;</span></li>
			<li><span>Consumption History &nbsp;</span></li>
		</ol>

	</div>
</header>
		

<!-- start: page -->

<div class="row">




	<div class="col-lg-12">


		<!-- message -->

		<c:if test="${!empty mCode}">
			<div
				class="alert
						<c:choose>
						<c:when test="${mCode == '0000'}"> <!-- false -->
						alert-danger 
						</c:when>
						<c:when test="${mCode == '1111'}"> <!-- true -->
						alert-success
						</c:when>
						<c:otherwise>
						alert-primary
						</c:otherwise>
						</c:choose>
						
						">

				<button class="close" aria-hidden="true" data-dismiss="alert"
					type="button">&times;</button>
				<strong>${message}</strong>
			</div>
		</c:if>

		<!-- message -->

		<section class="panel panel-featured panel-featured-primary">
			<header class="panel-heading">
				<h2 class="panel-title">Consumption History List</h2>

				<div class="panel-actions" id="resultDiv">
					<p id="result"></p>
				</div>
			</header>	
			
			
			<div class="panel-actions">
					<a href="/pos/accounts/ownerConsumptionInfo"><button
							class="btn btn-primary btn-round" type="button">
							<span><i class="fa fa-arrow-left" aria-hidden="true"></i> Back
								 </span>
						</button></a>
			</div>


			<div class=" panel-body">
				<div class="table-responsive datatable">
					<div style="overflow: scroll; max-height: 1000px;">
						<table
							class="table table-striped table-bordered table-condensed table-hover mb-none"
							id="consumeHistoryDataTable" role="grid">

							<thead>
								<tr>
									<th>#</th>
									<th>Consume Date</th>
									<th>Owner Name</th>
									<th>Member No & Name</th>
									<th>Item Name</th>
									<th>Quantity</th>
									<th>Price</th>
									<th>Remarks</th>
									<th>Update By</th>
								</tr>
							</thead>

							<tbody id="ownerConsumeData">

								<c:if test="${!empty consumptionHistoryList}">
									<%
										int i = 1;
									%>

									<c:forEach items="${consumptionHistoryList}" var="list">
										<tr>
											<td style="text-align: center;">
												<%
													out.print(i);
												%>
											</td>

											<td style="text-align: left;">${list.consumeDate}</td>
											<td style="text-align: left;">${list.employeeName}</td>
											<td style="text-align: left;">${list.memberName}</td>
											<td style="text-align: left;">${list.itemName}</td>
											<td style="text-align: center;">${list.quantity}</td>
											<td style="text-align: center;">${list.price}</td>
											<td style="text-align: left;">${list.remarks}</td>
											<td style="text-align: left;">${list.updateBy}</td>
										</tr>
										<%
											i = i + 1;
										%>

									</c:forEach>

								</c:if>

								<c:if test="${! empty consumptionHistoryListNotFound}">
									<tr>
										<td colspan="9" class="hiddenRow"><div>${consumptionHistoryListNotFound}</div></td>
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
								
								
							
						             <tfoot>
							            <tr>
							                <th></th>
							                <th></th>
							                <th></th>
							                <th></th>
							                <th></th>
							                <th></th>
							                <th></th>
							                <th></th>
							                <th></th>
							            </tr>
							        </tfoot>
						       

							</tbody>
						</table>

					</div>
				</div>
			</div>

		</section>

	</div>
</div>




<!-- end: page -->

<script src = "https://code.jquery.com/jquery-3.3.1.js"></script>
<script src = "https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>

<script>
	
	
window.onload = function load() {
	$("#consumeHistoryDataTable").DataTable();
}
/* $(document).ready(function() {
    $('#consumeHistoryDataTable').DataTable( {
        "footerCallback": function ( row, data, start, end, display ) {
            var api = this.api(), data;
 
            // Remove the formatting to get integer data for summation
            var intVal = function ( i ) {
                return typeof i === 'string' ?
                    i.replace(/[\$,]/g, '')*1 :
                    typeof i === 'number' ?
                        i : 0;
            };
 
            // Total over all pages
            total = api
                .column( 6 )
                .data()
                .reduce( function (a, b) {
                    return intVal(a) + intVal(b);
                }, 0 );
 
            // Total over this page
            pageTotal = api
                .column( 6, { page: 'current'} )
                .data()
                .reduce( function (a, b) {
                    return intVal(a) + intVal(b);
                }, 0 );
 
            // Update footer
            $( api.column( 6 ).footer() ).html(
                '$'+pageTotal +' ( $'+ total +' total)'
            );
        }
    } );
} ); */
	
</script>