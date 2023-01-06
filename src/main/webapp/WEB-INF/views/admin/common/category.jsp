<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Electronic Shop</title>
</head>
<body>
	<!-- LEFT SIDEBAR -->
	<div th:fragment="category">
		<div id="sidebar-nav" class="sidebar">
			<div class="sidebar-scroll" style="padding-top: 20px;">
				<nav>
					<ul class="nav">
						<li><a href="home" class=""><i class="lnr lnr-home"></i>
								<span>Trang chủ</span></a></li>
						<li><a href="user-list" class=""><i class="lnr lnr-users"></i>
								<span>User</span></a></li>
						<li><a href="product-list" class=""><i
								class="lnr lnr-gift"></i> <span>Product</span></a></li>
						<li><a href="order-list" class=""><i
								class="lnr lnr lnr-dice"></i> <span>Order</span></a></li>
						<li><a href="../client/home" class=""><i
								class="lnr lnr-store"></i> <span>Go to shop</span></a></li>
						<li><a href="" class=""><i class="lnr lnr-cog"></i> <span>Setting</span></a></li>
					</ul>
				</nav>
			</div>
		</div>
	</div>
	<!-- END LEFT SIDEBAR -->
</body>
</html>