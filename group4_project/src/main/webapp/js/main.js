$(function(){
	$('#test1').click(function(){
		document.getElementById("test1").style.display = "none";
		document.getElementById("test5").style.display = "none";
		document.getElementById("test4").style.display = "block";
		  $('html, body').animate({
  			scrollTop: $(".page-main").offset().top 
 			}, 'slow');
	});
});