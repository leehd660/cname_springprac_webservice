var index = {
    init : function () {
        var _this = this;
        $('#btn-save').on('click', function (){
            _this.save();
        });
        $('#btn-update').on('click', function (){
//            btn-update란 id를 가진 Html엘리먼트레 click이벤트가 발생할 때 update function을 실행하도록 이벤트를 등록함.
            _this.update();
        });
        $('#btn-delete').on('click', function (){
            _this.delete();
        });
    },
    save : function () {
        var data = {
            title: $('#title').val(),
            author: $('#author').val(),
            content: $('#content').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/posts',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 등록되었습니다.');
            window.location.href = '/';
            //글 등록이 성공하면 메인페이지주소인 '/'로 이동함.
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    },
    update : function () { //update function
        var data = {
            title: $('#title').val(),
            content: $('#content').val()
        };

        var id = $('#id').val();

        $.ajax({
//        웹페이지를 새로 로딩하지 않아도 실시간적으로 서버와 통신하면서 부분적 값을 주고 받는 것.
            type: 'PUT', //여러 HTML중 PUT메소드를 선택
            //PostApiController에 있는 API에서 이미 @PutMapping으로 선언했기 때문에 PUT을 사용해야함
            //Rest에서 CRUD는 다음 같이 HTTP에 매핑됨.
            //생성:POST, 읽기:GET, 수정:PUT, 삭제:DELETE
            url: '/api/v1/posts/'+id,
            //어느 게시글을 수정할지 URL Path로 구분하기 위해 Path에 id를 추가함.
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 수정되었습니다.');
            window.location.href = '/';
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    },
    delete : function () {
        var id = $('#id').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/posts/'+id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8'
        }).done(function () {
            alert('글이 삭제되었습니다.');
            window.location.href = '/';
        }).fail(function (){
            alert(JSON.stringify(error));
        });
    }

};

index.init();