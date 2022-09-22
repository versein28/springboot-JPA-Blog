<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="mb-3 text-center">
    <h5>상품 주문서</h5>
</div>

<div class="container row orderContainer">
    <div class="col">
        <button class="btn btn-secondary ml-5" onclick="history.back()">돌아가기</button>
        <c:if test="${product.user.id == principal.user.id}">
            <a href="/product/${product.id}/updateForm" class="btn btn-warning">수정</a>
            <button id="btn-delete" class="btn btn-danger">삭제</button>
        </c:if>
        <img class="mt-2" src="${product.filePath}" id="preview-lg" onerror="this.onerror=null; this.src='https://via.placeholder.com/400.jpg';" alt="" /> <input type="hidden" id="userName" value="${product.user.username}">
        <div class="form-group ml-5">
            글 번호 : <span id="id">${product.id}</span> 작성자 : <span>${product.user.username}</span>
        </div>
    </div>
    <div class="col">
        <div class="form-group orderForm">
            <label for="prodName">상품명</label>
            <input type="text" class="form-control" value="${product.prodName}" id="prodName" readonly>
            <label for="prodBrand">브랜드명</label>
            <input type="text" class="form-control" value="${product.prodBrand}" id="prodBrand" readonly>
            <label>주소</label>
            <input class="form-control btn btn-light" type="button" onclick="DaumPostcode()" value="우편번호 찾기">
            <input class="form-control w-30" type="text" id="roadAddress" placeholder="도로명주소"> <input class="form-control w-30" type="text" id="jibunAddress" placeholder="지번주소">
            <span id="guide" type="hidden"></span> <input class="form-control" type="text" id="detailAddress" placeholder="상세주소"> <input class="form-control" type="text" id="extraAddress" placeholder="참고항목">
            <label for="buyerTel">휴대폰번호</label>
            <div class="row">
                <input type="text" class="form-control col mr-3" id="buyerTel1" maxlength="4" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');">
                <input type="text" class="form-control col mr-3" id="buyerTel2" maxlength="4" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');">
                <input type="text" class="form-control col" id="buyerTel3" maxlength="4" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');">
            </div>
            <label for="quantity">수량</label>
            <input type="number" class="form-control" id="quantity" min="1" value="1">
            <label for="prodKrw">가격</label>
            <input type="text" id="prodKrw" class="form-control" value="<fmt:formatNumber value="${product.prodKrw}" pattern="#,###"/>" readonly>
        </div>
    </div>
    <div class="col">
        <h6>결제 안내</h6>
        <c:choose>
            <c:when test="${product.qty != 0}">
                <button id="card" class="btn btn-outline-primary m-1" onclick="import_inicis(this)">신용카드 결제</button>
                <button id="phone" class="btn btn-outline-primary m-1" onclick="import_inicis(this)">휴대폰 결제</button>
                <button id="vbank" class="btn btn-outline-primary m-1" onclick="import_inicis(this)" disabled>가상계좌 결제</button>
                <button id="trans" class="btn btn-outline-primary m-1" onclick="import_inicis(this)">실시간계좌이체</button>
            </c:when>
            <c:when test="${product.qty == 0}">
                <button class="btn btn-light" disabled>매진된 상품입니다</button>
            </c:when>
        </c:choose>
        <h6 class="mt-3">기타</h6>
        <button id="cart-save" class="btn btn-light">장바구니에 담기</button>
    </div>
</div>
</section>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    //본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
    function DaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var roadAddr = data.roadAddress; // 도로명 주소 변수
                var extraRoadAddr = ''; // 참고 항목 변수

                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
                    extraRoadAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if (data.buildingName !== '' && data.apartment === 'Y') {
                    extraRoadAddr += (extraRoadAddr !== '' ? ', ' +
                        data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if (extraRoadAddr !== '') {
                    extraRoadAddr = ' (' + extraRoadAddr + ')';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('roadAddress').value = data.zonecode;
                document.getElementById("roadAddress").value = roadAddr;
                document.getElementById("jibunAddress").value = data.jibunAddress;

                // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
                if (roadAddr !== '') {
                    document.getElementById("extraAddress").value = extraRoadAddr;
                } else {
                    document.getElementById("extraAddress").value = '';
                }

                var guideTextBox = document.getElementById("guide");
                // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
                if (data.autoRoadAddress) {
                    var expRoadAddr = data.autoRoadAddress +
                        extraRoadAddr;
                    guideTextBox.innerHTML = '(예상 도로명 주소 : ' +
                        expRoadAddr + ')';
                    guideTextBox.style.display = 'block';

                } else if (data.autoJibunAddress) {
                    var expJibunAddr = data.autoJibunAddress;
                    guideTextBox.innerHTML = '(예상 지번 주소 : ' +
                        expJibunAddr + ')';
                    guideTextBox.style.display = 'block';
                } else {
                    guideTextBox.innerHTML = '';
                    guideTextBox.style.display = 'none';
                }
            }
        }).open();
    }
</script>

<script src="/js/cart.js"></script>
<script src="/js/import.js"></script>
<script src="/js/product.js"></script>
<%@ include file="../layout/footer.jsp"%>