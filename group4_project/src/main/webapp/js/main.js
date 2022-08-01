$(function(){
	$('#test1').click(function(){
		document.getElementById("test1").style.display = "none";
		document.getElementById("test5").style.display = "none";
		document.getElementById("test4").style.display = "block";
		  $('html, body').animate({
  			scrollTop: $(".page-main-first").offset().top 
 			}, 'slow');
	});
	
	$('#test4').click(function(){
		document.getElementById("test1").style.display = "block";
		document.getElementById("test5").style.display = "block";
		document.getElementById("test4").style.display = "none";
	});
});