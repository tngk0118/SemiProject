<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<script src="https://code.jquery.com/jquery-3.7.0.min.js"
	integrity="sha256-2Pmvv0kuTBOenSvLm6bvfBSSHrUJ+3A7x6P5Ebd07/g="
	crossorigin="anonymous"></script>
	
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<c:if test="${empty memberId}">
	<form class="col-md-9 m-auto" action="signup.do" method="post"
		role="form">
		<div class="input-group mb-3">
			<span class="input-group-text"><i
				class="fa fa-fw fa-user text-dark"></i></span> <input type="text"
				name="memberId" class="form-control" placeholder="ID"
				aria-label="ID" aria-describedby="basic-addon1" required />
		</div>
		<div class="input-group mb-3">
			<span class="input-group-text"><i
				class="fa fa-fw fa-lock text-dark"></i></span> <input type="password"
				name="memberPw" class="form-control" placeholder="Password"
				aria-label="Password" aria-describedby="basic-addon1" required />
		</div>
		<div class="input-group mb-3">
			<span class="input-group-text"><i
				class="fa fa-fw fa-id-card text-dark"></i></span> <input type="text"
				name="memberName" class="form-control" placeholder="Name"
				aria-label="Username" aria-describedby="basic-addon1" required />
		</div>
		<div class="input-group mb-3">
			<span class="input-group-text"><i
				class="fa fa-fw fa-mobile text-dark"></i></span> <input type="text"
				name="phoneNumber" class="form-control" placeholder="Mobile"
				aria-label="Username" aria-describedby="basic-addon1" required />
		</div>
		<div class="input-group mb-3">
			<span class="input-group-text">
				<i class="fa fa-fw fa-envelope text-dark"></i>
			</span>
				<input type="text" name="email" class="form-control" placeholder="email" aria-label="Email" required />
				<span class="input-group-text">@</span>
				<input class="form-control" name="domaintxt" id="domain-txt" type="text" required />
				<select class="form-select" name="domainlist" id="domain-list">
				<option value="type">직접 입력</option>
				<option value="naver.com">naver.com</option>
				<option value="gmail.com">gmail.com</option>
				<option value="hanmail.net">hanmail.net</option>
				<option value="nate.com">nate.com</option>
				<option value="kakao.com">kakao.com</option>
			</select>
		</div>
		<div class="input-group mb-3">
			<span class="input-group-text">
			<i class="fa fa-fw fa-map-marker text-dark"></i>
			</span>
			<input class="form-control" type="text" id="postcode" name="zipcode" placeholder="우편번호" readonly />
			<input class="btn btn-success btn-lg px-3" type="button" onclick="execDaumPostcode()" value="우편번호 찾기" />
		</div>
		<div class="input-group mb-3">
			<input class="form-control" type="text" id="address" placeholder="기본주소" name="address" readonly />
		</div>
		<div class="input-group mb-3">
			<input class="form-control" type="text" id="addressdetail" name="addressdetail" placeholder="상세주소" />
		</div>
		<div class="input-group mb-3" style="justify-content: flex-end;">
			<input type="submit" class="btn btn-success btn-lg px-3" value="회원가입">
		</div>
	</form>
</c:if>



<c:if test="${not empty memberId}">

	<form class="col-md-9 m-auto" action="updateMember.do" method="post"
		role="form">

		<div class="input-group mb-3">
			<span class="input-group-text"><i
				class="fa fa-fw fa-user text-dark"></i></span> <input type="text"
				name="memberId" class="form-control" placeholder="ID"
				aria-label="ID" aria-describedby="basic-addon1"
				value="${mdata.memberId}" readonly />
		</div>
		<c:set var="memberId" value="${memberId}" />
		<c:choose>
			<c:when test="${fn:contains(memberId,'G@')}">
				<div class="input-group mb-3">
					<span class="input-group-text"><i
						class="fa fa-fw fa-lock text-dark"></i></span> <input type="password"
						name="tmpPw" class="form-control"
						placeholder="구글회원은 비밀번호를 변경 할 수 없습니다." aria-label="Password"
						aria-describedby="basic-addon1" readonly />
				</div>
			</c:when>
			<c:otherwise>
				<div class="input-group mb-3">
					<span class="input-group-text"><i
						class="fa fa-fw fa-lock text-dark"></i></span> <input type="password"
						name="tmpPw" class="form-control" placeholder="NEW Password"
						aria-label="Password" aria-describedby="basic-addon1" />
				</div>
			</c:otherwise>
		</c:choose>
		<div class="input-group mb-3">
			<span class="input-group-text"><i
				class="fa fa-fw fa-id-card text-dark"></i></span> <input type="text"
				name="memberName" class="form-control" placeholder="Name"
				aria-label="Username" aria-describedby="basic-addon1"
				value="${mdata.memberName}" required />
		</div>

		<div class="input-group mb-3">
			<span class="input-group-text"><i
				class="fa fa-fw fa-mobile text-dark"></i></span> <input type="text"
				name="phoneNumber" class="form-control" placeholder="Mobile"
				aria-label="Username" aria-describedby="basic-addon1"
				value="${mdata.phonenumber}" required />
		</div>

		<div class="input-group mb-3">
			<span class="input-group-text">
				<i class="fa fa-fw fa-envelope text-dark"></i>
			</span>
				<input type="text" name="email" class="form-control" placeholder="email" aria-label="Email" value="${mdata.email}" required />
				<span class="input-group-text">@</span>
				<input class="form-control" name="domaintxt" id="domain-txt" type="text" value="${domain}" required />
				<select class="form-select" name="domainlist" id="domain-list">
				<option value="type">직접 입력</option>
				<option value="naver.com">naver.com</option>
				<option value="gmail.com">gmail.com</option>
				<option value="hanmail.net">hanmail.net</option>
				<option value="nate.com">nate.com</option>
				<option value="kakao.com">kakao.com</option>
			</select>
			</select>
		</div>
		<div class="input-group mb-3">
			<span class="input-group-text">
			<i class="fa fa-fw fa-map-marker text-dark"></i>
			</span>
			<input class="form-control" type="text" id="postcode" name="zipcode" placeholder="우편번호" value="${mdata.zipcode}" readonly />
			<input class="btn btn-success btn-lg px-3" type="button" onclick="execDaumPostcode()" value="우편번호 찾기" />
		</div>
		<div class="input-group mb-3">
			<input class="form-control" type="text" id="address" placeholder="기본주소" name="address" value="${mdata.address}" readonly />
		</div>
		<div class="input-group mb-3">
			<input class="form-control" type="text" id="addressdetail" name="addressdetail" placeholder="상세주소" value="${mdata.addressdetail}" />
		</div>
		<div class="input-group mb-3" style="justify-content: flex-end;">
			<input type="submit" class="btn btn-success btn-lg px-3" value="변경하기">
		</div>
	</form>
</c:if>
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