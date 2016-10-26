//Created By Vikas Kuamr


$(document).ready(function(){
	var name=getCookie("name");
	var username=getCookie("username");
	document.getElementById("nav_name").innerHTML=name+"<br><br>"+username;
});

function log_out()
{
	eraseCookie("username");
	eraseCookie("name");
	eraseCookie("phone");
	eraseCookie("city");
	eraseCookie("office_id");
	eraseCookie("shortlist");
	window.location.replace("login.html");
}