<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <!-- REFERENCE THE JSTL TAG LIB -->
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
 
 <!-- BOOTSTRAP CDN -->
<link href="https://unpkg.com/bootstrap@4.1.0/dist/css/bootstrap.min.css" rel="stylesheet"/>
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.min.css"/>
 
<body>

<%

String email = (String)session.getAttribute("email");
if(email== null){
	response.sendRedirect("index.jsp");
}
%>

<div class="container">
<div class="float-right">
 <a href="${pageContext.request.contextPath}/logout.jsp">Logout</a>
 </div>
<p>${message}</p>
<button class="btn btn-primary" onclick="window.location.href='Views/employee-add.jsp'">Add Employee</button><br>
 <table border="1" class="table table-striped table-bordered" id="datatable">
    <thead>
    <tr class="thead-dark">
       <th>Name</th>
       <th>Department</th> 
       <th>Date Of birth</th>
       <th>Actions</th>
       
   </tr>
    </thead>
   <tbody>
   <!--LOOP OVER THE LIST -->
   <c:forEach items = "${list}" var = "employee">
         <tr>
              
             <td>${employee.name}</td>
             <td>${employee.department}</td>
             <td>${employee.dob}</td>
             <td>
                <a href="${pageContext.request.contextPath}/EmployeeController?action=EDIT&id=${employee.id}"> Edit</a>
                |
                <a href="${pageContext.request.contextPath}/EmployeeController?action=DELETE&id=${employee.id}"> Delete</a>
             </td>
              
                
             
         </tr>
   </c:forEach>
   </tbody>
 </table>
 </div>
 
 <script src ="https://unpkg.com/jquery@3.5.1/dist/jquery.js"></script>
 <script type="text/javascript" src="https://cdn.datatables.net/v/bs4/dt-1.10.21/datatables.min.js"></script>
 <script>
       $(document).ready(function() {
	    $('#datatable').DataTable();
	} );
 </script>
</body>
</html>