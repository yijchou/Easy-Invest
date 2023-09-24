<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="bootstrap.min.css" rel="stylesheet">
<title>Create an Educational Material</title>
</head>
<body>
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
	<div class="container">
		<h1 class="fs-1 text-primary fw-bold text-center mt-3 mb-3"> Admin: Publish An Educational Material</h1>
		<form action="materialcreate" method="post">
			<h1 class="fs-2 fw-bold">Create Educational Material</h1>
			<div class="form-group">
				<label class="form-label" for="title">Title</label>
				<input class="form-control" id="title" name="Title" value="">
			</div>
			<div class="form-group">
				<label class="form-label" for="content">Content</label>
				<input class="form-control" id="content" name="Content" value="">
			</div>
			<div class="form-group">
				<label class="form-label" for="created">Date(Format:YYYY-MM-DD)</label>
				<input class="form-control" id="created" name="Created" value="">
			</div>
			<div class="form-group">
				<label class="form-label" for="published">Published</label> 
				<input class="form-control" id="published" name="Published" value="">
			</div>
			<div class="form-group">
				<label class="form-label" for="username">UserName</label>
				<input class="form-control" id="username" name="username" value="">
			</div>

			<button type="submit" class="btn btn-primary fw-bold mt-3">
                Submit
            </button>
            <!-- try conditional button  -->
<%--             <input type="button" <c:if test="${username ne ('Beginner'&&'Intermediate'&&'Advanced'&&'Expert')}"> 
            Submit
        	<c:out value="disabled='disabled'"/>
        	</c:if>> --%>
            
		</form>
		<div class="mt-3" id="successMessage">${messages.success}</div>
	</div>
</body>
    <!-- Bootstrap -->
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="bootstrap.min.js"></script>
</html>