<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  
    "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="bn" lang="bn">
<head>

<!-- META SECTION -->
		<title><tiles:insertAttribute name="title" ignore="true" /></title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        
        <link rel="icon" href="<c:url value="/resource/mytheme/img/logo-grey@2x.png"/>" type="image/x-icon" />
        
        
        <!-- END META SECTION -->



<!-- CSS INCLUDE -->        
        <link rel="stylesheet" href="<c:url value="/resource/mytheme/css/theme-default.css" />">


		
<%-- <!-- CSS INCLUDE --> 

<link rel="stylesheet"
	href="<c:url value="/resource/mytheme/css/bootstrap-responsive.min.css" />">
<link rel="stylesheet"
	href="<c:url value="/resource/mytheme/css/bootstrap.min.css" />">
<link rel="stylesheet"
	href="<c:url value="/resource/mytheme/css/jquery.gritter.css" />">
<link rel="stylesheet"
	href="<c:url value="/resource/mytheme/css/style.css" />">
<link rel="stylesheet"
	href="<c:url value="/resource/mytheme/css/datepicker.css" />">



<script src="<c:url value="/resource/mytheme/js/jquery.min.js" />"></script>
<script
	src="<c:url value="/resource/mytheme/js/jquery.easing.min.js" />"></script>
<script src="<c:url value="/resource/mytheme/js/bootstrap.min.js" />"></script>
<script
	src="<c:url value="/resource/mytheme/js/jquery.nicescroll.min.js" />"></script>
<script
	src="<c:url value="/resource/mytheme/js/jquery.gritter.min.js" />"></script>

<script
	src="<c:url value="/resource/mytheme/js/bootstrap-datepicker.min.js" />"></script>

<script
	src="<c:url value="/resource/mytheme/js/additional-methods.min.js" />"></script>
<script src="<c:url value="/resource/mytheme/js/application.min.js" />"></script>

<script
	src="<c:url value="/resource/mytheme/js/demonstration.min.js" />"></script>
<script src="<c:url value="/resource/mytheme/js/eakroko.min.js" />"></script>
<script
	src="<c:url value="/resource/mytheme/js/jquery.maskedinput.min.js" />"></script>




<script src="<c:url value="/resource/mytheme/js/jquery.form.min.js" />"></script>



<script src="<c:url value="/resource/mytheme/js/jquery.validate.min.js" />"></script>
	

<script src="<c:url value="/resource/mytheme/js/sortable.js" />"></script>


 --%>


</head>

<tiles:insertAttribute name="body" />


</html>
