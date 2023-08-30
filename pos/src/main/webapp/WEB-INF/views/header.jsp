<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


				<div class="logo-container">
					<%-- <a href="/pos/" class="logo">
							<img src="<c:url value="/resources/images/tera_final_logo2.png" />" height="50" alt="Teracotta" style="margin-top:-10px"/>
					</a> --%>
					
					<div class="col-xs-4 hidden-xs">
						
						<%-- <img src="<c:url value="/resources/images/BRTA-banner.png" />" width="100%" alt="BRTA" style="margin-top:14px;margin-left:25px"/> --%>
					</div>
					<div class="visible-xs toggle-sidebar-left" data-toggle-class="sidebar-left-opened" data-target="html" data-fire-event="sidebar-left-opened">
						<i class="fa fa-bars" aria-label="Toggle sidebar"></i>
					</div>
				</div>
			
				<!-- start: search & user box -->
				<div class="header-right">
				
				<span class="separator"></span>
				
						
					<!-- <span class="separator"></span> -->
			
					<div id="userbox" class="userbox" style="float:left;">
						<a href="#" data-toggle="dropdown">
							
							<div class="profile-info">
								<span class="name"><%out.print(session.getAttribute("username")); %></span>
								<span class="role"><%out.print(session.getAttribute("designationName")); %></span>
								<%-- <span class="role"><%out.print(session.getAttribute("branchName")); %></span> --%>
							</div>
			
							<i class="fa custom-caret"></i>
						</a>
			
						<div class="dropdown-menu">
							<ul class="list-unstyled">
								<li class="divider"></li>
								<!-- <li>
									<a role="menuitem" tabindex="-1" href="pages-user-profile.html"><i class="fa fa-user"></i> My Profile</a>
								</li> -->
								<li>
									<a role="menuitem" tabindex="-1" href="/pos/user/changePassword"><i class="fa fa-refresh"></i> Password Change</a>
								</li>
								<li>
									<a role="menuitem" tabindex="-1" href="/pos/logout.html"><i class="fa fa-power-off"></i> Logout</a>
								</li>
							</ul>
						</div>
					</div>
				</div>