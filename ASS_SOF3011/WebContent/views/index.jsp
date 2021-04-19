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
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="/ASS_SOF3011/Home">Online
					Entertainment</a>
			</div>
			<ul class="nav navbar-nav">
				<li><a href="/ASS_SOF3011/Home">Home</a></li>
				<c:if test="${ sessionScope.acc != null }">
					<li><a href="/ASS_SOF3011/Favorite">My Favorite </a></li>
				</c:if>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<c:if test="${ sessionScope.acc == null }">
					<li><a href="/ASS_SOF3011/Login"><span
							class="glyphicon glyphicon-log-in"></span> Login</a></li>
				</c:if>
				<c:if test="${ sessionScope.acc != null }">
					<li><a><b> Hi, ${ sessionScope.acc.fullname }</b></a></li>
					<li><a href="/ASS_SOF3011/myAccount"><span
							class="glyphicon glyphicon-user"></span> My Account</a></li>
					<li><a href="/ASS_SOF3011/Logout"><span
							class="glyphicon glyphicon-log-in"></span> Logout</a></li>
				</c:if>

			</ul>
		</div>
	</nav>


	<div class="container">
		<div class="page-header">
			<h1 class="text-danger" align="center">Hot Videos</h1>
		</div>

		<div class="row">
			<c:forEach var="tit" items="${ listT }">
				<div class="col-sm-4" align="center">
					<div class="panel panel-primary">
						<div class="panel-heading" id="title">${ tit.title }</div>
						<div class="panel-body">
							<a href="/ASS_SOF3011/Details?id=${ tit.id }" target="_blank">
								<img src="${ tit.poster }" class="img-responsive"
								style="width: 100%" alt="Image">
							</a>
						</div>
						<div class="panel-footer">
							<form action="/ASS_SOF3011/LikeVid" method="get">
								<button
									${ sessionScope.acc != null ? 'type="submit"' : 'type="button"' }
									data-toggle="modal" data-target="#modalFavoFull"
									class="btn btn-info" onclick="btnAlert()">
									<span class="glyphicon glyphicon-thumbs-up"></span>Like
								</button>
								<input id="videoId" type="hidden" name="videoId"
									value="${ tit.id }">
								<button type="button" class="btn btn-info" onclick="btnAlert()"
									data-toggle="modal" data-target="#myModal"
									data-id="${ tit.id }" id="share">
									<span class="glyphicon glyphicon-share-alt"></span> Share
								</button>
							</form>


						</div>
					</div>
				</div>
			</c:forEach>
		</div>
		<br>
		<!-- paginate -->
		<div align="center">
			<a class="btn btn-lg" href="/ASS_SOF3011/Home?page=1"> <span
				class="glyphicon glyphicon-fast-backward"></span>
			</a> <a class="btn btn-lg"
				href="/ASS_SOF3011/Home?page=${ page == 1 ? page : page - 1 }">
				<span class="glyphicon glyphicon-backward"></span>
			</a> <a class="btn btn-lg" href="/ASS_SOF3011/Home?page=${ page + 1 }">
				<span class="glyphicon glyphicon-forward"></span>
			</a> <a class="btn btn-lg" href="/ASS_SOF3011/Home?page=${ page }"> <span
				class="glyphicon glyphicon-fast-forward"></span>
			</a>
		</div>
	</div>

	<!-- Footer -->
	<br>

	<footer class="container-fluid text-center">
		<strong>Nguyễn Thành Nam &copy; PH11607</strong>
	</footer>

	<c:if test="${ sessionScope.acc != null }">
		<!-- Modal -->
		<div class="modal fade" id="myModal" role="dialog">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">Chia sẻ tiểu phẩm</h4>
					</div>
					<form action="/ASS_SOF3011/SendEmail?id=${ vidFind.id }"
						method="get">
						<div class="modal-body" id="modal-body">
							<div class="form-group">
								<input id="posterId" type="hidden" name="posterId" value="">
								<label for="email">Nhập email bạn muốn chia sẻ:</label> <input
									type="email" class="form-control" id="email"
									placeholder="Enter email" name="email">
							</div>
							<button type="submit" class="btn btn-info">Send</button>
						</div>
					</form>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>
				</div>

			</div>
		</div>
	</c:if>

	<c:if test="${ favoNull != null }">
		<div class="modal fade" id="modalFavoFull" role="dialog">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">Thông Báo</h4>
					</div>
					<div class="modal-body" id="modal-body">
						<div class="form-group">
							<label for="email">Bạn đã thích tiểu phẩm này!!!</label>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>
				</div>

			</div>
		</div>
	</c:if>
	<script type="text/javascript">
		if(${sessionScope.acc == null}){
			function btnAlert() {
				alert("Bạn chưa đăng nhập!");
			}
		}
		
		
		$(document).on("click", "#share", function () {
		     var myPosterId = $(this).data('id');
		     $(".modal-body #posterId").val( myPosterId );
		    $('#myModal').modal('show');
		});

	</script>
</body>
</html>