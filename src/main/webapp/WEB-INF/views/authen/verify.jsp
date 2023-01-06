<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Electronic Shop</title>
<link rel="stylesheet" type="text/css" href="resource/common/authen.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src='https://kit.fontawesome.com/a076d05399.js'></script>
</head>
<body style="background-image: url(resource/client/images/bg.jpg);">
	<!-- Authen form -->
	<form action="verify" method="post">
		<div class="auth-form" style="background-color: white;">
			<div class="auth-form-container">
				<a href="client/home"><h5 class="auth-form-title"
						style="text-align: center; padding-top: 20px;">Welcome to
						Shop</h5></a>
				<hr style="margin-top: -35px;" />
				<p style="font-size: 18px; text-align: center;">
					Chúng tôi vừa gửi mã xác nhận tới <span style="font-weight: bold;">${sessionScope.emailRegister}</span>
				</p>
				<div class="auth-form-form"
					style="margin-top: 15px; margin-bottom: -13px;">
					<div class="auth-form-group">
						<c:if test="${resend != null}">
							<div style="display: flex; margin-bottom: 2px; color: #0101DF;">
								<i class="material-icons" style="font-size: 19px;">&#xe88f;</i>
								<span>Kiểm tra email của bạn.</span>
							</div>
						</c:if>

						<c:if test="${verifyFail != null}">
							<div style="display: flex; margin-bottom: 2px; color: red;">
								<i style="font-size: 18px" class="fa">&#xf071;</i> <span
									style="margin-left: 2px;">${verifyFail}</span>
							</div>
						</c:if>

						<c:if test="${verifySuccess != null}">
							<div style="display: flex; margin-bottom: 2px; color: #339900;">
								<i style='font-size: 19px' class='fas'>&#xf058;</i> <span>${verifySuccess}</span>
								<a href="login" style="margin-left: 10px;"> Đăng nhập</a>
							</div>
						</c:if>

						<input class="auth-form-input" type="text" name="code"
							placeholder="Enter code" required="required" />
					</div>
				</div>
				<div style="font-size: 17px; text-align: center;">
					<span>Không hoạt động<a href="resend-code">Gửi mã khác.</a>
					</span>
				</div>
				<div class="auth-form-controls"
					style="margin-left: 80px; margin-top: 15px;">
					<button type="submit" class="btn" style="cursor: pointer;">Xác
						nhận</button>
				</div>
				<div style="padding-bottom: 20px;"></div>
			</div>
		</div>
	</form>
</body>
</html>
