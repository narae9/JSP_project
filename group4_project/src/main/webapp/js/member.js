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
					$('#message_id').css('color','white').text('등록 가능 ID');
				}else if(param.result == 'idDuplicated'){
					idChecked = 0;
					$('#message_id').css('color','white').text('중복된 ID');
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
	$('#register_form').submit(function(){
		if($('#id').val().trim()==''){
			alert('아이디를 입력하세요!');
			$('#id').val('').focus();
			return false;
		}
		if(idChecked==0){
			alert('아이디 중복 체크 필수');
			return false;
		}
		if($('#name').val().trim()==''){
			alert('이름을 입력하세요!');
			$('#name').val('').focus();
			return false;
		}
		if($('#passwd').val().trim()==''){
			alert('비밀번호를 입력하세요!');
			$('#passwd').val('').focus();
			return false;
		}
		if($('#agecode').val().trim()==''){
			alert('생년월일 입력하세요!');
			$('#agecode').val('').focus();
			return false;
		}
		if($('#phone').val().trim()==''){
			alert('전화번호를 입력하세요!');
			$('#phone').val('').focus();
			return false;
		}
		if($('#email').val().trim()==''){
			alert('이메일을 입력하세요!');
			$('#email').val('').focus();
			return false;
		}
		if($('#zipcode').val().trim()==''){
			alert('우편번호를 입력하세요!');
			$('#zipcode').val('').focus();
			return false;
		}
		if($('#add1').val().trim()==''){
			alert('주소를 입력하세요!');
			$('#address1').val('').focus();
			return false;
		}
		if($('#add2').val().trim()==''){
			alert('나머지 주소를 입력하세요!');
			$('#address2').val('').focus();
			return false;
		}
		
	});
		
	
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
	
	
	//============= 회원정보수정 ==============//
	$('#modify_form').submit(function(){
		if($('#name').val().trim()==''){
			alert('이름을 입력하세요!');
			$('#name').val('').focus();
			return false;
		}
		if($('#phone').val().trim()==''){
			alert('전화번호를 입력하세요!');
			$('#phone').val('').focus();
			return false;
		}
		if($('#email').val().trim()==''){
			alert('이메일을 입력하세요!');
			$('#email').val('').focus();
			return false;
		}
		if($('#zipcode').val().trim()==''){
			alert('우편번호를 입력하세요!');
			$('#zipcode').val('').focus();
			return false;
		}
		if($('#add1').val().trim()==''){
			alert('주소를 입력하세요!');
			$('#add1').val('').focus();
			return false;
		}
		if($('#add2').val().trim()==''){
			alert('나머지 주소를 입력하세요!');
			$('#add2').val('').focus();
			return false;
		}
		
		if($('#agecode').val().trim()==''){
			alert('나머지 주소를 입력하세요!');
			$('#agecode').val('').focus();
			return false;
		}
	});
	
	//============= 회원탈퇴 ==============//
	$('#delete_form').submit(function(){
		if($('#id').val().trim()==''){
			alert('아이디를 입력하세요!');
			$('#id').val('').focus();
			return false;
		}
		if($('#email').val().trim()==''){
			alert('이메일을 입력하세요!');
			$('#email').val('').focus();
			return false;
		}
		if($('#passwd').val().trim()==''){
			alert('비밀번호를 입력하세요!');
			$('#passwd').val('').focus();
			return false;
		}
		if($('#cpasswd').val().trim()==''){
			alert('비밀번호 확인을 입력하세요!');
			$('#cpasswd').val('').focus();
			return false;
		}
		if($('#passwd').val()!=$('#cpasswd').val()){
			alert('비밀번호와 비밀번호 확인 불일치');
			$('#passwd').val('').focus();
			$('#cpasswd').val('');
			return false;
		}
	});//end of submit
	
	//비밀번호 확인까지 한 후 다시 비밀번호를 수정하면 비밀번호 확인 및
	//메시지 초기화
	$('#passwd').keyup(function(){
		$('#cpasswd').val('');
		$('#message_id').text('');
	});
	
	//비밀번호와 비밀번호 확인 일치 여부 체크
	$('#cpasswd').keyup(function(){
		if($('#passwd').val()==$('#cpasswd').val()){
			$('#message_id').text('비밀번호 일치');
		}else{
			$('#message_id').text('');
		}
	});
})