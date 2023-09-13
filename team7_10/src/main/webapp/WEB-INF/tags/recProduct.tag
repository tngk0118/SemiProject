<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:forEach var="pdatas" items="${pdatas}">
	<div class="col-12 col-md-4 mb-4">
		<div class="card h-100">
			<a href="shopSinglePage.do?productNum=${pdatas.productNum}">
			<img src="assets/img/PRODUCT${pdatas.productNum} (1).jpeg" class="card-img-top" alt="${pdatas.productNum}번 상품">
			</a>
			<div class="card-body">
				<ul class="list-unstyled d-flex justify-content-between">
					<!--별점 <li><i class="text-warning fa fa-star"></i> <i
						class="text-warning fa fa-star"></i> <i
						class="text-warning fa fa-star"></i> <i
						class="text-muted fa fa-star"></i> <i
						class="text-muted fa fa-star"></i></li> -->
					<li class="text-muted text-right">${pdatas.company}</li>
				</ul>
				<a href="shopSinglePage.do?productNum=${pdatas.productNum}" class="h2 text-decoration-none text-dark" style="font-size:20px !important;">${pdatas.productName}</a>
				<p class="card-text" style="font-size:16px !important;">${pdatas.productExplain}</p>
				<!--리뷰 <p class="text-muted">Reviews (48)</p> -->
			</div>
		</div>
	</div>
</c:forEach>