<div class="container">
	<div class="page-header">
		<h1 class="text-center text-danger">Favorites</h1>
	</div>
	<br>
	<div class="table-reponsive">
		<table class="table table-bordered text-center">
			<thead align="center">
				<tr class="warning">
					<th>Video Title</th>
					<th>Favorite Count</th>
					<th>Latest Date</th>
					<th>Oldest Date</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach begin="1" end="5">
					<tr class="info">
						<td>Title1</td>
						<td>Count2</td>
						<td>date late3</td>
						<td>date old4</td>
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