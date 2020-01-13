<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Movies</title>
<link rel="stylesheet" href="styles/style.css">
<script src="js/script.js"></script>
</head>
<body>
	<div class="topnav">

		<div class="home">Movie Cruiser</div>
		<img src="images/movie-logo.png"> <a
			href="ShowMoviesListAdmin">Movies</a>
	</div>
	<h1>
		Movies
	</h1>
	<table>
		<tr>
			<th>Title</th>
			<th>Box office</th>
			<th>Active</th>
			<th>Date of Launch</th>
			<th>Genre</th>
			<th>Has Teaser</th>
			<th>Action</th>
		</tr>
		<c:forEach items="${movies}" var="movies">
			<tr>
				<td><c:out value="${movies.title}" /></td>
				<td><fmt:setLocale value="en_US" /> <fmt:formatNumber
						value="${movies.gross}" type="currency" /></td>
				<td><c:if test="${movies.active eq 'true'}">Yes</c:if> <c:if
						test="${movies.active eq 'false'}">No</c:if></td>
				<td><fmt:formatDate type="date" pattern="dd/MM/yyyy"
						value="${movies.dateOfLaunch}" /></td>
				<td><c:out value="${movies.genre}" /></td>
				<td><c:if test="${movies.teaser eq 'true'}">Yes</c:if> <c:if
						test="${movies.teaser eq 'false'}">No</c:if></td>
				<td><a href="ShowEditMovies?id=${movies.id }">Edit</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
<div class="footer">

	<h5>copyright©2019</h5>
	
</div>
</html>