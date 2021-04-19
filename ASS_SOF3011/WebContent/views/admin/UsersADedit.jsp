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
				<h1 class="text-center text-success">User Edition</h1>
				<br>
				<form action="/ASS_SOF3011//Admin/Users/Edit?id=${ user.id }"
					method="post">
					<div class="form-group">
						<label for="id">ID:</label> <input type="text"
							class="form-control validate" id="id" name="ID" readonly
							value="${ user.id }">
					</div>

					<div class="form-group">
						<label data-error="wrong" data-success="right" for="Username">Username
							*</label> <input type="text" class="form-control validate" id="Username"
							name="username" value="${ user.username }"  ${ user.id == 1 ? 'readonly' : 'required'}>
					</div>

					<div class="form-group">
						<label data-error="wrong" data-success="right" for="pwd">Password
							*</label> <input type="password" class="form-control validate" id="pwd"
							name="password" value="${ user.password }" ${ user.id >= 1 ? 'readonly' : 'required'}>
					</div>

					<div class="form-group">
						<label data-error="wrong" data-success="right" for="Fullname">Fullname
							*</label> <input type="text" class="form-control validate" id="Fullname"
							name="fullname" value="${ user.fullname }" ${ user.id >= 1 ? 'readonly' : 'required'}>
					</div>

					<div class="form-group">
						<label data-error="wrong" data-success="right" for="email">Email
							address *</label> <input type="email" class="form-control validate"
							id="email" name="email" value="${ user.email }" ${ user.id >= 1 ? 'readonly' : 'required'}>
					</div>

					<div class="form-group">
						<label data-error="wrong" data-success="right" for="formRoleEdit">Role</label>
						<select id="formRoleEdit" name="role"
							class="form-control validate">
							<option ${ user.role == 0 ? "Selected" : "" } value="0">User</option>
							<option ${ user.role == 1 ? "Selected" : "" } value="1">Admin</option>
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