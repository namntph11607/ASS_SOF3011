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
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="/ASS_SOF3011/Admin/Home"><b>Admin
						OE</b></a>
			</div>
			<ul class="nav navbar-nav">
				<li><a href="/ASS_SOF3011/Admin/Home">Home</a></li>
				<li><a href="/ASS_SOF3011/Admin/Videos">Videos</a></li>
				<li><a href="/ASS_SOF3011/Admin/Users">Users</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="/ASS_SOF3011/Login"><span
						class="glyphicon glyphicon-log-in"></span> Logout</a></li>
			</ul>
		</div>
	</nav>
	<div class="container">
		<div class="page-header">
			<h1 class="text-danger" align="center">Videos List</h1>
		</div>
		<div class="row">
			<div class="col-sm-2" data-toggle="modal" data-target="#modalAdd">
				<a href="/ASS_SOF3011/Admin/Videos/Edit?id=0">
					<button class="btn btn-success">
						<span class="glyphicon glyphicon-plus"></span>
					</button>
				</a>
			</div>
<!-- 
			<div class="col-sm-8"></div>
			<div class="col-sm-2 d-flex justify-content-end">
				<select class="form-control" id="select" name="limit">
					<option value="10" selected>10</option>
					<option value="25">25</option>
					<option value="50">50</option>
					<option value="100">100</option>
				</select>
			</div>
		</div> -->
		<br>

		<table class="table table-bordered text-center" id="">
			<thead align="center">
				<tr class="warning">
					<th>ID</th>
					<th>Title</th>
					<th>Poster</th>
					<th>Views</th>
					<th>Description</th>
					<th>Active</th>
					<th colspan="2">Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="vid" items="${ listV }">
					<tr class="info">
						<td>${ vid.id }</td>
						<td>${ vid.title }</td>
						<td><img alt="poster" src="${ vid.poster }" width="40px"
							height="50px"></td>
						<td>${ vid.views }</td>
						<td>${ vid.description }</td>
						<td>${ vid.active == 1 ? "Active" : "Don't Active" }</td>
						<td><a href="/ASS_SOF3011/Admin/Videos/Edit?id=${ vid.id }">
								<button class="btn btn-primary">
									<span class="glyphicon glyphicon-pencil"></span>
								</button>
						</a></td>
						<td>
							<form action="/ASS_SOF3011/Admin/Videos/Delete?id=${ vid.id }"
								method="POST" class="form-delete">
								<button class="btn btn-danger" type="submit" id="btnDelete">
									<span class="glyphicon glyphicon-trash"></span>
								</button>
							</form>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<ul class="pager">
		<c:if test="${ page > 1}">
			<li><a
				href="/ASS_SOF3011/Admin/Videos?page=${ page == 1 ? page : page - 1 }">

					<span class="glyphicon glyphicon-chevron-left"></span>

			</a></li>
		<span class="label label-info">${ page }</span>
		</c:if>
		<li><a href="/ASS_SOF3011/Admin/Videos?page=${ page + 1 }"> <span
				class="glyphicon glyphicon-chevron-right"></span>
		</a></li>
	</ul>

	<script>
		    $(document).ready(function() {
			// Nếu người dùng click vào nút delete
			// Thì submit form
			$('#btnDelete').click(function() {
				$(this).parent().submit();
				return false;
			});

			// Nếu sự kiện submit form xảy ra thì hỏi người dùng có chắc không?
			$('.form-delete').submit(function() {
				if (!confirm('Bạn có chắc muốn xóa thành viên này không?')) {
					return false;
				}
				// Thực hiện xóa
				return true;
			});
		});
	</script>
</body>
</html>