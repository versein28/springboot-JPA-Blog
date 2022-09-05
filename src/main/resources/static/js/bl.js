let bl = {
    init: function() {
        $("#bl-check").on("click", () => {
            this.check();
        });
    },

    check: function() {
        var b_no = $("#b_no").val();

        var data = {
            "b_no": [b_no]
        };
        console.log("data" + JSON.stringify(data));
        $.ajax({
            type: "POST",
            url: "https://api.odcloud.kr/api/nts-businessman/v1/status?serviceKey=5hBVGxDy%2BBEx6gbo3OtIKOdSDfS1%2BqXy7gPpAHZuqTwsg1US3qCqT3VDg06YKUpUoVQ0Lio5jqvgjJ%2Bo8Vzc%2BA%3D%3D",
            data: JSON.stringify(data),
            contentType: "application/json",
            accept: "application/json",
            dataType: "JSON",
            success: function(result) {
                alert(result.data[0].tax_type); // type 호출
                if (result.data[0].tax_type != "국세청에 등록되지 않은 사업자등록번호입니다.") {
                    $("#btn-save").removeAttr('disabled'); //회원가입 버튼 활성화
                    $("#bt").val(result.data[0].tax_type); //사업자등록 타입 변수에 가져오기
                }
            },
            error: function(result) {
                console.log(result.responseText); // responseText의 에러메세지 확인
            }
        });
    }
}
bl.init();