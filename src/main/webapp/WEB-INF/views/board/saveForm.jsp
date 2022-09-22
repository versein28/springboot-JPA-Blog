<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="mb-3 text-center">
    <h5>공지사항 등록</h5>
</div>
<div class="container">
	<form>
		<div class="form-group">
			<label for="title">Title</label> <input type="text" class="form-control" placeholder="Enter title" id="title">
		</div>

		<div class="form-group">
			<label for="comment">Content</label>
			<textarea class="form-control summernote" rows="5" id="content"></textarea>
		</div>
	</form>
	<button id="btn-save" class="btn btn-light">글쓰기완료</button>
</div>
</section>

<script>
	$('.summernote').summernote({
		tabsize : 2,
		height : 100
	});
</script>
<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>