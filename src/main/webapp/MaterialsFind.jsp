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
<title>Find Educational Materials</title>
</head>
<body>
	<!-- I set up the navibar like this, please merge the format as the landing page -- Yuhan Dec06  --> 
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
		<h1 class="fs-1 text-primary fw-bold text-center mt-3 mb-3">Study Area: Browse Your Educational Materials</h1>
		<form action="materialfind" method="get">
			<h1 class="fs-2 fw-bold">Search for Materials by CompetencyLevel</h1>
			<div class="form-group">
				<label class="form-label"  for="username">CompetencyLevel</label>
				<input class="form-control" id="username" name="username" value="${fn:escapeXml(param.username)}">
			</div>
			<button type="submit" class="btn btn-primary fw-bold mt-3">
                Submit
        	</button> 
        	
		</form>
		<div class="mt-3" id="successMessage">${messages.success}</div>
	<h1><center>Matching Educational Materials</center></h1>
	<table class="table table-hover">
  	<thead>
    <tr>
      <th scope="col">Competency Level</th>
      <th scope="col">Title</th>
      <th scope="col">Content</th>
    </tr>
  	</thead>
  	<tbody>
   	<c:forEach items="${educationalMaterials}" var="educationalMaterial" >
   	 <tr class="table-primary">
      <th scope="row">Primary</th>
                    <td><c:out value="${educationalMaterial.getUserName()}" /></td>
                    <td><c:out value="${educationalMaterial.getTitle()}" /></td>
                    <td><c:out value="${educationalMaterial.getContent()}" /></td>
    </tr>
    </c:forEach>
  </tbody>
  </table>
     <button class="btn btn-primary fw-bold mt-3 mb-3" id="educationalmaterialcreate"  >
    	<a style="color:#FFFFFF" href="MaterialsCreate.jsp">Create An Educational Material</a>
    </button>
   </div>
</body>
</html>