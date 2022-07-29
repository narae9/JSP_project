$(function(){
	let idChecked = 0;
	
	//========== 회원가입 ==========//
	// 아이디 중복 체크 이벤트 연결
	
	// 아이디 중복 안내 메시지 초기화 및 아이디 중복 값 초기화
	
	// 회원정보 등록 유효성 체크
	
	
	//========== 로그인 ==========//
	//로그인 이벤트 연결
	$('#login_form').submit(function(){
		if($('#id').val().trim()==''){
			arlert('아이디를 입력하세요!');
			$('#id').val('').focus();
			return false;
		}
		
		if($('#passwd').val().trim()==''){
			arlert('비밀번호를 입력하세요!');
			$('#passwd').val('').focus();
			return false;
		}
	})
})