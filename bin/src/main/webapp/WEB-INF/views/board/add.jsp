<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
               <div class="row">
                  <form action="add" method="post" enctype="multipart/form-data">
                  	 
                  	 <!-- CSRF Token 적용 : 웹 구조의 보안을 강화하고 사용자 데이터 및 조치를 안전하게 보호 -->
                  	 <sec:csrfInput/>	
                     <div class="mb-3">
                        <label for="boardTitle" class="form-label">Title</label>
                        <input type="text" class="form-control" name="boardTitle" id="boardTitle" placeholder="제목을 입력하세요.">
                     </div>
                     <div class="mb-3">
                        <label for="boardWriter" class="form-label">Writer</label>
                        <input type="text" class="form-control" name="boardWriter" id="boardWriter">
                     </div>
                     <div class="mb-3">
                        <label for="boardContents" class="form-label">Contents</label>
                        <textarea class="form-control" name="boardContents" id="boardContents" rows="3"></textarea>
                     </div>

                     <div class="mb-3">
                        <input type="file" name="files" class="form-control">
                     </div>
                     <div class="mb-3">
                        <input type="file" name="files" class="form-control">
                     </div>

                     <button class="btn btn-danger">ADD</button>
                  </form>
               </div>
	         </div>
         
         </div>
         <c:import url="/WEB-INF/views/layout/btnbar.jsp"></c:import>
      </div>
   </div>
   
<c:import url="/WEB-INF/views/layout/footer.jsp"></c:import>
</body>
</html>