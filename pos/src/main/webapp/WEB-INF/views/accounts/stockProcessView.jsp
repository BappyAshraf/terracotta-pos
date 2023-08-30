<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<header class="page-header">
	<!-- <h2>Personal Identification</h2> -->

	<div class="left-wrapper pull-left">
		<ol class="breadcrumbs">
			<li><a href="/pos/"> &nbsp;<i class="fa fa-home"></i></a></li>
			<li><span>Accounts &nbsp;</span></li>
			<li><span>Stock Process &nbsp;</span></li>
		</ol>
	</div>
</header>



<!-- start: page -->

<div class="row">
	<div class="col-lg-12">

		<!-- message -->

		<c:if test="${!empty message}">
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

		<form class="form-horizontal form-bordered" method="post" autocomplete="off"
			action="/pos/accounts/processStock" id="form"
			onkeypress="return event.keyCode != 13;">
			<section class="panel panel-featured panel-featured-primary">
			
				<header class="panel-heading">
					<div class="panel-actions">
					</div>
					<h2 class="panel-title">Stock Process</h2>
				</header>

				<div class="panel-body">
					<div class="col-md-12">
					
					<div class="col-sm-6 form-group">
								<label class="col-md-4 col-sm-3 control-label"> 
									Date <span class="required">*</span> </label>
								<div class="col-md-8">
									<div class="input-group">
										<span class="input-group-addon"> <i
											class="fa fa-calendar"></i>
										</span> <input type="text" class="form-control" id="processDate" required
											onkeypress="goToNext(event,'contactNo')" name="processDate"
											placeholder="dd/mm/yyyy"
											value="${employeeMonthlyConsumption.consumeDate}"
											data-plugin-datepicker />
									</div>
								</div>
					</div>
					
					<div class="col-md-6 form-group">
						<div class="col-sm-5 col-sm-offset-0">
							<button class="mb-xs mt-xs mr-xs btn btn-xs btn-primary"
								type="submit">
								<i class="fa fa-retweet"></i> Process
							</button>
							<a href="/pos/accounts/stockProcessView">
								<button class="mb-xs mt-xs mr-xs btn btn-xs btn-default"
									type="button">
									<i class="fa fa-refresh"></i> Clear
								</button>
							</a>
						</div>
					</div>
					
					</div>
					
					<br />
					

				</div>
				
				<footer class="panel-footer">
					<div class="row">
						<div class="col-sm-offset-6"></div>
					</div>
				</footer>


			</section>
		</form>



	</div>
</div>

<script>

</script>


<!-- end: page -->