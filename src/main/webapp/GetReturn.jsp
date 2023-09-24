<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Get Ten Years Return</title>
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
	<div class="container">
	<form action="getreturn" method="get">
		<h1><center>Get Ten Years Return by Ticker</center></h1>
		<p>
			<center>
			<div class="form-group">
  				<label class="col-form-label col-form-label-lg mt-4" for="givenTicker">Ticker</label>
  				<input class="form-control form-control-lg" type="text" placeholder="AAPL" id="givenTicker" name="givenTicker" value="${fn:escapeXml(param.ticker)}">
			</div>
			</center>
		</p>
		<p><center>
			<button type="submit"class="btn btn-primary"> submit </button>
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</center></p>
	</form>
		<h1><center>10 Years Return (Based on Adj Close Price) from 2012 to 2022</center></h1>
	<table class="table table-hover">
  <thead>
    <tr>
      <th scope="col">Return</th>
    </tr>
  </thead>
  <tbody>
    <tr class="table-primary">
		<td><c:out value="${ret}" /></td>
    </tr>
  </tbody>
</table>
      
    	<h1><center>The Starting and Ending Price Data</center></h1>
	<table class="table table-hover">
	  <thead>
	    <tr>
	      <th scope="col">Date</th>
	      <th scope="col">Open</th>
	      <th scope="col">High</th>
	      <th scope="col">Low</th>
	      <th scope="col">Close</th>
	      <th scope="col">Adj_Close</th>
	      <th scope="col">Volume</th>
	      <th scope="col">MarketCap_Rank</th>
	      <th scope="col">Ticker</th>
	    </tr>
	  </thead>
	  <tbody>
	   <c:forEach items="${stocks}" var="stock" >
	    <tr class="table-primary">
              	<td><fmt:formatDate value="${stock.getDate()}" pattern="yyyy-MM-dd"/></td>
                  <td><c:out value="${stock.getOpen()}" /></td>
                  <td><c:out value="${stock.getHigh()}" /></td>
                  <td><c:out value="${stock.getLow()}" /></td>
              	<td><c:out value="${stock.getClose()}" /></td>
              	<td><c:out value="${stock.getAdjClose()}" /></td>
              	<td><c:out value="${stock.getVolume()}" /></td>
              	<td><c:out value="${stock.getStockCapRank()}" /></td>
              	<td><c:out value="${stock.getTickerName()}" /></td>
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