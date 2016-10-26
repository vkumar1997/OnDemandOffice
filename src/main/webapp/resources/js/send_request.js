//Created By Vikas Kumar



//if no username cookie present then redirect to login page
if(!checkCookie("username") || getCookie("login_type")!="user")
{
	window.location.replace("login.html");
}


//Office Post Method
$(document).ready(function(){
	
	document.getElementById("name").value=getCookie("name");
	document.getElementById("mail").value=getCookie("username");
	document.getElementById("p_number").value=getCookie("phone");
	document.getElementById('start').valueAsDate=new Date();
	
	//Office Form
	
	$('#form').submit(function(e){
	e.preventDefault();
	if(validateForm()!=false)
	{
		
		
		var o_name=getCookie('owner_name');
		
		var o_mail=getCookie('owner_mail');
		
		var o_price=getCookie('office_price');
		var o_address=getCookie('office_address');
		
		var o_phone=getCookie('owner_phone');
	
		var s_name=document.getElementById("name").value;
		
		var s_mail=document.getElementById("mail").value;
		
		var s_phone=document.getElementById("p_number").value;
	
		var o_id=getCookie('office_id');
		
		var start=document.getElementById('start').value;
		
		var s_time=document.getElementById('start_time').value;
	
		var e_time=document.getElementById('end_time').value;
	
		var spa_requested=document.getElementById('max_people').value;
			$('#loader').show();
			$('#request').hide();
			$.ajax({
				type:"POST",
				url:"/ood/request",
				data:{office_id:o_id,owner_name:o_name,sender_name:s_name,owner_mail:o_mail,sender_mail:s_mail,owner_phone:o_phone,sender_phone:s_phone,space_requested:spa_requested,office_address:o_address,office_price:o_price,start_time:s_time,end_time:e_time,start_date:start},
				success:function(result)
				{
					
					if(result=="Success")
					{
						window.location.replace("Find_Office.html");
					}
					else
					{
						alert("failure");
						$('#loader').hide();
						$('#request').show();
					}
				},
				error: function(ts){
					alert("Server not available");
					$('#loader').show();
					$('#request').hide();
				}
			});
		}
	});
});






//Validate Office Form
function validateForm()
{
	var x=document.forms["form"]["start_time"].value;
	var y=document.forms["form"]["end_time"].value;

	if(x.split(":").length==2)
	{
		document.getElementById('start_time').value=x+":00";
	}
	if(y.split(":").length==2)
	{
		document.getElementById('end_time').value=y+":00";
	}
	if(document.getElementById('start_time').value==document.getElementById('end_time').value || document.getElementById('start_time').value.split(":").length!=3 || document.getElementById('end_time').value.split(":").length!=3)
	{
		
		alert("Invalid Timings");
		return false;
	}
	
	
	
	
	var x = document.forms["form"]["name"].value;
    
	if (x == null || x == "") {
	
        alert("Name must be filled out");
		return false;
    }
	
	x = document.forms["form"]["mail"].value;
    var atpos = x.indexOf("@");
    var dotpos = x.lastIndexOf(".");
    if (atpos<4 || dotpos<atpos+3 || dotpos+2>=x.length) {
        alert("Not a valid e-mail address");
        return false;
    }
	
	x = document.forms["form"]["p_number"].value; 
	if(x.length!=10)  
    {  
        alert("Not a valid Phone Number");  
        return false;  
    } 

	x = document.forms["form"]["p_number"].value; 
	if(x.length!=10)  
    {  
        alert("Not a valid Phone Number");  
        return false;  
    }
	
}

