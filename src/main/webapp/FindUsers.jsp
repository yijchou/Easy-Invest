<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- Bootstrap -->
<link href="bootstrap.min.css" rel="stylesheet">
<title>Find a User</title>
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">Easy Invest</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarColor01" aria-controls="navbarColor01" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarColor01">
      <ul class="navbar-nav me-auto">
        <li class="nav-item">
          <a class="nav-link active" href="LandingPage.jsp">Home
            <span class="visually-hidden">landingPage</span>
          </a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="findusers">Users</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="findstrategyposts">Strategy Post</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="findStocks">Stock Recommendations</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="materialfind">Educational Materials</a>
        </li>
                <li class="nav-item">
          <a class="nav-link" href="#"></a>
        </li>
      </ul>
    </div>
  </div>
</nav>
</head>
<body>
	<div class="container" style="text-align:center">
	<form action="findusers" method="get">
	<br>
		<h1><center>Search User by First Name</center></h1>
		<p>
			<center>
			<div class="form-group">
  				<label class="col-form-label col-form-label-lg mt-4" for="firstname">FirstName</label>
  				<input class="form-control form-control-lg" type="text" placeholder="enter name" id="firstname" name="firstname" value="${fn:escapeXml(param.firstname)}">
			</div>
			</center>
		</p>
		<p>
			<center>
			<button type="submit"class="btn btn-primary">Submit</button>
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
			</center>
		</p>
	</form>
	<br/>
	<button onclick="location.href='usercreate'" type="button" class="btn btn-primary">Create User</button>
	<br/>
	
	<h1><center>Matching User</center></h1>
	</div>
	<table class="table table-hover">
  <thead>
    <tr>
      <th scope="col">UserName</th>
      <th scope="col">Created</th>
      <th scope="col">IsMember</th>
      <th scope="col">FirstName</th>
      <th scope="col">LastName</th>
      <th scope="col">Email</th>
      <th scope="col">Phone</th>
      <th scope="col">CompetencyLevel</th>
      <th scope="col">Posts</th>
      <th scope="col">Reviews</th>
      <th scope="col">Delete</th>
      <th scope="col">Update</th>
    </tr>
  </thead>
  <tbody>
   <c:forEach items="${users}" var="user" >
    <tr class="table-active">
          <td><c:out value="${user.getUserName()}" /></td>
          <td><fmt:formatDate value="${user.getCreated()}" pattern="yyyy-MM-dd" /></td>
          <td><c:out value="${user.isMember()}" /></td>
          <td><c:out value="${user.getFirstName()}" /></td>
          <td><c:out value="${user.getLastName()}" /></td>
          <td><c:out value="${user.getEmail()}" /></td>
          <td><c:out value="${user.getPhone()}" /></td>
          <td><c:out value="${user.getCompetencyLevel()}" /></td>
          <td><a href="userposts?username=<c:out value="${user.getUserName()}"/>">StrategyPosts</a></td>
          <td><a href="blogcomments?username=<c:out value="${user.getUserName()}"/>">PostComments</a></td>
          <td><a href="userdelete?username=<c:out value="${user.getUserName()}"/>">Delete</a></td>
          <td><a href="userupdate?username=<c:out value="${user.getUserName()}"/>">Update</a></td>
    </tr>
    </c:forEach>
  </tbody>
</table>
       <br/>
</body>
    <!-- Bootstrap -->
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
</html>

