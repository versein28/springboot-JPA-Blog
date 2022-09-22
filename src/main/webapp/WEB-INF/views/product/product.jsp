<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="container main">
    <form id="keyword" action="/auth/product" method="GET">
        <div class="mx-quto input-group m-3">
            <mx-auto> <input name="keyword" type="text" class="form-control" placeholder="검색어 입력" aria-label="search" aria-describedby="button-addon2" value="${param.keyword}"> </mx-auto>
            <input type="hidden" name="reverseOrder" value="${param.reverseOrder}"> <input type="hidden" name="category_name" value="${param.category_name}"> <input type="hidden" name="content_name" value="${param.content_name}">
            <button class="btn btn-info" type="submit" id="button-addon2">검색</button>
        </div>
    </form>
    <div class="dropdown mt-3">
        <button class="btn btn-light dropdown-toggle" type="button" data-toggle="dropdown" id="dropdownBtn">${content_name}</button>
        <div class="dropdown-menu">
            <a class="dropdown-item" href="/auth/product?keyword=${param.keyword}&reverseOrder=&category_name=id&content_name=최신상품순(default)">최신상품순(default)</a>
            <a class="dropdown-item" href="/auth/product?keyword=${param.keyword}&reverseOrder=true&category_name=id&content_name=오래된상품순">오래된상품순</a>
            <a class="dropdown-item" href="/auth/product?keyword=${param.keyword}&reverseOrder=&category_name=count&content_name=판매높은순">판매높은순</a>
            <a class="dropdown-item" href="/auth/product?keyword=${param.keyword}&reverseOrder=true&category_name=count&content_name=판매낮은순">판매낮은순</a>
            <a class="dropdown-item" href="/auth/product?keyword=${param.keyword}&reverseOrder=&category_name=prodKrw&content_name=가격높은순">가격높은순</a>
            <a class="dropdown-item" href="/auth/product?keyword=${param.keyword}&reverseOrder=true&category_name=prodKrw&content_name=가격낮은순">가격낮은순</a>
        </div>
        <a class="btn btn-light" type="button" href="/auth/product">전체보기</a>
    </div>
    <div class="card-group row w-100">
        <c:forEach var="product" items="${products.content}">
            <div id="card" class="card col-3 pointer" OnClick="location.href ='/product/${product.id}'" data-toggle="tooltip" data-placement="bottom" title="${product.prodBrand}">
                <div class="img-box">
                    <img class="card-img-top mt-1" src="${product.filePath}" onerror="this.onerror=null; this.src='https://via.placeholder.com/400.jpg';" alt="Card image cap" />
                </div>
                <div class="card-body">
                    <b class="card-title">${product.prodName}</b>
                    <p class="card-text">${product.prodBrand}</p>
                    <p class="card-text">
                        <fmt:formatNumber value="${product.prodKrw}" pattern="#,###" /> 원
                        <c:if test="${product.qty < 3 && product.qty != 0}">
                            <a class="soldOut">매진임박</a>
                        </c:if>
                        <c:if test="${product.qty == 0}">
                            <a class="soldOut">매진</a>
                        </c:if>
                    </p>
                    <small class="text-muted mt-3">등록일자:
                        <fmt:formatDate value="${product.createDate}" pattern="yyyy-MM-dd" />
                    </small>
                </div>
            </div>
        </c:forEach>
    </div>

    <ul class="pagination justify-content-center mt-5">
        <c:choose>
            <c:when test="${products.first}">
                <li class="page-item disabled"><a class="page-link" href="?keyword=${param.keyword}&reverseOrder=${param.reverseOrder}&category_name=${param.category_name}&content_name=${param.content_name}&page=${products.number-1}">Previous</a></li>
            </c:when>
            <c:otherwise>
                <li class="page-item"><a class="page-link" href="?keyword=${param.keyword}&reverseOrder=${param.reverseOrder}&category_name=${param.category_name}&content_name=${param.content_name}&page=${products.number-1}">Previous</a></li>
            </c:otherwise>
        </c:choose>

        <c:choose>
            <c:when test="${products.last}">
                <li class="page-item disabled"><a class="page-link" href="?keyword=${param.keyword}&reverseOrder=${param.reverseOrder}&category_name=${param.category_name}&content_name=${param.content_name}&page=${products.number+1}">Next</a></li>
            </c:when>
            <c:otherwise>
                <li class="page-item"><a class="page-link" href="?keyword=${param.keyword}&reverseOrder=${param.reverseOrder}&category_name=${param.category_name}&content_name=${param.content_name}&page=${products.number+1}">Next</a></li>
            </c:otherwise>
        </c:choose>
    </ul>

</div>
</section>
<%@ include file="../layout/footer.jsp"%>