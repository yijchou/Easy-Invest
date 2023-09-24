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
<title>Get Ten Years Return</title>
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
	<div class="container">
	<form action="getdaily" method="get">
		<br>
		<h1><center>Get All Daily Recommendations by Ticker</center></h1>
		<p><center>
			<div class="form-group">
  				<label class="col-form-label col-form-label-lg mt-4" for="givenTicker">Ticker</label>
  				<input class="form-control form-control-lg" type="text" placeholder="AAPL" id="givenTicker" name="givenTicker" value="${fn:escapeXml(param.ticker)}">
			</div>
			</center>
		</p>
		<p><center>
		 <input type="submit">
            <br/><br/><br/>
            <span id="successMessage"><b>${messages.success}</b></span>
		</p></center>
	</form>
		<h1><center>The Daily Recommendation</center></h1>
		
	<table class="table table-hover">
	  <thead>
	    <tr>
	      <th scope="col">ID</th>
	      <th scope="col">Ticker</th>
	      <th scope="col">Date</th>
	      <th scope="col">Recommend Position</th>
	    </tr>
	  </thead>
	  <tbody>
	   <c:forEach items="${recDate}" var="rec" >
	    <tr class="table-primary">
              	<td><c:out value="${rec.getDailyStockRecommendationID()}" /></td>
              	<td><c:out value="${rec.getTickerName()}" /></td>
              	<td><fmt:formatDate value="${rec.getDate()}" pattern="yyyy-MM-dd"/></td>
              	<td><c:out value="${rec.getPosition()}" /></td>
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