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
<title>Find Strategy Post</title>

<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">Easy Invest</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarColor01" aria-controls="navbarColor01" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarColor01">
      <ul class="navbar-nav me-auto">
        <li class="nav-item">
          <a class="nav-link active" href="#">Home
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
	<form action="findstrategyposts" method="get">
		<h1>Search for strategy posts by user name</h1>
		<p>
			<label for="username">User name</label>
			<input id="username" name="username" value="${fn:escapeXml(param.username)}">
		</p>
		<p>
			<input type="submit" class = "btn btn-success" value = "search">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	
	<br/>
	<button onclick="location.href='strategypostcreate'" type="button" class="btn btn-primary">Create a strategy post</button>
	<br/>
	
	<br/>
	<br/>
	
	<h1>Matching strategy posts</h1>
	<table class="table table-hover">
            <tr>
                <th>UserName</th>
                <th>Created</th>
                <th>IsPublished</th>
                <th>Title</th>
                <th>Content</th>
                <th>Likes</th>
                <th>Delete Strategy Post</th>
                <th>Update Strategy Post</th>
            </tr>
            <c:forEach items="${strategyPosts}" var="strategypost" >
                <tr>
                    <td><c:out value="${strategypost.getUserName()}" /></td>
                    <td><fmt:formatDate value="${strategypost.getCreated()}" pattern="yyyy-MM-dd" /></td>
                    <td><c:out value="${strategypost.isPublished()}" /></td>
                    <td><c:out value="${strategypost.getTitle()}" /></td>
                    <td><c:out value="${strategypost.getContent()}" /></td>
                    <td><c:out value="${strategypost.getLikes()}" /></td>
                    <td><a href="strategypostdelete?postId=<c:out value="${strategypost.getPostId()}"/>">Delete</a></td>
                    <td><a href="strategypostupdate?postId=<c:out value="${strategypost.getPostId()}"/>">Update</a></td>
                </tr>
            </c:forEach>
       </table>
       <br/>
       </center>
</body>
</html>