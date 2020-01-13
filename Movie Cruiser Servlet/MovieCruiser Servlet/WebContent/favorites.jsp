<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Favorites</title>
<link rel="stylesheet" href="styles/style.css">
<script src="js/script.js"></script>
</head>
<body onLoad="finalCheck()">
	<div class="topnav">

		<div class="home">Movie Cruiser</div>
		<img src="images/movie-logo.png"> <a
			href="ShowFavoriteMovies">Favorites</a> <a
			href="ShowMoviesListCustomer">Movies</a>
	</div>
	<h1>Favorites</h1>
	<c:if test="${message }">
		<h3>Item Removed from Favorites Successfully</h3>
	</c:if>
	<table id="tableID">
		<tr>
			<th>Title</th>

			<th class="space">Gross</th>
			<th>Genre</th>
			<th></th>
		</tr>
		<c:forEach items="${movies.movieList }" var="movies">
			<tr>
				<td>${movies.title }</td>
				<td class="space">$. <fmt:formatNumber value="${movies.gross}"
						pattern="#,##,##,##,###.00" />
				</td>
				<td>${movies.genre}</td>
				<td><a href="RemoveFavorites?id=${movies.id }">Delete</a></td>
			</tr>
		</c:forEach>
		<tr>
			<td></td>
			<td class="ttotal">No Of Favorites</td>
			<td class="ttotal"><fmt:formatNumber
					value="${movies.noOfFavorites }" /></td>
			<td></td>
		</tr>
		</table>
</body>
<div class="footer">
	
	<h5>copyright©2019</h5>
	
</div>
</html>