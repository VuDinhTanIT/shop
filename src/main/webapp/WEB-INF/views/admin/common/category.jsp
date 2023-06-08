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
						<li><a href="stat" class=""> <i
								class="lnr lnr-gift"></i> <span>Thống kê</span>
						</a>
							<ul>
								<li><a href="month-stat">Theo tháng</a></li>
								<li><a href="best-selling-products-stat">Thống kê theo tháng</a></li>
							</ul></li>
						<li><a href="../client/home" class=""><i
								class="lnr lnr-store"></i> <span>Go to shop</span></a></li>
						<li><a href="" class=""><i class="lnr lnr-cog"></i> <span>Setting</span></a></li>
					</ul>
				</nav>
			</div>
		</div>
	</div>
	<!-- END LEFT SIDEBAR -->
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script>
	$(document).ready(function() {
		  $('.sidebar .nav > li > a[href="#stat"]').click(function() {
		    $(this).next('ul').slideToggle();
		  });
		  $('.nav li:has(ul) > a').click(function(e) {
		    if (!$(this).parent().hasClass('active')) {
		      e.preventDefault();
		      $('.nav li ul').slideUp();
		      $('.nav li').removeClass('active');
		      $(this).parent().addClass('active');
		      $(this).next('ul').slideDown();
		    } else {
		      $(this).next('ul').slideUp();
		      $(this).parent().removeClass('active');
		    }
		    e.stopPropagation();
		  });
		});
	</script>
</body>
</html>