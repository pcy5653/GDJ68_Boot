<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="/WEB-INF/views/layout/header.jsp"></c:import>

</head>
<body id="page-top">
<div id="wrapper">
      <!-- sidebar -->
      <c:import url="/WEB-INF/views/layout/sidebar.jsp"></c:import>
      
      <div id="content-wrapper" class="d-flex flex-column">
         <div id="content">
	         <c:import url="/WEB-INF/views/layout/topbar.jsp"></c:import>
	         
	         <div class="container-fluid">
	         	<!-- page 상세내용 -->
	         	
	         	
	         	<!-- topbar > profile --> -->
	         	<sec:authentication property="principal" var="member" /> 
                <span>ID : ${member.username}</span> <br>
                <span>PW : ${member.password}</span><br>
                <span>EMAIL : ${member.email}</span><br>
                <span>BIRTH : ${member.birth}</span><br>
                <span>NAME : ${member.name}</span>
	         	
	         </div>
         
         </div>
         <c:import url="/WEB-INF/views/layout/btnbar.jsp"></c:import>
      </div>
   </div>
   
<c:import url="/WEB-INF/views/layout/footer.jsp"></c:import>
</body>
</html>