<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 

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
	         	<div>
	         		<!-- login 실패시 message가 뜸 -->
	         		<h3>${param.message}</h3>
	         	</div>
	         	
	         	
               <!-- action 안쓰면 url 그대로 주소 사용 /member/join -->
               <!-- prefix="form" 사용 -->
               <!-- ^^modelAttribute^^ : Controller에서 보내는 키이름 -->
               <form:form modelAttribute="memberVO" method="post">
                  <div class="form-group">
                  	 <form:label path="username">Username</form:label>
                     <!-- path : MemberVO의 setter이름, name(파라미터)사용 X -->
                     <form:input id="username" path="username" cssClass="form-control"/>
                     <!-- error message -->
                     <form:errors path="username"></form:errors>
                  </div>
                  
                  <div class="form-group">
                  	<form:label path="password">Password</form:label>
                  	<form:password id="password" path="password" cssClass="form-control"/>
                     <!-- error message -->
                     <form:errors path="password"></form:errors>
                  </div>

                  <button type="submit" class="btn btn-primary">Submit</button>
               
               </form:form>
	         </div>
         
         </div>
         <c:import url="/WEB-INF/views/layout/btnbar.jsp"></c:import>
      </div>
   </div>
   
<c:import url="/WEB-INF/views/layout/footer.jsp"></c:import>

<script type="text/javascript">
	/* 로그인 실패 시, 파라미터로 받아온 message를 띄움. */
	let message = '${param.message}';
	
	if(message != ""){
		alert('${param.message}');
	}
	
</script>
</body>
</html>