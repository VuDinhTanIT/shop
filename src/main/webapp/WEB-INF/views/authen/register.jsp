<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="resource/common/authen.css">
<script src='https://kit.fontawesome.com/a076d05399.js'></script>
</head>
<body style="background-image: url(resource/client/images/bg.jpg);">
	<!-- Authen form -->
	<div class="auth-form" style="background-color: white;">
		<form action="register" method="post">
			<div class="auth-form-container">
				<div class="auth-form-header">
					<h3 class="auth-form-title">Register</h3>
					<a href="login" class="auth-form-subtitle">Login</a>
				</div>
				<p style="color: red; margin-top: -20px; margin-left: 3x; margin-bottom: 5px;">${error}</p>

				<div class="auth-form-form">
					<div class="auth-form-group">
						<input class="auth-form-input" type="email" name="email"
							placeholder="Email" required="required" value="${email}"/>
					</div>
					<div class="auth-form-group">
						<input class="auth-form-input" type="password" name="password"
							placeholder="Password" required="required" />
					</div>
					<div class="auth-form-group">
						<input class="auth-form-input" type="password" name="repassword"
							placeholder="Re-password" required="required" />
					</div>
				</div>

				<div class="auth-form-controls">
					<a href="client/home" class="btn">Back</a> <button type="submit" style="cursor: pointer;"
						class="btn">Register</button>
				</div>
			</div>
		</form>
		<div class="auth-form-social">
			<a href="#" class="btn-social-fb"><i class='fab'
				style="font-size: 20px;">&#xf09a;</i> Login with Facebook </a> <a
				href="#" class="btn-social-gg"><i class='fab'
				style='font-size: 20px; color: tomato'>&#xf2b3;</i> Login with
				Google </a>
		</div>
	</div>
</body>
</html>