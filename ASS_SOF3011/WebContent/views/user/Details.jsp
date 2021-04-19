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
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="page-header">
		<h1 class="text-danger" align="center">Details</h1>
	</div>
	<div class="container">
		<div class="row content">
			<div class="col-sm-8">
				<div class="panel panel-success">
					<div class="panel-body" style="height: 666px;">
						<img src="${ det.poster }" class="img-responsive"
							style="width: 100%; height: 100%;" alt="Image">
					</div>
					<div class="panel-heading" id="title">
						<h4>${ det.title }</h4>
					</div>
					<div class="panel-body">
						<h2>${ det.description }</h2>
					</div>
					<div class="panel-footer" align="right">
						<a class="btn btn-success" onclick="btnAlert()"> <span
							class="glyphicon glyphicon-thumbs-up"></span> Like
						</a> <a class="btn btn-success" onclick="btnAlert()"
							data-toggle="modal" data-target="#myModal" data-id="${ tit.id }"
							id="share"> <span class="glyphicon glyphicon-share-alt"></span>
							Share
						</a>
					</div>
				</div>

			</div>

			<div class="col-sm-4">
				<c:forEach items="${ listD }" var="pst">
					<div class="panel panel-info">
						<div class="panel-heading">
							<a href="/ASS_SOF3011/Details?id=${ pst.id }">
								<div class="row">
									<div class="col-sm-4">
										<img src="${ pst.poster }" class="img-responsive"
											style="width: 100%; height: 100%;" alt="Image">
									</div>
									<div class="col-sm-8">
										<h3>${ pst.title }</h3>
									</div>
								</div>
							</a>
						</div>
					</div>
				</c:forEach>

			</div>
		</div>
	</div>

</body>
</html>