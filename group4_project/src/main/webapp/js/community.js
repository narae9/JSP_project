$(function(){
	//=============게시판 글쓰기============//
	$('#write_form2').submit(function(){
		if($('#co_title').val().trim()==''){
			alert('제목을 입력하세요!');
			$('#co_title').val('').focus();
			return false;
		}
		if($('#co_write').val().trim()==''){
			alert('내용을 입력하세요!');
			$('#co_write').val('').focus();
			return false;
		}
	});
	
	//=============목록 검색창============//
	$('#search_form').submit(function(){
		if($('#keyword_board2').val().trim()==''){
			alert('검색어를 입력하세요!');
			$('#keyword_board2').val('').focus();
			return false;
		}
	});
});