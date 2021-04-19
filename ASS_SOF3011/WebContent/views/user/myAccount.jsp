<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Online Entertainment</title>
<style>
body {
	position: relative;
	width: 100%;
	height: 0;
	background-image: url("https://wallpaperaccess.com/full/4721651.jpg");
}

/* Add a gray background color and some padding to the footer */
footer {
	position: relative;
	padding: 15px;
	bottom: 0;
	width: 100%;
	color: white;
	background-image:
		url("https://hdwallsource.com/img/2014/9/blur-26347-27038-hd-wallpapers.jpg");
}
</style>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
	<div class="container rounded bg-white mt-5">
		<div class="row">
			<div class="col-md-4 border-right">
				<div
					class="d-flex flex-column align-items-center text-center p-3 py-5">
					<img class="rounded-circle mt-5"
						src="https://i.imgur.com/XplLIk9.png" width="90"> <span
						class="font-weight-bold">${ acc.fullname }</span> <span
						class="text-black-50">${ acc.email }</span>
				</div>
			</div>
			<div class="col-md-8">
				<div class="p-3 py-5">
					<div class="d-flex justify-content-between align-items-center mb-3">
						<div class="d-flex flex-row align-items-center back">
							<a href="/ASS_SOF3011/Home"> <i
								class="fa fa-long-arrow-left mr-1 mb-1"></i> <b>Back to home</b>
							</a>
						</div>
						<h6 class="text-right">Edit Profile</h6>
					</div>
					<form action="/ASS_SOF3011/myAccount?id=${ acc.id }" method="post">
						<div class="row mt-3">
							<div class="col-md-12">
								<input type="text" class="form-control" placeholder="Username"
									name="username" readonly value="${ acc.username }">
							</div>
						</div>
						<div class="row mt-3">
							<div class="col-md-12">
								<input type="password" class="form-control"
									placeholder="Password" name="password" readonly
									value="${ acc.password }">
							</div>
						</div>
						<div class="row mt-3">
							<div class="col-md-12">
								<input type="text" class="form-control" placeholder="Fullname"
									name="fullname" value="${ acc.fullname }">
							</div>
						</div>
						<div class="row mt-3">
							<div class="col-md-12">
								<input type="text" class="form-control" placeholder="Email"
									name="email" value="${ acc.email }">
							</div>
						</div>
						<div class="mt-4 text-right">
							<a class="text-info" data-toggle="modal"
								data-target="#ChangePass" style="cursor: pointer;">Change
								Password</a>

						</div>
						<div class="mt-4 text-right">
							<button class="btn btn-primary profile-button" type="submit">Save
								Profile</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<!-- Modal -->
	<div class="modal fade" id="ChangePass" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">Change Password</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<div class="modal-body">
					<form action="/ASS_SOF3011/ChangePassword?id=${ acc.id }"
						method="post">
						<div class="form-content">
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<input type="text" class="form-control"
											placeholder="User Name *" name="CurrentUser" required />
									</div>
									<div class="form-group">
										<input type="password" class="form-control"
											placeholder="Current Password *" name="CurrentPass" required />
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<input type="password" class="form-control"
											placeholder="New Password *" name="NewPass" required />
									</div>
									<div class="form-group">
										<input type="password" class="form-control"
											placeholder="Confirm Password *" name="ConfirmPass" required />
									</div>
								</div>
							</div>
							<button type="submit" class="btn btn-primary profile-button">Submit</button>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>

		</div>
	</div>
</body>
</html>