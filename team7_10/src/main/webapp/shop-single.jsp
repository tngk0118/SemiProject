<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="slk"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">

<head>
<title>${pdata.productName}</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="apple-touch-icon" href="assets/img/apple-icon.png">
<link rel="shortcut icon" type="image/x-icon"
	href="assets/img/logo-no-background.ico">

<link rel="stylesheet" href="assets/css/bootstrap.min.css">
<link rel="stylesheet" href="assets/css/templatemo.css">
<link rel="stylesheet" href="assets/css/custom.css">

<!-- Load fonts style after rendering the layout styles -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;200;300;400;500;700;900&display=swap">
<link rel="stylesheet" href="assets/css/fontawesome.min.css">

<!-- Slick -->
<link rel="stylesheet" type="text/css" href="assets/css/slick.min.css">
<link rel="stylesheet" type="text/css" href="assets/css/slick-theme.css">
<!--
    
TemplateMo 559 Zay Shop

https://templatemo.com/tm-559-zay-shop

-->
</head>

<body>
	<!-- Start Top Nav -->
	<slk:nav />
	<!-- Close Top Nav -->


	<!-- Header -->
	<slk:header />
	<!-- Close Header -->

	<!-- Modal -->
	<slk:modal />

	<!-- Open Content -->
	<section class="bg-light" style="background-color: #eeeeee !important;">
		<div class="container pb-5">
			<div class="row">
				<div class="col-lg-5 mt-5">
					<!--  <div class="card mb-3">
                        <img class="card-img img-fluid" src="assets/img/product_single_10.jpg" alt="Card image cap" id="product-detail">
                    </div> -->
					<div class="row">
						<!--Start Controls-->
						<div class="col-1 align-self-center">
							<a href="#multi-item-example" role="button" data-bs-slide="prev">
								<i class="text-dark fas fa-chevron-left"></i> <span
								class="sr-only">Previous</span>
							</a>
						</div>
						<!--End Controls-->
						<!--Start Carousel Wrapper-->
						<div id="multi-item-example"
							class="col-10 carousel slide carousel-multi-item"
							data-bs-ride="carousel">
							<!--Start Slides-->
							<div class="carousel-inner product-links-wap" role="listbox">

								<!--First slide-->

								<div class="carousel-item active">
									<div class="row">
										<div class="mb-3">
											<a href="${pdata.path}"><img class="card-img img-fluid"
												src="${pdata.path}" alt="Product Image">
											</a>
										</div>
									</div>
								</div>

								<!--/.First slide-->

								<!--Second slide-->
								<c:forEach var="i" items="${idatas}">
									<div class="carousel-item">
										<div class="row">
											<div class="mb-3">
												<a href="${i.path}"> <img class="card-img img-fluid"
													src="${i.path}" alt="Product Images">
												</a>
											</div>
										</div>
									</div>
								</c:forEach>
							</div>
							<!--End Slides-->
						</div>
						<div class="col-1 align-self-center">
							<a href="#multi-item-example" role="button" data-bs-slide="next">
								<i class="text-dark fas fa-chevron-right"></i> <span
								class="sr-only">Next</span>
							</a>
						</div>
						<!--End Controls-->
					</div>
				</div>
				<!--End Carousel Wrapper-->
				<!--Start Controls-->

				<!-- col end -->
				<div class="col-lg-7 mt-5">
					<div class="card">
						<div class="card-body">
							<h1 class="h2">${pdata.productName}</h1>
							<fmt:formatNumber value="${pdata.productPrice}" />
							&nbsp;원
							</p>
							<!--  <p class="py-2">
                                <i class="fa fa-star text-warning"></i>
                                <i class="fa fa-star text-warning"></i>
                                <i class="fa fa-star text-warning"></i>
                                <i class="fa fa-star text-warning"></i>
                                <i class="fa fa-star text-secondary"></i>
                                <span class="list-inline-item text-dark">Rating 4.8 | 36 Comments</span>
                            </p> -->
							<ul class="list-inline">
								<li class="list-inline-item">
									<h6>Brand</h6>
								</li>
								<li class="list-inline-item">
									<p class="text-dark">
										<strong>${pdata.company}</strong>
									</p>
								</li>
							</ul>

							<ul class="list-inline">
								<li class="list-inline-item">
									<h6>Description</h6>
								</li>
								<li class="list-inline-item">
									<p class="text-dark">${pdata.productExplain}</p>
								</li>
							</ul>
							<!--     <h6>Specification:</h6>
                            <ul class="list-unstyled pb-3">
                                <li>Lorem ipsum dolor sit</li>
                                <li>Amet, consectetur</li>
                                <li>Adipiscing elit,set</li>
                                <li>Duis aute irure</li>
                                <li>Ut enim ad minim</li>
                                <li>Dolore magna aliqua</li>
                                <li>Excepteur sint</li>
                            </ul> -->

							<form action="" method="GET">
								<input type="hidden" name="product-title" value="Activewear">
								<div class="row">
									<div class="col-auto">
										<ul class="list-inline pb-3">
											<li class="list-inline-item text-right">
												<h6>Quantity</h6> <input type="hidden"
												name="product-quanity" id="product-quanity" value="1">
											</li>
											<li class="list-inline-item"><span
												class="btn btn-success" id="btn-minus">-</span></li>
											<li class="list-inline-item"><span
												class="badge bg-secondary" id="var-value">1</span></li>
											<li class="list-inline-item"><span
												class="btn btn-success" id="btn-plus">+</span></li>
										</ul>
									</div>
								</div>
								<c:if test="${empty memberId}">
									<div class="row pb-3">
									<!--<div class="col d-grid">
											<a href="loginPage.do" class="btn btn-success btn-lg px-3">Buy</a>
										</div> -->
										<div class="col d-grid">
											<a href="error.do" class="btn btn-success btn-lg px-3">Add
												to Cart</a>
										</div>
									</div>
								</c:if>
								<c:if test="${not empty memberId}">
									<div class="row pb-3">
										<%-- <div class="col d-grid">
											<a href="payPage.do?productNum=${pdata.productNum}"
												class="btn btn-success btn-lg px-3">Buy</a>
										</div> --%>
										<div class="col d-grid">
											<a class="btn btn-success btn-lg px-3 ${pdata.productNum}">Add
												to Cart</a>
										</div>
									</div>
								</c:if>
							</form>

						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- Close Content -->

	<!-- Start Article -->
	<section class="py-5">
		<div class="container">
			<div class="row text-left p-2 pb-3">
				<h4>추천 상품</h4>
			</div>
			<!--Start Carousel Wrapper-->
			<div class="row">
				<slk:product />
			</div>
		</div>	
	</section>
	<!-- End Article -->


	<slk:footer />

	<!-- Start Script -->
	<script src="assets/js/jquery-1.11.0.min.js"></script>
	<script src="assets/js/jquery-migrate-1.2.1.min.js"></script>
	<script src="assets/js/bootstrap.bundle.min.js"></script>
	<script src="assets/js/templatemo.js"></script>
	<script src="assets/js/custom.js"></script>
	<!-- End Script -->

	<!-- Start Slider Script -->
	<script src="assets/js/slick.min.js"></script>
	<script>
        $('#carousel-related-product').slick({
            infinite: true,
            arrows: false,
            slidesToShow: 4,
            slidesToScroll: 3,
            dots: true,
            responsive: [{
                    breakpoint: 1024,
                    settings: {
                        slidesToShow: 3,
                        slidesToScroll: 3
                    }
                },
                {
                    breakpoint: 600,
                    settings: {
                        slidesToShow: 2,
                        slidesToScroll: 3
                    }
                },
                {
                    breakpoint: 480,
                    settings: {
                        slidesToShow: 2,
                        slidesToScroll: 3
                    }
                }
            ]
        });
        
        $(".btn.btn-success.btn-lg.px-3."+${pdata.productNum}).on("click",function(){
        	var a = $(".badge.bg-secondary").text();
        	$.ajax({
        	      url: 'addCartlist.do?productNum='+${pdata.productNum}+'&cartcount='+a,
        	      type:'POST',
        	      success:function(result){
        	         console.log(result);
        	         if(result=='insert'){
        	            console.log('장바구니 추가 성공');
        	         }else{
        	            console.log('장바구니 추가 실패');
        	         }
        	      },
        	      error:function(error){
        	         console.log(error);
        	      }
        	   });
        });
    </script>
	<!-- End Slider Script -->

</body>

</html>