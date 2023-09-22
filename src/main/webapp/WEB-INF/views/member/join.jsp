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
	         	
               <!-- action 안쓰면 url 그대로 주소 사용 /member/join -->
               <!-- prefix="form" 사용 -->
               <!-- ^^modelAttribute^^ : Controller에서 보내는 키이름 -->
               <form:form modelAttribute="memberVO" method="post" enctype="multipart/form-data">
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
                  
                  <div class="form-group">
                  	<form:label path="passwordCheck">PasswordCheck</form:label>
                  	<form:password id="passwordCheck" path="passwordCheck" cssClass="form-control"/>
                  </div>
                  
                  <div class="form-group">
                  	 <form:label path="name">Name</form:label>
                     <form:input id="name" path="name" cssClass="form-control"/>
                     <!-- error message -->
                     <form:errors path="name"></form:errors>
                  </div>
                  
                  <div class="form-group">
                  	 <form:label path="email">Email</form:label>
                     <form:input id="email" path="email" cssClass="form-control"/>
                     <!-- error message -->
                     <form:errors path="email"></form:errors>
                  </div>
                  
                  <div class="form-group">
                  	 <form:label path="birth">Birth</form:label>
                     <form:input id="birth" path="birth" cssClass="form-control"/>
                     <!-- error message -->
                     <form:errors path="birth"></form:errors>
                  </div>
                  
                  <div class="form-group">
                     <label for="photo">Photo</label>
                     <input type="file" name="photo" class="form-control" id="photo" aria-describedby="photoHelp">
                     <small id="photoHelp" class="form-text text-muted">본인 사진을 넣으세요!</small>
                  </div>
                  
                  <button type="submit" class="btn btn-primary">Submit</button>
               
               </form:form>
	         </div>
         
         </div>
         <c:import url="/WEB-INF/views/layout/btnbar.jsp"></c:import>
      </div>
   </div>
   
<c:import url="/WEB-INF/views/layout/footer.jsp"></c:import>
</body>
</html>