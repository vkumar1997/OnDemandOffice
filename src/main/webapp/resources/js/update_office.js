//Update office. Similar to office form.js
//Created by Vikas Kuamr and Piyush Malhotra


//if no username cookie present then redirect to login page

if(!checkCookie("username")|| getCookie("login_type")!="user")
{

	window.location.replace("login.html");
}




//Office Post Method
$(document).ready(function(){
	
	
	
	document.getElementById('name').value=getCookie('owner_name');
	document.getElementById('mail').value=getCookie('owner_mail');
	document.getElementById('p_number').value=getCookie('owner_phone');
	document.getElementById('home_address').value=getCookie('owner_address');
	document.getElementById('office_address').value=getCookie('office_address');
	document.getElementById('office_price').value=getCookie('office_price');
	document.getElementById('city').value=getCookie('owner_city');
	
	document.getElementById('max_people').value=getCookie('estimated_people');
	document.getElementById('start_time').value=getCookie('opening_time');
	document.getElementById('end_time').value=getCookie('closing_time');
	document.getElementById('office_id').value=getCookie('office_id');
	$('#form').submit(function(e){
		e.preventDefault();
			
		if(validateForm()!=false)
		{
			$('#loader').show();
			var formData = new FormData($(this)[0]);
			$('#update').hide();
			$.ajax({
				type:"POST",
				url:"/ood/updateOffice",
				data:formData,
				contentType: false,
				processData: false,
				success:function(result)
				{
					if(result=="Failure")
					{
						alert("Something went wrong");
						
						$('#loader').hide();
						$('#update').show();
					}
					else
					{
						window.location.replace("office_uploads.html");
					}
						
				},
				error: function(){
					alert("Server not available");
					$('#loader').hide();
					$('#update').show();
				}
			});
		}
	});
});






//Office image
function add_image()
{
	while (document.getElementById('pic').firstChild) {
		document.getElementById('pic').removeChild(document.getElementById('pic').firstChild);
	}
	document.getElementById('file').click();	
}

window.onload = function () {
    document.getElementById('file').onchange = function () {
		for(i=0; i<this.files.length; i++)
		{
		var reader = new FileReader();
		
		reader.onload=function(e)
		{
			var img=document.createElement("img");
			img.className +='snapshots';
			document.getElementById('pic').appendChild(img);
			img.src=e.target.result;
		};
		reader.readAsDataURL(this.files[i])
		}
    };
};



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

	x = document.forms["form"]["home_address"].value;
    if (x == null || x == "") {
        alert("Home Address must be filled out");
        return false;
    }
	
	x = document.forms["form"]["office_address"].value;
    if (x == null || x == "") {
        alert("Office Address must be filled out");
        return false;
    }
	
	
    //IF only NCR cities required
    /*
	  x = document.forms["form"]["city"].value;
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
    
    var x = document.getElementById("city").value;
	if(x.length<=4)  
    {  
        alert("Enter a valid city");  
        return false;  
    }
	
	
	
	x = document.getElementById('file').files.length;
	
    if (x<3) {
        alert("Atleast three images must be uploaded.");
        return false;
    }
	
	x = document.forms["form"]["description"].value;
    if (x == null || x == "") {
        alert("You need to describe your office..");
        return false;
    }
}

