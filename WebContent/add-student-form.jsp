<%@ page import= "java.util.*, com.luv2code.web.jdbc.*" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<title>FooBAR University</title>
<link type= "text/css" rel="stylesheet" href= "css/style.css">
<link type= "text/css" rel="stylesheet" href= "css/add-student-style.css">

</head>


<body>

<div id= "wrapper">

<div id= "header">

	<h2>FooBar University</h2>

</div>

</div>

<div id="container">

<h2>Add Student</h2>

<form action= "StudentControllerServlet" method= "GET">


<input type= "hidden" name= "command" value= "Add">



<table>
	<tbody>
		<tr>
		
			<td><label>First name:</label></td>
			<td><input type= "text" name= "firstName"></td>
		</tr>
		<tr>
		
			<td><label>Last name:</label></td>
			<td><input type= "text" name= "lastName"></td>
		</tr>
		<tr>
		
			<td><label>Email:</label></td>
			<td><input type= "text" name= "email"></td>
		</tr>
		<tr>
		
			<td><input type= "submit" value= "Save" class="save"></td>
		</tr>

	</tbody>

</table>

</form>

<div style= "clear: both;">

<p>

	<a href= "StudentControllerServlet">Back To List</a>

</p>

</div>

</div>

</body>
</html>