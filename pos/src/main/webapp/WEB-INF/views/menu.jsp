<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.pos.login.model.MenuInfo"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.List"%>


<!-- START PAGE SIDEBAR -->

<div class="sidebar-header">
	<!-- <div class="sidebar-title">
							Navigation
						</div> -->
	<div class="sidebar-toggle hidden-xs"
		data-toggle-class="sidebar-left-collapsed" data-target="html"
		data-fire-event="sidebar-left-toggle">
		<i class="fa fa-bars" aria-label="Toggle sidebar"></i>
	</div>
</div>

<div class="nano">
	<div class="nano-content">
		<nav id="menu" class="nav-main" role="navigation">
			<ul class="nav nav-main">
				<li
					class="nav-parent<%if (request.getAttribute("javax.servlet.forward.request_uri").equals("/pos/validateUser")
					|| request.getAttribute("javax.servlet.forward.request_uri").equals("/pos/")
					|| request.getAttribute("javax.servlet.forward.request_uri").equals("/pos/login")) {
				out.print(" nav-expanded nav-active");
			}%>">
					<a href="/pos/"> <i class="fa fa-home" aria-hidden="true"></i>
						<span>Dashboard</span>
				</a>
				</li>
				
				<!-- start employee management -->

				<%
					List<MenuInfo> sEmployeeManagementMenuInfo = (List<MenuInfo>) session.getAttribute("sEmployeeManagement");
					if (!sEmployeeManagementMenuInfo.isEmpty()) {
				%>

				<li
					class="nav-parent<%if (request.getAttribute("javax.servlet.forward.request_uri").equals("/pos/hr/employeeInfoView")
							|| request.getAttribute("javax.servlet.forward.request_uri").equals("/pos/hr/employeeInfo")
							|| request.getAttribute("javax.servlet.forward.request_uri").equals("/pos/pointOfSale/orderView")
						|| request.getAttribute("javax.servlet.forward.request_uri").equals("/pos/pointOfSale/orderFinalize")) {
					out.print(" nav-expanded nav-active");
				}%>">
					<a> <i class="fa fa-users" aria-hidden="true"></i> <span>Employee
							Management</span>
				</a>
					<ul class="nav nav-children">

						<%
							Iterator it = sEmployeeManagementMenuInfo.iterator();
								while (it.hasNext()) {
									MenuInfo sEmployeeManagementMenuInfoList = (MenuInfo) it.next();
						%>

						<li
							class="<%if (request.getAttribute("javax.servlet.forward.request_uri")
							.equals(sEmployeeManagementMenuInfoList.getMenuActionName())) {
						out.print("nav-active");
					}%>">
							<a href=<%=sEmployeeManagementMenuInfoList.getMenuActionName()%>><i
								class="" aria-hidden="true"></i> <%=sEmployeeManagementMenuInfoList.getMenuName()%></a>
						</li>

						<%
							}
						%>

					</ul>


				</li>
				<%
					}
				%>

				<!-- end employee management -->

				<!-- start inventory -->

				<%
					List<MenuInfo> sInventoryMenuInfo = (List<MenuInfo>) session.getAttribute("sInventory");
					if (!sInventoryMenuInfo.isEmpty()) {
				%>

				<li
					class="nav-parent<%if (request.getAttribute("javax.servlet.forward.request_uri").equals("/pos/inventory/createIngredients")
						|| request.getAttribute("javax.servlet.forward.request_uri")
								.equals("/pos/inventory/ingredientsView")
						|| request.getAttribute("javax.servlet.forward.request_uri")
								.equals("/pos/inventory/storeIngredients")
						|| request.getAttribute("javax.servlet.forward.request_uri")
								.equals("/pos/inventory/otherInventory")
						|| request.getAttribute("javax.servlet.forward.request_uri")
								.equals("/pos/inventory/wastageView")
						|| request.getAttribute("javax.servlet.forward.request_uri")
								.equals("/pos/inventory/wastage")
						|| request.getAttribute("javax.servlet.forward.request_uri")
								.equals("/pos/inventory/storeManagement")
						|| request.getAttribute("javax.servlet.forward.request_uri")
								.equals("/pos/inventory/wastage")
						|| request.getAttribute("javax.servlet.forward.request_uri")
								.equals("/pos/inventory/kitchenManagementView")
						|| request.getAttribute("javax.servlet.forward.request_uri")
								.equals("/pos/inventory/kitchenManagement")) {
					out.print(" nav-expanded nav-active");
				}%>">
					<a> <i class="fa fa-book" aria-hidden="true"></i> <span>Inventory</span>
				</a>
					<ul class="nav nav-children">

						<%
							Iterator it = sInventoryMenuInfo.iterator();
								while (it.hasNext()) {
									MenuInfo sInventoryMenuInfoList = (MenuInfo) it.next();
						%>

						<li
							class="<%if (request.getAttribute("javax.servlet.forward.request_uri")
							.equals(sInventoryMenuInfoList.getMenuActionName())) {
						out.print("nav-active");
					}%>">
							<a href=<%=sInventoryMenuInfoList.getMenuActionName()%>><i
								class="" aria-hidden="true"></i> <%=sInventoryMenuInfoList.getMenuName()%></a>
						</li>

						<%
							}
						%>

					</ul>


				</li>
				<%
					}
				%>

				<!-- end inventory -->



				<!-- start pos -->

				<%
					List<MenuInfo> sPointOfSaleMenuInfo = (List<MenuInfo>) session.getAttribute("sPointOfSale");
					if (!sPointOfSaleMenuInfo.isEmpty()) {
				%>

				<li
					class="nav-parent<%if (request.getAttribute("javax.servlet.forward.request_uri").equals("/pos/pointOfSale/createItem")
						|| request.getAttribute("javax.servlet.forward.request_uri")
								.equals("/pos/pointOfSale/itemConfiguration")
						|| request.getAttribute("javax.servlet.forward.request_uri")
								.equals("/pos/pointOfSale/orderManagement")
						|| request.getAttribute("javax.servlet.forward.request_uri")
								.equals("/pos/pointOfSale/discount")
						|| request.getAttribute("javax.servlet.forward.request_uri")
								.equals("/pos/pointOfSale/discountView")
						|| request.getAttribute("javax.servlet.forward.request_uri")
								.equals("/pos/pointOfSale/dueCustomer")) {
					out.print(" nav-expanded nav-active");
				}%>">
					<a> <i class="fa fa-check-circle" aria-hidden="true"></i> <span>Point
							Of Sale</span>
				</a>
					<ul class="nav nav-children">

						<%
							Iterator it = sPointOfSaleMenuInfo.iterator();
								while (it.hasNext()) {
									MenuInfo sPointOfSaleMenuInfoList = (MenuInfo) it.next();
						%>

						<li
							class="<%if (request.getAttribute("javax.servlet.forward.request_uri")
							.equals(sPointOfSaleMenuInfoList.getMenuActionName())) {
						out.print("nav-active");
					}%>">
							<a href=<%=sPointOfSaleMenuInfoList.getMenuActionName()%>><i
								class="" aria-hidden="true"></i> <%=sPointOfSaleMenuInfoList.getMenuName()%></a>
						</li>

						<%
							}
						%>

					</ul>


				</li>
				<%
					}
				%>

				<!-- end pos -->


				<!-- start user management -->

				<%
					List<MenuInfo> sUserManagementMenuInfo = (List<MenuInfo>) session.getAttribute("sUserManagement");
					if (!sUserManagementMenuInfo.isEmpty()) {
				%>

				<li
					class="nav-parent<%if (request.getAttribute("javax.servlet.forward.request_uri").equals("/pos/admin/roleInfoView")
						|| request.getAttribute("javax.servlet.forward.request_uri")
								.equals("/pos/admin/roleInfo")
						|| request.getAttribute("javax.servlet.forward.request_uri")
								.equals("/pos/admin/roleMenuMappingView")
						|| request.getAttribute("javax.servlet.forward.request_uri")
								.equals("/pos/admin/roleMenuMapping")
						|| request.getAttribute("javax.servlet.forward.request_uri")
								.equals("/pos/admin/userInfoView")
						|| request.getAttribute("javax.servlet.forward.request_uri")
								.equals("/pos/admin/userInfo")) {
					out.print(" nav-expanded nav-active");
				}%>">
					<a> <i class="fa fa-user" aria-hidden="true"></i> <span>User
							Management</span>
				</a>
					<ul class="nav nav-children">

						<%
							Iterator it = sUserManagementMenuInfo.iterator();
								while (it.hasNext()) {
									MenuInfo sUserManagementMenuInfoList = (MenuInfo) it.next();
						%>

						<li
							class="<%if (request.getAttribute("javax.servlet.forward.request_uri")
							.equals(sUserManagementMenuInfoList.getMenuActionName())) {
						out.print("nav-active");
					}%>">
							<a href=<%=sUserManagementMenuInfoList.getMenuActionName()%>><i
								class="" aria-hidden="true"></i> <%=sUserManagementMenuInfoList.getMenuName()%></a>
						</li>

						<%
							}
						%>

					</ul>


				</li>
				<%
					}
				%>

				<!-- end user management -->

				

				<!-- start venue reservation -->

				<%
					List<MenuInfo> sVenueReservationMenuInfo = (List<MenuInfo>) session.getAttribute("sVenueReservation");
					if (!sVenueReservationMenuInfo.isEmpty()) {
				%>

				<li
					class="nav-parent<%if (request.getAttribute("javax.servlet.forward.request_uri").equals("/pos/venueReservation/reserveVenueView")
						|| request.getAttribute("javax.servlet.forward.request_uri")
								.equals("/pos/venueReservation/reserveVenue")
						|| request.getAttribute("javax.servlet.forward.request_uri")
								.equals("/pos/pointOfSale/orderFinalize")) {
					out.print(" nav-expanded nav-active");
				}%>">
					<a> <i class="fa fa-building-o" aria-hidden="true"></i> <span>Venue
							Reservation</span>
				</a>
					<ul class="nav nav-children">

						<%
							Iterator it = sVenueReservationMenuInfo.iterator();
								while (it.hasNext()) {
									MenuInfo sVenueReservationMenuInfoList = (MenuInfo) it.next();
						%>

						<li
							class="<%if (request.getAttribute("javax.servlet.forward.request_uri")
							.equals(sVenueReservationMenuInfoList.getMenuActionName())) {
						out.print("nav-active");
					}%>">
							<a href=<%=sVenueReservationMenuInfoList.getMenuActionName()%>><i
								class="" aria-hidden="true"></i> <%=sVenueReservationMenuInfoList.getMenuName()%></a>
						</li>

						<%
							}
						%>

					</ul>


				</li>
				<%
					}
				%>

				<!-- end venue reservation -->


				<!-- start program management -->

				<%
					List<MenuInfo> sProgramManagementMenuInfo = (List<MenuInfo>) session.getAttribute("sProgramManagement");
					if (!sProgramManagementMenuInfo.isEmpty()) {
				%>

				<li
					class="nav-parent<%if (request.getAttribute("javax.servlet.forward.request_uri").equals("/pos/pointOfSale/createOrder")
						|| request.getAttribute("javax.servlet.forward.request_uri")
								.equals("/pos/pointOfSale/orderView")
						|| request.getAttribute("javax.servlet.forward.request_uri")
								.equals("/pos/pointOfSale/orderFinalize")) {
					out.print(" nav-expanded nav-active");
				}%>">
					<a> <i class="fa fa-cubes" aria-hidden="true"></i> <span>Program
							Management</span>
				</a>
					<ul class="nav nav-children">

						<%
							Iterator it = sProgramManagementMenuInfo.iterator();
								while (it.hasNext()) {
									MenuInfo sProgramManagementMenuInfoList = (MenuInfo) it.next();
						%>

						<li
							class="<%if (request.getAttribute("javax.servlet.forward.request_uri")
							.equals(sProgramManagementMenuInfoList.getMenuActionName())) {
						out.print("nav-active");
					}%>">
							<a href=<%=sProgramManagementMenuInfoList.getMenuActionName()%>><i
								class="" aria-hidden="true"></i> <%=sProgramManagementMenuInfoList.getMenuName()%></a>
						</li>

						<%
							}
						%>

					</ul>


				</li>
				<%
					}
				%>

				<!-- end program management -->

				<!-- start membership -->

				<%
					List<MenuInfo> sMembershipMenuInfo = (List<MenuInfo>) session.getAttribute("sMembership");
					if (!sMembershipMenuInfo.isEmpty()) {
				%>

				<li
					class="nav-parent<%if (request.getAttribute("javax.servlet.forward.request_uri").equals("/pos/membership/addMember")
						|| request.getAttribute("javax.servlet.forward.request_uri")
								.equals("/pos/membership/generateCard")
						|| request.getAttribute("javax.servlet.forward.request_uri")
								.equals("/pos/membership/accountStatus")) {
					out.print(" nav-expanded nav-active");
				}%>">
					<a> <i class="fa fa-thumbs-o-up" aria-hidden="true"></i> <span>Membership</span>
				</a>
					<ul class="nav nav-children">

						<%
							Iterator it = sMembershipMenuInfo.iterator();
								while (it.hasNext()) {
									MenuInfo sMembershipMenuInfoList = (MenuInfo) it.next();
						%>

						<li
							class="<%if (request.getAttribute("javax.servlet.forward.request_uri")
							.equals(sMembershipMenuInfoList.getMenuActionName())) {
						out.print("nav-active");
					}%>">
							<a href=<%=sMembershipMenuInfoList.getMenuActionName()%>><i
								class="" aria-hidden="true"></i> <%=sMembershipMenuInfoList.getMenuName()%></a>
						</li>

						<%
							}
						%>

					</ul>


				</li>
				<%
					}
				%>

				<!-- end membership -->

				<!-- start program management -->

				<%
					List<MenuInfo> sKidsZoneMenuInfo = (List<MenuInfo>) session.getAttribute("sKidsZone");
					if (!sKidsZoneMenuInfo.isEmpty()) {
				%>

				<li
					class="nav-parent<%if (request.getAttribute("javax.servlet.forward.request_uri").equals("/pos/pointOfSale/createOrder")
						|| request.getAttribute("javax.servlet.forward.request_uri")
								.equals("/pos/pointOfSale/orderView")
						|| request.getAttribute("javax.servlet.forward.request_uri")
								.equals("/pos/pointOfSale/orderFinalize")) {
					out.print(" nav-expanded nav-active");
				}%>">
					<a> <i class="fa fa-cab" aria-hidden="true"></i> <span>Kids
							Zone</span>
				</a>
					<ul class="nav nav-children">

						<%
							Iterator it = sKidsZoneMenuInfo.iterator();
								while (it.hasNext()) {
									MenuInfo sKidsZoneMenuInfoList = (MenuInfo) it.next();
						%>

						<li
							class="<%if (request.getAttribute("javax.servlet.forward.request_uri")
							.equals(sKidsZoneMenuInfoList.getMenuActionName())) {
						out.print("nav-active");
					}%>">
							<a href=<%=sKidsZoneMenuInfoList.getMenuActionName()%>><i
								class="" aria-hidden="true"></i> <%=sKidsZoneMenuInfoList.getMenuName()%></a>
						</li>

						<%
							}
						%>

					</ul>


				</li>
				<%
					}
				%>

				<!-- end program management -->
				
				<!-- start reports -->

				<%
					List<MenuInfo> sReportsMenuInfo = (List<MenuInfo>) session.getAttribute("sReports");
					if (!sReportsMenuInfo.isEmpty()) {
				%>

				<li
					class="nav-parent<%if (request.getAttribute("javax.servlet.forward.request_uri").equals("/pos/reports/posReport")
						|| request.getAttribute("javax.servlet.forward.request_uri")
								.equals("/pos/reports/inventoryReport")
						|| request.getAttribute("javax.servlet.forward.request_uri")
								.equals("/pos/reports/employeeReport")
								|| request.getAttribute("javax.servlet.forward.request_uri")
								.equals("/pos/reports/costAnalysisReport")
								|| request.getAttribute("javax.servlet.forward.request_uri")
								.equals("/pos/reports/payrollReport")
								|| request.getAttribute("javax.servlet.forward.request_uri")
								.equals("/pos/reports/stockSummaryReport")) {
					out.print(" nav-expanded nav-active");
				}%>">
					<a> <i class="fa fa-file-pdf-o" aria-hidden="true"></i> <span>Reports</span>
				</a>
					<ul class="nav nav-children">

						<%
							Iterator it = sReportsMenuInfo.iterator();
								while (it.hasNext()) {
									MenuInfo sReportsMenuInfoList = (MenuInfo) it.next();
						%>

						<li
							class="<%if (request.getAttribute("javax.servlet.forward.request_uri")
							.equals(sReportsMenuInfoList.getMenuActionName())) {
						out.print("nav-active");
					}%>">
							<a href=<%=sReportsMenuInfoList.getMenuActionName()%>><i
								class="" aria-hidden="true"></i> <%=sReportsMenuInfoList.getMenuName()%></a>
						</li>

						<%
							}
						%>

					</ul>


				</li>
				<%
					}
				%>

				<!-- end reports -->
				
				
					<!-- start Accounts -->

				<%
					List<MenuInfo> sAccountsMenuInfo = (List<MenuInfo>) session.getAttribute("sAccounts");
					if (!sAccountsMenuInfo.isEmpty()) {
				%>

				<li
					class="nav-parent<%if (request.getAttribute("javax.servlet.forward.request_uri").equals("/pos/accounts/empMonthlyConsumption")
						|| request.getAttribute("javax.servlet.forward.request_uri")
								.equals("/pos/accounts/salaryProcess")
						|| request.getAttribute("javax.servlet.forward.request_uri")
								.equals("/pos/accounts/ownerConsumptionInfo") 
						|| request.getAttribute("javax.servlet.forward.request_uri")
								.equals("/pos/accounts/ownerConsumptionHistory") 
						|| request.getAttribute("javax.servlet.forward.request_uri")
								.equals("/pos/accounts/supplierInfo")
						|| request.getAttribute("javax.servlet.forward.request_uri")
								.equals("/pos/accounts/supplierInfoView")
						|| request.getAttribute("javax.servlet.forward.request_uri")
								.equals("/pos/accounts/stockProcessView")) {
					out.print(" nav-expanded nav-active");
				}%>">
					<a> <i class="fa fa-cubes" aria-hidden="true"></i> <span>Accounts</span>
				</a>
					<ul class="nav nav-children">

						<%
							Iterator it = sAccountsMenuInfo.iterator();
								while (it.hasNext()) {
									MenuInfo sAccountsMenuInfoList = (MenuInfo) it.next();
						%>

						<li
							class="<%if (request.getAttribute("javax.servlet.forward.request_uri")
							.equals(sAccountsMenuInfoList.getMenuActionName())) {
						out.print("nav-active");
					}%>">
							<a href=<%=sAccountsMenuInfoList.getMenuActionName()%>><i
								class="" aria-hidden="true"></i> <%=sAccountsMenuInfoList.getMenuName()%></a>
						</li>

						<%
							}
						%>

					</ul>


				</li>
				<%
					}
				%>

				<!-- end Accounts -->
				
				
				
				
				

			</ul>
		</nav>
	</div>
</div>