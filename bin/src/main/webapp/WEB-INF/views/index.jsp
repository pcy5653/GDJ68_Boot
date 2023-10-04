<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<!-- JSP에서 properties의 메세지를 사용할 수 있도록 하는 API -->
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>   

<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="/WEB-INF/views/layout/header.jsp"></c:import>

</head>
<body id="page-top">
    <!-- Page Wrapper -->
    <div id="wrapper">
       <!-- sidebar -->
       <c:import url="/WEB-INF/views/layout/sidebar.jsp"></c:import>
       
        <div id="content-wrapper" class="d-flex flex-column">
           <div id="content">
              
              <c:import url="/WEB-INF/views/layout/topbar.jsp"></c:import>
              
              <div class="container-fluid">
                 <!-- page 실제 내용 -->
                 <h1>Welcome : <spring:message code="hello"></spring:message></h1>
                 <h1><spring:message code="hi"></spring:message></h1>
                 
                 <!-- 로그인 성공시에만 <h1> 출력 -->
                 <sec:authorize access="isAuthenticated()">
                 	<!-- user의 정보를 가져오는데 name 속성에서 arguments에 값을 넣기 위해 var에 name키의 값을 넣어서 arguments에 var의 값을 넣는다.-->
                 	<sec:authentication property="name" var="username" /> 
                 	<h1><spring:message code="login.welcome" arguments="${username}"></spring:message></h1>
              	 </sec:authorize>
              </div>
           
           </div>
           
           <c:import url="/WEB-INF/views/layout/btnbar.jsp"></c:import>
        
        </div>
       
    </div>
    
    

<c:import url="/WEB-INF/views/layout/footer.jsp"></c:import>
</body>
</html>