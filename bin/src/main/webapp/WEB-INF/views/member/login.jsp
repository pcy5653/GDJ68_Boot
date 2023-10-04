<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

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
	         		<!-- login 실패시 message가 뜸. code=키이름 var=키값을 담는 곳 -->
	         		<h3>${param.message}</h3>
	         		<spring:message code="${param.message}" var="msg"></spring:message>
	         		<h3>${msg}</h3>
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
                  
                  <!-- Form 에서 checkbox remember-me를 체크 하면 다음 접속시 로그인 없이 바로 인증 -->
                  <div class="form-group">
                  	<label for="remember">Remember Me</label>
                  	<input type="checkbox" name="remember-me" id="remember" class="form-control">
                  </div>

                  <button type="submit" class="btn btn-primary">Submit</button>
               
               </form:form>

               <!-- 카카오 API : URL 주소는 변경 불가 -->
               <a href="/oauth2/authorization/kakao">KakaoLogin</a>
	         </div>
         
         </div>
         <c:import url="/WEB-INF/views/layout/btnbar.jsp"></c:import>
      </div>
   </div>
   
<c:import url="/WEB-INF/views/layout/footer.jsp"></c:import>

<script type="text/javascript">
	/* 로그인 실패 시, 파라미터로 받아온 message를 띄움. 
	   let message = '${param.message}'; => 키값을 ${msg}에 담았기 때문에 값을 ${msg}라고 생각.
	*/
	let message = '${msg}';
	
	if(message != ""){
		alert(message);
	}
	
	// redirect 로 보냈을 때, URL 경로나타내는 것을 삭제.
	// 현재는 alert 창이 나타나고 그것을 클릭해야 URL 삭제
	history.replaceState({}, null, location.pathname);
	
</script>
</body>
</html>