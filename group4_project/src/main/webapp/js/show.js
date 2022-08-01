$(function(){


	//===================공연 등록 & 공연 수정=====================//
	$('#showInsertForm').submit(function(){
		if($('#sh_title').val().trim()==''){
			alert('제목을 입력하세요');
			$('#sh_title').val('').focus();
			return false;	
		}
		if($('#sh_date').val().trim()==""){
			alert('날짜를 입력하세요');
			$('#sh_date').val('').focus();
			return false;	
		}
		if($('#sh_time').val().trim()==""){
			alert('시간을 입력하세요');
			$('#sh_time').val('').focus();
			return false;	
		}
		if($('#sh_place').val().trim()==""){
			alert('장소를 입력하세요');
			$('#sh_place').val('').focus();
			return false;	
		}
		if($('#sh_detail').val().trim()==""){
			alert('공연 설명을 입력하세요');
			$('#sh_detail').val('').focus();
			return false;	
		}
	});
	
	$('#reserve_btn').submit(function(){
		if($('#re_spon').val().trim()==""){
			$('#re_spon').val('0');
		}
		
		$.ajax({
			url:'reserveShow.do',
			type:'post',
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(param){
				if(param.result=='logout'){
					alert('로그인 후 예매하세요.');
				}
			}

		});
		
	});
	

	


});