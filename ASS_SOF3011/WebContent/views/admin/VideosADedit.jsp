<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Online Entertainment</title>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<style>
body {
	background-image:
		url(https://hdwallsource.com/img/2014/9/blur-26347-27038-hd-wallpapers.jpg);
}
</style>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body>
	<div class="container" style="margin-top: 5%;">
		<div class="row">
			<div class="col-sm-3"></div>
			<div class="col-md-6">
				<h1 class="text-center text-success">Video Edition</h1>
				<br>
				<form action="/ASS_SOF3011/Admin/Videos/Edit?id=${ vid.id }" method="post">
					<div class="form-group">
						<label for="id">ID:</label> <input type="text"
							class="form-control validate" id="id" name="ID" readonly value="${ vid.id }">
					</div>

					<div class="form-group">
						<label data-error="wrong" data-success="right" for="title">Title:</label> <input type="text"
							class="form-control validate" id="title" name="title" value="${ vid.title }" ${ vid.id >= 1 ? 'readonly' : 'required'}>
					</div>

					<div class="form-group">
						<label data-error="wrong" data-success="right" for="poster">Poster:</label> <input type="text"
							class="form-control validate" id="poster" name="poster" value="${ vid.poster }" ${ vid.id >= 1 ? 'readonly' : 'required'}>
					</div>

					<div class="form-group">
						<label data-error="wrong" data-success="right" for="views">Views:</label> <input type="text"
							class="form-control validate" id="views" name="views" value="${ vid.views }" ${ vid.id >= 1 ? 'readonly' : 'required'}>
					</div>

					<div class="form-group">
						<label data-error="wrong" data-success="right" for="Description">Description:</label> <input type="text"
							class="form-control validate" id="Description" name="description" value="${ vid.description }" ${ vid.id >= 1 ? 'readonly' : 'required'}>
					</div>

					<div class="form-group">
						<label data-error="wrong" data-success="right" for="active">Active:</label>
						<select id="active" name="active"
							class="form-control validate">
							<option ${ vid.active == 1 ? "Selected" : "" } value="1">Active</option>
							<option ${ vid.active == 0 ? "Selected" : "" } value="0">Don't Active</option>
						</select>
					</div>

					<br>
					<button type="submit" class="btn btn-success">Submit</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>