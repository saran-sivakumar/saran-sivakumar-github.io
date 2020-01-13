<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="styles/style.css">
<script src="js/script.js"></script>
</head>
<body>
	<div class="topnav">
		<div class="movies">Movie Cruiser</div>
		<img src="images/movie-logo.png"> <a
			href="ShowMoviesListAdmin">Movies</a>
	</div>
	<h1>Edit Movies</h1>
	<div class="body-content-color">
		<form action="EditMovies" onsubmit="return validateMoviesForm()"
			name="editMovieList" method="post">
			<div class="form-field-spacing">
				<label for="title">Title</label>
				<div>
					<input type="text" value="${movies.title }" class="name-box"
						name="title" id="title">
				</div>
			</div>
			<div>
				<input type="hidden" name="id" value="${movies.id }">
			</div>
			<div>
				<div class="form-field-spacing">
					<label for="boxOffice">Gross($.)</label>
					<div>
						<input type="text" value="${movies.gross }" class="text-box"
							name="boxOffice" id="boxOffice">
					</div>
				</div>
				<div class="form-field-spacing">
					<label for="active">Active</label>
					<div>
						<input class="radio" type="radio" name="active" value="Yes"
							<c:if test="${movies.active }">checked </c:if>>Yes <input
							class="radio" type="radio" name="active" value="No"
							<c:if test="${!movies.active }">checked </c:if>>No
					</div>
				</div>
				<div class="form-field-spacing">
					<label for="dateOfLaunch">Date of Launch</label>
					<div>

						<input type="text" class="text-box" name="dateOfLaunch"
							value="<fmt:formatDate type="date" pattern="dd/MM/yyyy"
	                                         value="${movies.dateOfLaunch}" />">
					</div>
				</div>
				<div class="form-field-spacing">
					<label for="genre">Genre</label>
					<div>
						<select name="genre" class="dropdown" id="genre">
							<option value="${movies.genre }">${movies.genre }</option>
							<option value="Seience Fiction">Seience Fiction</option>
							<option value="Superhero">Superhero</option>
							<option value="Romance">Romance</option>
							<option value="Comedy">Comedy</option>
							<option value="Thriller">Thriller</option>
						</select>
					</div>
				</div>
			</div>
			<div>
				<div class="form-field-spacing">
					<c:if test="${movies.teaser }">
						<input type="checkbox" name="teaser" checked>
					</c:if>
					<c:if test="${!movies.teaser }">
						<input type="checkbox" name="teaser">
					</c:if>
					<label for="teaser">Has Teaser</label>
				</div>
			</div>
			<div>
				<div>
					<input type="hidden" name="id" value="${movies.id }">
				</div>
				<input type="submit" class="success-button" value="Save">
			</div>
			<div class="footer">
				
				<h5>copyright©2019</h5>
				
			</div>
		</form>
	</div>
</body>

</html>

