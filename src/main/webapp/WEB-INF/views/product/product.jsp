<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container" style="width: 900px;">
	<div class="row">
	<c:forEach var="product" items="${products.content}">
		<div class="col-6">
			<div class="card">
				<div class="card-header">${product.prodName}</div>
				<img src="${product.filePath}" onerror="this.onerror=null; this.src='https://via.placeholder.com/400.jpg';" style="max-width: 100%; height: auto;" alt="" />
				<div class="card-body">
					<h5 class="card-title">${product.prodBrand}</h5>
					<p class="card-text">${product.content}</p>
					<a href="/product/${product.id}" class="btn btn-primary">주문하기</a>
				</div>
			</div>
		</div>
		</c:forEach>
	</div>
	
		<ul class="pagination justify-content-center" style="margin-top:20px;">
		<c:choose>
			<c:when test="${products.first}">
				<li class="page-item disabled"><a class="page-link" href="?page=${products.number-1}">Previous</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item"><a class="page-link" href="?page=${products.number-1}">Previous</a></li>
			</c:otherwise>
		</c:choose>

		<c:choose>
			<c:when test="${products.last}">
				<li class="page-item disabled"><a class="page-link" href="?page=${products.number+1}">Next</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item"><a class="page-link" href="?page=${products.number+1}">Next</a></li>
			</c:otherwise>
		</c:choose>

	</ul>
	
</div>


<%@ include file="../layout/footer.jsp"%>


