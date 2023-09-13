<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="slk"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>SoundPlay | 결제</title>

<link rel="apple-touch-icon" href="assets/img/apple-icon.png" />
<link rel="shortcut icon" type="image/x-icon"
	href="assets/img/logo-no-background.ico" />

<link rel="stylesheet" href="assets/css/bootstrap.min.css" />
<link rel="stylesheet" href="assets/css/templatemo.css" />
<link rel="stylesheet" href="assets/css/custom.css" />
<link rel="stylesheet" href="assets/css/join.css" />


<!-- Load fonts style after rendering the layout styles -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;200;300;400;500;700;900&display=swap" />
<link rel="stylesheet" href="assets/css/fontawesome.min.css" />

<!-- Load map styles -->
<link rel="stylesheet"
	href="https://unpkg.com/leaflet@1.7.1/dist/leaflet.css"
	integrity="sha512-xodZBNTC5n17Xt2atTPuE1HxjVMSvLVW9ocqUKLsCC5CXdbqCmblAshOMAS6/keqq/sMZMZ19scR4PsZChSR7A=="
	crossorigin="" />

<script src="https://code.jquery.com/jquery-3.7.0.min.js"
   integrity="sha256-2Pmvv0kuTBOenSvLm6bvfBSSHrUJ+3A7x6P5Ebd07/g="
   crossorigin="anonymous"></script>

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

	<!-- Start Content Page -->
	<div class="container-fluid bg-dark py-5 text-light">
		<div class="col-md-6 m-auto text-center">
			<h1 class="h1">결제 페이지</h1>
		</div>
	</div>
	<!-- Start Contact -->
	<!-- Shopping Cart Section Begin -->
	<div class="shopping-cart spad">
		<div class="container py-5">
			<div class="shopping__cart__table">
				<table class="table">
					<thead class="table-dark">
						<tr>
							<th></th>
							<th>상품</th>
							<th>수량</th>
							<th>가격</th>
						</tr>
					</thead>
					<tbody style="vertical-align: middle;">
						<slk:order />
					</tbody>
					<tbody>
					<tr>
					<c:set var="total" value="0" />
						<c:forEach var="v" items="${cdatas}">
							<c:set var="price" value="${v.productPrice*v.cartCount}" />
							<c:set var="total" value="${total+price}" />
						</c:forEach>
					 <fmt:formatNumber value="${total}" var="formProductPrice"/>
						 <td></td>
						 <td></td>
						 <td><h5 class="text-right mt-3 mb-3">총 금액</h5></td>
						 <td><h5 class="text-right mt-3 mb-3 price">${formProductPrice}&nbsp;원</h5></td>
					</tr>
					</tbody>
				</table>
			</div>
			<table class="table">
					
			</table>
			<table class="table">
				<thead class="table-dark">
					<tr>
						<th>주문 정보</th>
					</tr>
				</thead>
			</table>
			<table>
				<tr>
					<td class="product__cart__item">
						<div class="container">
							<div class="row py-4">
								<!--  <span id="guide" style="color: #999; display: none"></span>-->
								<label for="basic-url" class="form-label">주문자 이름</label>
									<div class="input-group mb-3">
										<input class="form-control" type="text" name="memberName" value="${mdata.memberName}" />
									</div>
									<label for="basic-url" class="form-label">핸드폰 번호</label>
									<div class="input-group mb-3">
										<input class="form-control" type="text" name="phoneNumber" value="${mdata.phonenumber}"/>
									</div>
								<div class="input-group mb-3">
									<input class="btn btn-success btn-lg px-3" type="button"
										onclick="execDaumPostcode()" value="우편번호 찾기" />
								</div>
								<label for="basic-url" class="form-label">우편번호</label>
								<div class="input-group mb-3">
									<input class="form-control" type="text" id="postcode" name="zipcode" value="${mdata.zipcode}"readonly required/>
								</div>
								<label for="basic-url" class="form-label">주소</label>
								<div class="input-group mb-3">
									<input class="form-control" type="text" id="address" name="address" value="${mdata.address}"
										readonly required/>
								</div>
								<label for="basic-url" class="form-label">상세주소</label>
								<div class="input-group mb-3">
									<input class="form-control" type="text" name="addressdetail" name="addressdetail" value="${mdata.addressdetail}" required/> 
								</div>
								
							</div>
						</div>
					</td>
				</tr>
			</table>
			<div class="container py-5">
				 <div id="payment-method"></div>
				 <div style="text-align:right;">
				 <a id="payment-request-button" class="btn btn-success btn-lg px-3" >결제하기</a>
				 </div>
			</div>
		</div>
	</div>

	<slk:footer />


	<!-- Start Script -->
	<script src="https://js.tosspayments.com/v1/payment-widget"></script>
	<script src="https://cdn.ckeditor.com/ckeditor5/38.1.0/classic/ckeditor.js"></script>
	<script src="https://cdn.ckeditor.com/ckeditor5/34.0.0/classic/translations/ko.js"></script>
	<script src="https://cdn.jsdelivr.net/gh/alpinejs/alpine@v2.x.x/dist/alpine.min.js" defer></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.3/Chart.min.js" defer></script>
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

	<script src="assets/js/jquery-1.11.0.min.js"></script>
	<script src="assets/js/jquery-migrate-1.2.1.min.js"></script>
	<script src="assets/js/bootstrap.bundle.min.js"></script>
	<script src="assets/js/templatemo.js"></script>
	<script src="assets/js/custom.js"></script>
	<script src="assets/js/join.js"></script>
	<script>
    function execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
            	
            	var addr = '';
            	
                var roadAddr = data.roadAddress; // 도로명 주소 변수
                var extraRoadAddr = ''; // 참고 항목 변수
                
              //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraRoadAddr += data.bname;
                }

                // 건물명이 있고, 공동주택일 경우 추가한다.
                if (data.buildingName !== '' && data.apartment === 'Y'){
                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }

                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if (extraRoadAddr !== ''){
                    extraRoadAddr = ' (' + extraRoadAddr + ')';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                $("#postcode").val(data.zonecode);
                $("#address").val(addr);


                // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
                var guideTextBox = $("#guide");
                if (data.autoRoadAddress) {
                    var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                    guideTextBox.html('(예상 도로명 주소 : ' + expRoadAddr + ')');
                    guideTextBox.css("display", "block");
                } else if (data.autoJibunAddress) {
                    var expJibunAddr = data.autoJibunAddress;
                    guideTextBox.html('(예상 지번 주소 : ' + expJibunAddr + ')');
                    guideTextBox.css("display", "block");
                } else {
                    guideTextBox.css("display", "none");
                }
            }
        }).open({autoClose: true});
    }
</script>
 <script>
      const paymentWidget = PaymentWidget(
        "test_ck_D5GePWvyJnrK0W0k6q8gLzN97Eoq",
        // 비회원 customerKey
        PaymentWidget.ANONYMOUS
      );

      /**
       * 결제창을 렌더링합니다.
       * @docs https://docs.tosspayments.com/reference/widget-sdk#renderpaymentmethods%EC%84%A0%ED%83%9D%EC%9E%90-%EA%B2%B0%EC%A0%9C-%EA%B8%88%EC%95%A1
       */
      paymentWidget.renderPaymentMethods("#payment-method", { value: ${total} });

      const paymentRequestButton = document.getElementById("payment-request-button");
      paymentRequestButton.addEventListener("click", () => {
        /** 결제 요청
         * @docs https://docs.tosspayments.com/reference/widget-sdk#requestpayment%EA%B2%B0%EC%A0%9C-%EC%A0%95%EB%B3%B4
         */
        paymentWidget.requestPayment({
          orderId: generateRandomString(),
          orderName: "토스 티셔츠 외 "+${fn:length(cdatas)-1},
      	  /*customerEmail: ${mdata.email},
          customerName: ${mdata.memberName}, */
          successUrl: "http://localhost:8080/team7_10/pay.do",
          failUrl: "http://localhost:8080/team7_10/fail.jsp",
        });
      });

      function generateRandomString() {
        return window.btoa(Math.random()).slice(0, 20);
      }
    </script>
	<!-- End Script -->
</body>
</html>