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
<title>Create a strategy post</title>

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
	<center><br/>
	<h2>Create a New Strategy Post</h2>
	<form action="strategypostcreate" method="post">
		<p>
			<label for="username">UserName</label>
			<input id="username" name="username" value="">
		</p>
		<p>
			<label for="title">Post Title</label>
			<input id="title" name="title" value="">
		</p>
		<p>
			<label for="content">Content</label>
			<input id="content" name="content" value="">
		</p>
		<p>
 			<input type="submit" value = "post" class="btn btn-lg btn-success">
		
		</p>
	</form>
	<br/><br/>
	<p>
		<span id="successMessage"><b>${messages.success}</b></span>
	</p>

	<h3>
		<div id="FindStrategyPosts"><a href="findstrategyposts">Search for Strategy Posts by User Name</a></div>
	</h3>
		<br/>
	<h4>
		<div id="StrategyPostUpdate"><a href="strategypostupdate">Update Your Strategy Post</a></div>
	</h4>
		<br/>
	<h6>
	<div id="StrategyPostDelete"><a href="strategypostdelete">Delete Your Previous Strategy Post</a></div>
	</h6>	
		<br/><br/>
	
	
	</center>
</body>
</html>