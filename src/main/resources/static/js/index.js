//csrf토큰
var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");
//페이지 로드시 실행
$(window).on('load', function() {
    var data = {
        content: "서랍"
    };
    $.ajax({
        type: "POST",
        url: "/auth/product/rank",
        data: JSON.stringify(data),
        //보내는 데이터의 타입 
    	beforeSend : function(xhr)
        {   /*데이터를 전송하기 전에 헤더에 csrf값을 설정한다*/
			xhr.setRequestHeader(header, token);
        },
        contentType: "application/json; charset=utf-8",
        //받을 데이터 타입 
        dataType: "json"
    }).done(function(result) {
        for (var i = 0; i < result.content.length; i++) {
            $(".image-gallery").append("<li><a>" + (i + 1) + "위</a> <img src=" + result.content[i].filePath + ">" +
                "<div class='overlay' OnClick=\"location.href ='/product/" + result.content[i].id + "'\"> <span>" + result.content[i].content + "</span> </div></li>");
        }
        $('#desk').css('font-weight', 'bold');
    }).fail(function(error) {
        console.log(JSON.stringify(error));
    });
});
//공지사항
$(document).on('click', '.pagination li a', function() { // click() 사용시 새로 추가된 DOM요소 동작하지 않음
    var boardHtml = ""; //글 목록
    var pageHtml = ""; // 페이지네이션
    $.ajax({
        type: "GET",
        url: "/auth/board/pagination?page=" + $(this).attr('id'),
        dataType: "json"
    }).done(function(rsp) {
        for (var i = 0; i < rsp.numberOfElements; i++) {
            boardHtml += "<div class='card mb-3'>" + "<div class='card-body'>" + "<p class='card-title'>" + rsp.content[i].title + "</p>" + "<a class='btn btn-light' href=/board/" + rsp.content[i].id + ">" + "상세보기</a>" + "</div>" + "</div>"
        }
        $(".dataTableBody div").remove(); //기존 글 목록 삭제
        $(".dataTableBody").append(boardHtml); // 동적 글 목록 할당
        //글 목록 끝
        $(".pagination li").remove(); // 기존 페이지네이션  삭제
        //Number로 변환하지 않으면 아래에서 +를 할 경우 스트링 결합이 되어버림.. 
        var PageNum = Number(rsp.number);
        if (rsp.first) {
            pageHtml += "<li class='page-item disabled'><a id='" + (PageNum - 1) + "' class='page-link' href='#'>Previous</a></li>";
        } else {
            pageHtml += "<li class='page-item'><a id='" + (PageNum - 1) + "' class='page-link' href='#'>Previous</a></li>";
        }
        if (rsp.last) {
            pageHtml += "<li class='page-item disabled'><a id='" + (rsp.number + 1) + "' class='page-link' href='#'>Next</a></li>";
        } else {
            pageHtml += "<li class='page-item'><a id='" + (rsp.number + 1) + "' class='page-link' href='#'>Next</a></li>";
        }
        $(".pagination").append(pageHtml);
        //페이지 번호 끝
    }).fail(function(error) {
        console.log(JSON.stringify(error));
    });
});
//실시간 랭킹
$("#rank li").click(function() {
    var data = {
        content: $(this).attr("value") // ex)의자, 서랍, 탁자 
    };
    var target = $(this).attr("id"); // font-weight 설정
    $.ajax({
        type: "POST",
        url: "/auth/product/rank",
        data: JSON.stringify(data),
    	beforeSend : function(xhr)
        {   /*데이터를 전송하기 전에 헤더에 csrf값을 설정한다*/
			xhr.setRequestHeader(header, token);
        },
        //보내는 데이터의 타입 
        contentType: "application/json; charset=utf-8",
        //받을 데이터 타입 
        dataType: "json"
    }).done(function(result) {
        $(".image-gallery li").remove(); // 이미지 초기화
        for (var i = 0; i < result.content.length; i++) {
            $(".image-gallery").append("<li><a>" + (i + 1) + "위</a> <img src=" + result.content[i].filePath + ">" +
                "<div class='overlay' OnClick=\"location.href ='/product/" + result.content[i].id + "'\"> <span>" + result.content[i].content + "</span> </div></li>");
        }
        $("#rank li").css('font-weight', ''); // font-weight 초기화
        $('#' + target).css('font-weight', 'bold');
    }).fail(function(error) {
        console.log(JSON.stringify(error));
    });
});