<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
	<script src="https://cdn.ckeditor.com/ckeditor5/35.4.0/classic/ckeditor.js"></script>
	
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

<link rel="stylesheet" type="text/css"
	href="<c:url value='../resource/css/style.css'/>">
</head>
<body>
	<div id="wrapper" style="max-width: 1250px; margin: auto;">
		<%@include file="../common/header.jsp"%>
		<jsp:include page="../common/category.jsp" />
		<!-- MAIN -->
		<!-- MAIN CONTENT -->
		<div class="main">
			<div class="main-content">
				<div class="container-fluid">
					<!-- OVERVIEW -->
					<div class="panel panel-headline">
						<div class="panel-heading"
							style="display: flex; justify-content: space-between;">
							<h3 class="panel-title">Cập nhập sản phẩm</h3>
							<a class="btn btn-warning" href="product-list"
								style="background-color: #D9534F; padding: 2px 10px; text-decoration: none; border: none; margin-right: 10px; height: 25px;">Back</a>
						</div>
						<form action="product-update" method="post"
							enctype="multipart/form-data">
							<div class="row">
								<table style="margin: auto; margin-left: 60px;" class="col-md-6">
									<tr>
										<th>Danh mục:</th>
										<td><select name="categoryId">
												<c:forEach items="${categories}" var="category">
													<option
														<c:if test="${product.categoryDTO.categoryId == category.categoryId}">
															selected="selected"
														</c:if>
														value="${category.categoryId}">${category.categoryName}</option>
												</c:forEach>
										</select></td>
									</tr>
									<tr>
										<th>Tên sản phẩm:</th>
										<td><input type="text" class="form-control"
											required="required" style="height: 30px;"
											placeholder="Nhập Tên sản phẩm..." name="productName"
											value="${product.productName}" />
											<input type="hidden" name="productId" value="${product.productId}">
											</td>
									</tr>
									<tr>
										<th>Mô tả:</th>
										<td>
										<textarea id = "editor" name="description">${product.description}</textarea>
										<!--
										<textarea required="required" class="form-control"
												placeholder="Nhập mô tả sản phẩm..." rows="4"
												name="description">${product.description}</textarea></td>
										  -->									
									</tr>

									<tr>
										<th>Giá:</th>
										<td><input type="text" class="form-control" 
											style="height: 30px; width: 230px;"
											placeholder="<fmt:formatNumber value = '${product.price}' maxIntegerDigits='10'   /> VNĐ" name="newPrice" />
											<input type="hidden" name="oldPrice" value="${product.price}">
											</td>
									</tr>
									<tr>
										<th>Số lượng:</th>
										<td><input type="text" class="form-control"
											required="required" style="height: 30px; width: 230px;"
											placeholder="0" name="quantity" value="${product.quantity}" /></td>
									</tr>
									<tr>
										<th>Hình ảnh:</th>
										<td><img src="../download?image=${product.image}"
											style="width: 20%; margin-top: -20px;"> <input
											type="hidden" name="image" value="${product.image}" /> 
											<input type="file" name="imageFile" />
											<input type="hidden" name="image" value="${product.image}">
									</tr>
									<tr>
										<th>Giảm giá:</th>
										<td><select name="saleId">
												<c:forEach items="${sales}" var="sale">
													<option 
													<c:if test="${product.saleDTO.saleId == sale.saleId}">
														selected="selected"
													</c:if>
													value="${sale.saleId}">${sale.salePercent}%</option>
												</c:forEach>
										</select> <a style="margin-left: 10px;" href="">More Giảm giá</a></td>
									</tr>
									<tr>
										<th></th>
										<td>
											<button type="submit" class="btn btn-primary"
												style="font-weight: bold;">UPDATE</button>
										</td>
									</tr>
									<tr>
										<th></th>
										<td></td>
									</tr>
								</table>
							</div>
						</form>
					</div>

				</div>
				<div id="headline-chart" class="ct-chart"></div>
			</div>

			<!-- END OVERVIEW -->
		</div>
		<!-- END MAIN CONTENT -->
	</div>
	<script>
    ClassicEditor
        .create( document.querySelector( '#editor' ) )
        .catch( error => {
            console.error( error );
        } );
</script>
</body>

</html>
</html>