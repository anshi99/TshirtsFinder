<%@page import="com.nagarro.training.constants.Constants"%>
<%@page import="com.nagarro.training.models.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="ISO-8859-1">
<title>TShirtFinder</title>

<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<!-- Custom CSS -->
<link
	href="<c:url value="${PageContext.request.contextPath}/resources/css/styles.css" />"
	rel="stylesheet">

</head>

<body>
	<div class="container-fluid">
		<%
		User user = (User) session.getAttribute(Constants.USER_SESSION_ATTRIBUTE);
		if (user == null) {
			response.sendRedirect("./login");
		}
		%>
		<div class="d-flex py-3">
			<h4 class="mt-2 flex-grow-1 text-center">TShirts Finder</h4>
			<p class="mt-3 px-3">
				Hi
				<%
			if (user != null) {
				out.println(user.getUsername());
			}
			%>
				<button type="button" class="btn btn-secondary btn-sm mt-2"
					style="height: 35px;"
					onclick="window.location.replace('./logout');">Logout</button>
		</div>

		<div class="ms-4">

			<form id="tshirt_form" action="./filter" method="post">

				<table>
					<tr>
						<td><label for="color">Color</label></td>
						<td><input class="form-control" type="text" name="color"
							id="color" /></td>
						<td><small id="color_error"
							style="color: #f41f1f; margin-left: 1em;"> </small></td>
					</tr>
					<tr>
						<td><label for="size">Size</label></td>
						<td><select class="form-control" name="size" id="size">
								<option value="S">S</option>
								<option value="M">M</option>
								<option value="L">L</option>
								<option value="XL">XL</option>
								<option value="XXL">XXL</option>
						</select></td>
						<td></td>
					</tr>
					<tr>
						<td><label for="gender">Gender</label></td>
						<td><select class="form-control" name="gender" id="gender">
								<option value="M">Male</option>
								<option value="F">Female</option>
								<option value="U">Unisex</option>
						</select></td>
						<td></td>
					</tr>
					<tr>
						<td><label for="output_preference">Sort</label></td>
						<td><select class="form-control" name="output_preference"
							id="output_preference">
								<option value="1">By Price</option>
								<option value="2">By Rating</option>
								<option value="3">By Price & Rating</option>
						</select></td>
						<td></td>
					</tr>
					<tr>
						<td></td>
						<td><button type="submit"
								class="btn btn-primary btn-sm w-100 mt-2">Submit</button></td>
						<td></td>
					</tr>
				</table>
			</form>

		</div>

		<div>
			<p id="tshirt_loading" style="display: none">Loading...</p>
			<p id="no_tshirt_found" style="display: none">No TShirt Found!</p>
		</div>

		<div class="tshirt-table w-100 px-4 mt-5 text-center">
			<table class="w-100" id="result_table" style="display: none;">
				<thead>
					<tr>
						<th style="width: 5%;">S.No.</th>
						<th style="width: 15%">ID</th>
						<th style="width: 20%">Name</th>
						<th style="width: 10%">Color</th>
						<th style="width: 8%">Gender</th>
						<th style="width: 8%">Size</th>
						<th style="width: 8%">Price</th>
						<th style="width: 8%">Rating</th>
						<th style="width: 8%">Availability</th>
					</tr>
				</thead>
				<tbody id="result_body">
				</tbody>
			</table>
		</div>
	</div>

	<!-- BootStrap JS -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>

	<!-- JQUERY -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

	<!-- Custom JS -->
	<script
		src="<c:url value="${PageContext.request.contextPath}/resources/js/scripts.js" />"></script>

</body>