<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Get All Unique Stock Tickers</title>
<link href="bootstrap.min.css" rel="stylesheet">
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
	<div class="container" style="align:center">
	<form action="findStocks" method="get">
	<br>
		<h1><center>Get All Unique Stock Tickers</center></h1>
		<p><center>
		    <button type="submit" value="Get Tickers!" class="btn btn-primary">Submit</button>
			<br/><br/><br/>
			<span id="successMessage"><b>${messages}</b></span>
		</center></p>
	</form>
	<br/>
	    <button onclick="location.href='getreturn'" type="button" class="btn btn-primary">Get 10 years return by ticker</button>
	    <button onclick="location.href='getdaily'" type="button" class="btn btn-primary">Get daily recommendations by ticker</button>
	    <button onclick="location.href='getdailydate'" type="button" class="btn btn-primary">Get daily recommendations by date</button>
	<br/>
		<h1><center>All Unique Tickers In The Database</center></h1>
		<table class="table table-hover">
	  <thead>
	    <tr>
	      <th scope="col">Ticker</th>
	    </tr>
	  </thead>
	  <tbody>
	   <c:forEach items="${tickers}" var="ticker" >
	    <tr class="table-primary">
	         <td><c:out value="${ticker}" escapeXml="false" /></td>	
	    </tr>
	    </c:forEach>
	  </tbody>
	</table>
       </div>
</body>
    <!-- Bootstrap -->
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
</html>