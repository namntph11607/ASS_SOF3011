<div class="container">
	<div class="page-header">
		<h1 class="text-center text-danger">Favorite Users</h1>
	</div>
	<h3>
		<span class="label label-success">Video Title:</span>
	</h3>

	<select class="form-control" onchange="${ limitStr }">
		<option value="10">10</option>
		<option value="25">25</option>
		<option value="50">50</option>
		<option value="100">100</option>
	</select> <br>
	<div class="table-reponsive">
		<table class="table table-bordered text-center">
			<thead align="center">
				<tr class="warning">
					<th>UserName</th>
					<th>FullName</th>
					<th>Email</th>
					<th>Favorite Date</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="user" items="${ listU }">
					<tr class="info">
						<td>UserName1</td>
						<td>FullName2</td>
						<td>Email3</td>
						<td>date4</td>
					</tr>

				</c:forEach>
			</tbody>
		</table>
	</div>

	<div class="pager">
		<a
			href="/ASS_SOF3011/Admin/Users?page=${ page == 1 ? page : page - 1 }">
			<c:if test="${ page > 1}">
				<button class="btn btn-primary glyphicon glyphicon-chevron-left"></button>
			</c:if>
		</a> <span class="label label-info">${ page }</span> <a
			href="/ASS_SOF3011/Admin/Users?page=${ page + 1 }">
			<button class="btn btn-primary glyphicon glyphicon-chevron-right"></button>
		</a>
	</div>
</div>