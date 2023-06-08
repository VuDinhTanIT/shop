<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>Trang Quản Trị - Electronic Shop</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<!-- VENDOR CSS -->
<link rel="stylesheet"
	href="<c:url value='../resource/admin/assets/vendor/bootstrap/css/bootstrap.min.css'/>">
<link rel="stylesheet"
	href="<c:url value='../resource/admin/assets/vendor/font-awesome/css/font-awesome.min.css'/>">
<link rel="stylesheet"
	href="<c:url value='../resource/admin/assets/vendor/linearicons/style.css'/>">
<link rel="stylesheet"
	href="<c:url value='../resource/admin/assets/vendor/chartist/css/chartist-custom.css'/>">
<!-- MAIN CSS -->
<link rel="stylesheet"
	href="<c:url value='../resource/admin/assets/css/main.css'/>">
<link rel="stylesheet"
	href="<c:url value='../resource/admin/assets/css/demo.css'/>">
<!-- GOOGLE FONTS -->
<link
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700"
	rel="stylesheet">

<!-- ICONS -->
<link rel="apple-touch-icon" sizes="76x76"
	href="<c:url value='../resource/admin/assets/img/apple-icon.png'/>">
<link rel="icon" type="image/png" sizes="96x96"
	href="<c:url value='../resource/admin/assets/img/favicon.png'/>">
<script src='https://kit.fontawesome.com/a076d05399.js'></script>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.min.js"></script>
</head>
<body>
	<!-- WRAPPER -->
<body>
	<!-- WRAPPER -->
	<div id="wrapper">
		<jsp:include page="../common/header.jsp" />

		<jsp:include page="../common/category.jsp" />

		<!-- MAIN -->
		<!-- MAIN CONTENT -->
		<div class="main">
			<div class="main-content">
				<div class="container-fluid">
					<!-- OVERVIEW -->
					<div class="panel panel-headline">
						<div class="panel-body">
							<form>
								<div class="row">
									<div class="col-md-12">
										<div class="panel">
											<div class="panel-heading">
												<h3 class="panel-title">Thống kê theo tháng</h3>
												<div class="right">
													<button type="submit">
														<span class="label label-danger"
															style="font-size: 15px; margin-right: 15px;">Xóa</span>
													</button>
													<a href="#"><span class="label label-success"
														style="font-size: 15px;">Create new order</span></a>
												</div>
											</div>
											<div class="panel-body no-padding">
												<h1>Doanh thu theo tháng</h1>
												<table class=" table table-stat table-striped"
													style="margin: auto;">
													<thead>
														<tr>
															<th>Mã sản phẩm</th>
															<th>Tên sản phẩm</th>
															<th>Ảnh</th>
															<th>Số lượng bán được</th>
														</tr>
													</thead>
													<tbody>
														<c:forEach items="${bestSellingProducts}" var="product">
															<tr>
																<td>${product.key.productId}</td>
																<td>${product.key.productName}</td>
																<td><img
																		style="width: 20%;"
																		src="../download?image=${product.key.image}"></td>
																<td>${product.value}</td>
															</tr>
														</c:forEach>
													</tbody>
												</table>
												<canvas id="BestSellingProductsChart"></canvas>
											</div>
										</div>

									</div>
									<div id="headline-chart" class="ct-chart"></div>
								</div>
							</form>
						</div>
					</div>
				</div>
				<!-- END OVERVIEW -->
			</div>
			<!-- END MAIN CONTENT -->
		</div>
		<!-- END MAIN -->
		<div class="clearfix"></div>
		<footer>
			<div class="container-fluid">
				<p class="copyright">
					&copy; 2023 <a href="https://www.themeineed.com" target="_blank">
				</p>
			</div>
		</footer>
	</div>
	<!-- END WRAPPER -->
	<!-- Javascript -->
</body>
</body>
    <script>
        var ctx = document.getElementById('BestSellingProductsChart').getContext('2d');
        var bestSellingProductsJsonString = '${bestSellingProductsStringInt}';
        bestSellingProductsJsonString = bestSellingProductsJsonString.replace(/=/g, ': '); // thay thế ký tự "=" bằng dấu ":" để biến chuỗi thành định dạng JSON chuẩn
        var products = JSON.parse(bestSellingProductsJsonString); // chuyển đổi chuỗi JSON thành đối tượng JavaScript
        var labels = [];
        var data = [];

        for (varkey in products) { // lặp qua các sản phẩm trong `products`
          labels.push(key);
          data.push(products[key]);
        }
        var chart = new Chart(ctx, {
          type: 'pie',
          data: {
            labels: labels,
            datasets: [{
              data: data,
              backgroundColor: [
                '#FF6384',
                '#36A2EB',
                '#FFCE56',
                '#8B008B',
                '#00FA9A'
              ]
            }]
          },
          options: {}
        });
    </script>