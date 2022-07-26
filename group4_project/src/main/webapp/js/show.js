
//이미지 전송
$('#InsertSubmit_btn').click(function(){
	
	//파일 전송
	let form_data = new FormData(); //여기에 파일저장을해줌
				  //(파라미터네임, 벨류)
	$.ajax({
		url:'updatePhoto.do',
		type:'post',
		data:form_data,
		dataType:'json',
		contentType:false,//데이터 객체를 문자열로 바꿀지에 대한 값 true면 일반문자
		processData:false,//해당 타입을 true라고 명시하면 일반 text로 인식 -> 현재는 파일이라는것
		enctype:'multipart/form-data',
		success:function(param){
			
		},
		error:function(){
			alert('네트워크 오류 발생');
		}
		
	});
	
});//end of click

