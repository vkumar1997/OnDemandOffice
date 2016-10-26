//created by siddharth singh


//transfer of otp from one page to another
if(checkCookie("for_user")==false)
{
	window.location.replace("login.html");
}


//changing of password
$(document).ready(function(){	
	
	$('#form10').submit(function(e){
		e.preventDefault();
		var username=getCookie('for_user')
		var otp=document.getElementById("otp_ver").value;
		var password=document.getElementById("new_password").value;
		for(var i=0; i < 4; i++) {
			username = username.replace("`", ".");
		}
		if(otp==getCookie("otp"))
		{
			//ajax for changing password
			$.ajax({
				type: 'POST',
				url: '/ood/change',
				data: {mail: username, n_password: password},
				success: function (result) {
					if(result=="Success")
					{
						alert("Password changed successfully");
						window.location.replace("login.html");
					}
					if(result=="Failure")
					{
						alert("We could not change your password");
					}
				},
				error:function(ts){
					alert("Server not available");
				}
			});
		}
		else{
			alert("Invalid otp");
		}
		
	});
});