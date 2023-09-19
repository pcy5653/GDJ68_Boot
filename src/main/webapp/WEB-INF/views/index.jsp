<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="./layout/header.jsp"></c:import>

</head>
<body id="page-top">
	<!-- Page Wrapper -->
	<div id="wrapper">
		<!-- sidebar -->
		<c:import url="./layout/sidebar.jsp"></c:import>
		
		<div id="content-wrapper" class="d-flex flex-column">
			<div id="content">
			<c:import url="./layout/topbar.jsp"></c:import>
			
			
			<c:import url="./layout/btnbar.jsp"></c:import>
			</div>
		</div>
	</div>

<c:import url="./layout/footer.jsp"></c:import>
</body>
</html>