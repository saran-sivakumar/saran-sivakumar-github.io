<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Menu Items List</title>
<link rel="stylesheet" href="styles/style.css">
<script src="js/script.js"></script>
</head>
	<body>
		<div class="topnav">
			<div class="home">truYum</div>
			<img src="images/truyum-logo.png"> <a
				href="ShowMenuItemListAdmin">Menu</a>
		</div>
		<h1>Menu Items</h1>
		<table>
			<tr>
				<th>Name</th>
				<th>Price</th>
				<th>Active</th>
				<th>Date of Launch</th>
				<th>Category</th>
				<th>Free Delivery</th>
				<th>Action</th>
			</tr>
			<c:forEach items="${menuItemList}" var="menuItem">
				<tr>
					<td><c:out value="${menuItem.name}" /></td>
					<td><fmt:setLocale value="en_IN" /> <fmt:formatNumber
							value="${menuItem.price}" type="currency" /></td>
					<td><c:if test="${menuItem.active eq 'true'}">Yes</c:if> <c:if
							test="${menuItem.active eq 'false'}">No</c:if></td>
					<td><fmt:formatDate type="date" pattern="dd/MM/yyyy"
							value="${menuItem.dateOfLaunch}" /></td>
					<td><c:out value="${menuItem.category}" /></td>
					<td><c:if test="${menuItem.freeDelivery eq 'true'}">Yes</c:if>
						<c:if test="${menuItem.freeDelivery eq 'false'}">No</c:if></td>
					<td><a href="ShowEditMenuItem?id=${menuItem.id }">Edit</a></td>
				</tr>
			</c:forEach>
		</table>
	</body>
<div class="footer">
	
	<h5>copyright©2019</h5>
	
</div>

</html>
