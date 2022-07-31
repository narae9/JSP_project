$(function(){
	//============목록 검색창============//
	$('#search_form').submit(function(){
		if($('#keyword_board2').val().trim()==''){
			alert('검색어를 입력하세요!');
			$('#keyword_board2').val('').focus();
			return false;
		}
	});
});