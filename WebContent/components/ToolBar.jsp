<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>

<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
	<title>Auth Checker</title>
	

</head>
<body>
	<div class="container-fluid bg-light border border-info">
		<div class="row">
			<div class="col-sm-3">
				<a href="MakeAnAd.jsp" target="_self">
					<button type="button" class="btn-info btn-block btn-lg">
						Make An Ad
					</button>
				</a>

			</div>
			<div class="col-sm-3">
				<a href="ViewFlow.jsp">
				<button type="button" class="btn-info btn-block btn-lg">
					View Flow
				</button>
				</a>
				
			</div>
			<div class="col-sm-3">
				<a href="ValidateAFavor.jsp" target="_self">
					<button type="button" class="btn-info btn-block btn-lg">
					Validate A Favor
					<!-- TODO: implement button logic -->
				</button>
				</a>
			</div>
			<div class="col-sm-3">
				<div class="dropdown bg-info">
					<button class="btn-info dropdown-toggle btn-block btn-lg"
						type="button" data-toggle="dropdown">
						<%-- username here: --%>
						<% 
						String username = "";
						if (session.getAttribute("username") != null){
							username = session.getAttribute("username").toString();
						}
						%>
						<%= username %>
					</button>
					<ul class="dropdown-menu dropdown-menu-right">
						<li>
							<div class="row">
								<div class="col-sm-12">
									<button class="btn-block btn-outline-info" type="button">
										View Profile <img
											src="https://img.icons8.com/color/30/000000/test-account.png" />
										<!-- TODO: implement button logic -->
									</button>
								</div>
							</div>
						</li>
						<li>
							<div class="row no-gutters">
								<div class="col-sm-12">
									<button class="btn-block btn-outline-warning text-dark"
										type="button">
										log out <img
											src="https://img.icons8.com/offices/30/000000/exit.png" />
										<!-- TODO: implement button logic -->
									</button>
								</div>
							</div>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>

	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
		integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
		integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
		crossorigin="anonymous"></script>
</body>
</html>