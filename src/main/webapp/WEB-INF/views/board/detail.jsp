<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                  <table>
                     <thead>
                        <tr>
                           <th>Title</th>
                           <th>Writer</th>
                           <th>Contents</th>
                        </tr>
                     </thead>
                     <tbody>
                        <tr>
                           <td>${detail.boardTitle}</td>
                           <td>${detail.boardWriter}</td>
                           <td>${detail.boardContents}</td>
                        </tr>
                     </tbody>
                  </table>
               </div>

               <!-- file -->
               <div class="row">
                  <c:forEach items="${detail.list}" var="f">
                     <img src="../files/${board}/${f.fileName}" alt="">
                     <a href="./fileDown?fileNo=${f.fileNo}">${f.originalName}</a>
                  </c:forEach>
               </div>

               <div class="col-sm-12 col-md-5">
                  <div class="dataTables_info"id="dataTable_info"role="status"aria-live="polite">
                     <a href="./add" class="btn btn-primary btn-icon-split">
                           <span class="icon text-white-50">
                              <i class="fas fa-flag"></i>
                           </span>
                           <span class="text">글수정</span>
                     </a>
                     <a href="./add" class="btn btn-primary btn-icon-split">
                        <span class="icon text-white-50">
                           <i class="fas fa-flag"></i>
                        </span>
                        <span class="text">글삭제</span>
                  </a>
                  </div>
              </div>
	         </div>
         
         </div>
         <c:import url="/WEB-INF/views/layout/btnbar.jsp"></c:import>
      </div>
   </div>
   
<c:import url="/WEB-INF/views/layout/footer.jsp"></c:import>
</body>
</html>