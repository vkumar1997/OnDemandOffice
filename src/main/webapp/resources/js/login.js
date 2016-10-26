//Created by Vikas Kumar

//Post method

//redirect to either user or login home page
if(checkCookie("username")!=false)
	{
		if(getCookie("login_type")=="user")
		{
			window.location.replace("Find_Office.html");
		}
		else if(getCookie("login_type")=="admin")
		{
			window.location.replace("Approved_offices.html");
		}
	}

//login
$(document).ready(function(){	
	
	
	//form submit
	$('#form1').submit(function(e){
		e.preventDefault();
		var username=document.getElementById("username").value;
		var password=document.getElementById("password").value;
		var address='/ood/login';
		$('#load').show();
		$('#log').hide();
		$('#link').hide();
		$.ajax({
			type: 'POST',
			url: address,
			data: {user_name: username, pass_word: password},
			success: function (result) {
				if(result!="Failure")
				{
					//storing of cookies
					var res=result.split("^");
					setCookie("name",res[0],400);
					setCookie("username",username,400);
					setCookie("phone",res[2],400);
					setCookie("city",res[3],400);
					setCookie("login_type",res[4],400);
					if(getCookie("login_type")=="user")
					{
						window.location.replace("Find_Office.html");
					}
					else if(getCookie("login_type")=="admin")
					{
						window.location.replace("Approved_offices.html");
					}
				}
				else
				{
					alert("Username or password is incorrect");
					$('#load').hide();
					$('#log').show();
					$('#link').show();
				}
			},
			error: function(ts){
				alert("Server not available");
				$('#load').hide();
				$('#log').show();
				$('#link').show();
				
			}
		});	
		
	});

	//Register form (Validate OTP)
	
	$('#form2').submit(function(e){
		e.preventDefault();
		var user_name=document.getElementById('name').value;
		var username=document.getElementById('enter_username').value;
		if(validateRegister()!=false)
		{
			var otp=document.getElementById('otp').value;
			$('#loader').show();
			$('#register').hide();
			$.ajax({
				type:"POST",
				url:"/ood/otp",
				data:{mail: username, name: user_name, one_time:otp},
				//for otp generation using mail
				success:function(result)
				{
					if(result=="Success")
					{	$('#container1').hide();
						$('#container2').show();
					}
					else
					{
						alert("Cannot send mail");
						$('#loader').hide();
						$('#register').show();
					}
				},
				error: function(){
					alert("Server not available");
					$('#loader').hide();
					$('#register').show();
				}
			});
		}
	});
	
	//OTP Form (Post Register Page to backend)
	$('#form3').submit(function(e){
		e.preventDefault();
		
		var username=document.getElementById('enter_username').value;
		var password=document.getElementById('enter_password').value;
		var user_name=document.getElementById('name').value;
		var user_phone=document.getElementById('enter_phone').value;
		var user_city=document.getElementById('enter_city').value;
		
		if(validateOTP()!=false)
		{
			//validate otp and send to backend
			$.ajax({
				type:"POST",
				url:"/ood/addUser",
				data:{mail: username, enter_password: password,name: user_name,phone: user_phone,city: user_city},
				success:function(result)
				{
					if(result=="Created")
					{
						setCookie("name",user_name,400);
						setCookie("username",username,400);
						setCookie("password",password,400);
						setCookie("phone",user_phone,400);
						setCookie("city",user_city,400);
						setCookie("login_type","user",400);
						window.location.replace("Find_Office.html");
					}
					else
					{
						alert("Username already created");
						window.location.replace("login.html");
					}
					
				},
				
				error: function(){
					alert("Server not available");
				}
			});
			
		}
		
	});
	
	
	//Forgot password
	$('#link').click(function(e){
		e.preventDefault();
		var username=document.getElementById("username").value;
		for(var i=0; i < 4; i++) {
			username = username.replace(".", "`");
		}
		if(validateLogin()!=false)
		{
			$('#load').show();
			$('#log').hide();
			$('#link').hide();
			$.ajax({
				
				//check if username exists
				type: 'GET',
				url: '/ood/getUser/'+username,
				
				success: function (result)
				{
					var one=getCookie("otp");
					for(var i=0; i < 4; i++) {
						username = username.replace("`", ".");
					}
					if(result=="Failure")
					{
						alert("Username does not exist");
						$('#load').hide();
						$('#log').show();
						$('#link').show();
					}
					else
					{
						//generate otp 
						$.ajax({
							type:"POST",
							url:"/ood/otp",
							data:{mail:username,name:result,one_time:one},
							success:function(result)
							{
								if(result=="Success")
								{	
									setCookie("for_user",username,0.2);
									window.location.replace('forgot_pass.html');
								}
								else
								{
									alert("Something went wrong");
									$('#loader').hide();
									$('#register').show();
								}
							},
							error: function(){
								alert("Server not available");
								$('#loader').hide();
								$('#register').show();
							}
						});	
					}
				},
				error:function()
				{
					alert("Server not available");
					$('#load').hide();
					$('#log').show();
					$('#link').show();
				}
			});
		}
	});
	
	
});



//Sign In Page

function signin()
{
	document.getElementById('form2').style.display='none';
	document.getElementById('form1').style.display='block';
	document.getElementById('button2').style.backgroundColor='rgba(52,73,94,0.7)';
	document.getElementById('button1').style.backgroundColor='#222222';
}


//Register Page



function register()
{
	document.getElementById('form1').style.display='none';
	document.getElementById('form2').style.display='block';
	document.getElementById('button1').style.backgroundColor='rgba(52,73,94,0.7)';
	document.getElementById('button2').style.backgroundColor='#222222';
}


//ValidateRegisterPage

function validateRegister()
{
	
	var x = document.forms["form2"]["name"].value;
	
	if (x == null || x == "" || x.indexOf(" ")<=-1) 
	{
        alert("Full Name must be filled out");
		return false;
    }
	x = document.forms["form2"]["phone"].value;
	
	if(x.length!=10)  
    {  
        alert("Not a valid Phone Number");  
        return false;  
    } 
	
	
	//Code if only NCR Cities needed
	/*var x = document.getElementById("enter_city").value;
	var datalist=document.getElementById('city_list');
	var bool=false;
	for (i = 0; i < datalist.options.length; i++) {
		if(x != null && x != "" && datalist.options[i].value.toUpperCase()==x.toUpperCase())
		{
			bool=true;
			document.getElementById.value=datalist.options[i].value;
			break;
        }
	}
	if(bool==false)
	{
		alert("City not valid");
		return false;
	}*/
	
	var x = document.getElementById("enter_city").value;
	if(x.length<=4)  
    {  
        alert("Enter a valid city");  
        return false;  
    } 
	
	if(document.getElementById("enter_password").value.length<=4)
	{
		alert("Password should be atleast 5 digit long");
		return false
	}
	
    if (document.getElementById("enter_password").value!=document.getElementById("confirm_password").value) {
        alert("Passwords do not match");
        return false;
    }
	
	var otp=Math.floor(Math.random()*89999)+10000;
	document.getElementById('otp').value=otp;
	
	
	
}


//Validate OTP
function validateOTP()
{
	var x=document.getElementById('otp_verify').value;
	var y=document.getElementById('otp').value;
	if(x!=y)
	{
		alert("Incorrect OTP");
		return false;
	}
	
}


//Validate Login
function validateLogin()
{
	var x = document.getElementById('username').value;
    var atpos = x.indexOf("@");
    var dotpos = x.lastIndexOf(".");
    if (atpos<4 || dotpos<atpos+3 || dotpos+2>=x.length) {
        alert("Not a valid e-mail address");
        return false;
    }
	
	
	
	var one=Math.floor(Math.random()*89999)+10000;
	setCookie("otp",one,0.2);
	
}







