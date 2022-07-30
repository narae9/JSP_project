$(function(){
	let idChecked = 0;
	
	//========== 회원가입 ==========//
	// 아이디 중복 체크 이벤트 연결
	$('#id_check').click(function(){
		if($('#id').val().trim()==''){
			alert('아이디를 입력하세요!');
			$('#id').val('').focus();
			return;
		}
		
		//아이디 중복 여부를 표시하는 메시지 초기화
		$('#message_id').text('');
		console.log('버튼 이벤트 시작');
		
		//서버와 데이터 통신
		$.ajax({
			url:'checkDuplicatedId.do',
			type:'post',
			data:{id:$('#id').val()},
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(param){
				if(param.result == 'idNotFound'){
					idChecked = 1;
					$('#message_id').css('color','#000000').text('등록 가능 ID');
				}else if(param.result == 'idDuplicated'){
					idChecked = 0;
					$('#message_id').css('color','red').text('중복된 ID');
					$('#id').val('').focus();
				}else{
					idChecked = 0;
					alert('아이디 중복 체크 오류 발생');
				}
			},
			error:function(){
				idChecked = 0;
				alert('네트워크 오류 발생');
			}
		});
	});//end of click
	
	// 아이디 중복 안내 메시지 초기화 및 아이디 중복 값 초기화
	$('#register_form #id').keydown(function(){
		idChecked = 0;
		$('#message_id').text('');
	});
	
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