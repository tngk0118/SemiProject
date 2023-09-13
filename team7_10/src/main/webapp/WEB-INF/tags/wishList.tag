<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script src="https://code.jquery.com/jquery-3.7.0.min.js"
	integrity="sha256-2Pmvv0kuTBOenSvLm6bvfBSSHrUJ+3A7x6P5Ebd07/g="
	crossorigin="anonymous"></script>

<c:if test="${empty wdatas}">
	<!-- 비어있을경우 출력하도록 만들기  -->
	<tr>
		<td width=100% colspan="5"
			style="text-align: center; line-height: 300px; font-size: 30px;">
			<b>찜한 상품이 없습니다.</b>
		</td>
	</tr>
</c:if>
<c:if test="${not empty wdatas}">
	<c:forEach var="v" items="${wdatas}">
		<tr class="wishlist ${v.productNum}" style="vertical-align: middle;">
			<td class="product__cart__item"></td>
			<td class="product__cart__item">
				<div class="product__cart__item__pic">
					<img src="${v.path}" width="100px" height="100px">
				</div>
			</td>
			<td class="product__cart__item">
				<div class="product__cart__item__text">
					    <p class="text-light"><a href="shopSinglePage.do?productNum=${v.productNum}" style="color:#212529 !important;text-decoration: none !important;">${v.company}</a></p>
      	<p class="text-light"><a href="shopSinglePage.do?productNum=${v.productNum}" style="color:#212529 !important;text-decoration: none !important;">${v.productName}</a></p>
				</div>
			</td>
			<td class="cart__price"><fmt:formatNumber value="${v.productPrice}" />&nbsp;원</td>
			<td>
			<span class="btn btn-success ${v.productNum} btn-delete">x</span></td>
			<td class="cart__close"><i class="fa fa-close"></i></td>
		</tr>
		
		<script>
		$(".btn.btn-success."+${v.productNum}+".btn-delete").on("click",function(){
	console.log("위시리스트 삭제 콘솔");	
	$.ajax({
		url: 'deleteWishlist.do?productNum='+${v.productNum},
		type:'POST',
		success:function(result){
			console.log(result);
			if(result=='delete'){
				console.log('위시리스트 삭제 성공');
				<!-- td 없애버리기 -->
				$(".wishlist."+${v.productNum}).remove();
				location.reload();
			}else{
				console.log('위시리스트 삭제 실패');
			}
		},
		error:function(error){
			console.log(error);
		}
	});
});

</script>
	</c:forEach>
</c:if>
