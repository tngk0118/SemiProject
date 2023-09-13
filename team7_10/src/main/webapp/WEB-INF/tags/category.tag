<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<c:forEach var="cdatas" items="${cdatas}">
<li class="pb-3"><a
	class="collapsed d-flex justify-content-between h3 text-decoration-none"
	href="#"> ${cdatas.categoryName} <i class="fa fa-fw fa-chevron-circle-down mt-1"></i>
</a>
	<ul class="collapse show list-unstyled pl-3">
		<c:forEach var="cddatas" items="${cddatas}">
			<c:if test="${cdatas.categoryNum eq cddatas.categoryNum}">
			<li><a class="text-decoration-none" href="shopPage.do?categorydetailNum=${cddatas.categoryDetailNum}">${cddatas.categoryDetailName}</a></li>
			</c:if>
		</c:forEach>
	</ul></li>
</c:forEach>