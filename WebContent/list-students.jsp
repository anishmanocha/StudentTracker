<%@ page import= "java.util.*, com.luv2code.web.jdbc.*" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<title>FooBAR University</title>

<link type= "text/css" rel="stylesheet" href= "css/style.css">
</head>


<body>

<div id= "wrapper">

<div id= "header">

	<h2>FooBar University</h2>

</div>

<div id="container">

<div id= "content">
<input type= "button" value= "Add Student" onclick= "window.location.href= 'add-student-form.jsp'; return false;" class="add-student-button"></div>
<table>

<tr>

<th>First Name</th>
<th>Last Name</th>
<th>Email</th>
<th>Action</th>

</tr>

<c:forEach var= "student" items= "${listOfStudents}">

	<c:url var= "templink" value= "StudentControllerServlet">
		<c:param name= "command" value="Load" />
		<c:param name= "studentId" value= "${student.id}" />
	</c:url>
	
	<c:url var= "deletelink" value= "StudentControllerServlet">
		<c:param name= "command" value="Delete" />
		<c:param name= "studentId" value= "${student.id}" />
	</c:url>


	<tr>
	<td> ${student.firstName} </td>
	<td>${student.lastName}</td>
	<td>${student.email}</td>
	<td><a href="${templink}">Update</a><span> | </span><a href="${deletelink}">Delete</a></td>
	</tr>

</c:forEach>



</div>
</div>
</table>
</div>
</body>
</html>