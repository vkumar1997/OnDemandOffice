//Created By Piyush Malhotra

//if no username cookie present then redirect to login page
if(!checkCookie("username")|| getCookie("login_type")!="user")
{
	window.location.replace("login.html");
}

$(document).ready(function(){
	
	var username=getCookie("username");
	for(var i=0; i < 4; i++) {
		username = username.replace(".", "`");
	}
	$.ajax({
		type: 'GET',
		
		url: '/ood/requests/'+username,
		dataType: 'json',
		success: function (result) {
			populate(result);
			
		},
		error: function(ts){
			alert("Server not available");
		}
	});
});


function populate(result)
{
	if(result.length==0)
	{
		var message=document.createElement("p");
		message.className +='message';
		message.innerHTML="No Requests Made";
		document.body.appendChild(message);
	}
	else
	{
		
		for(i=0; i<result.length; i++)
		{
			
			var container=document.createElement("div");
			container.className +='container';
			
			var timestamp=document.createElement("div");
			timestamp.id='timestamp1';
			var value_time=document.createElement("label");
			value_time.className='value';
			var res=result[i].timestamp.split(" ");
			value_time.innerHTML=res[0];
			timestamp.appendChild(value_time);
			container.appendChild(timestamp);
			
			var timestamp=document.createElement("div");
			timestamp.id='timestamp';
			var value_time=document.createElement("label");
			value_time.className='value';
			var res=result[i].timestamp.split(" ");
			value_time.innerHTML=res[1];
			timestamp.appendChild(value_time);
			container.appendChild(timestamp);
			
			var office_address=document.createElement("div");
			office_address.className +='entry';
			var key_address=document.createElement("label");
			key_address.className+='key';
			key_address.innerHTML="Office Address:<br>";
			var value_address=document.createElement("label");
			value_address.className+='value';
			value_address.innerHTML=result[i].office_address;
			office_address.appendChild(key_address);
			office_address.appendChild(value_address);
			container.appendChild(office_address);
			
			var sender_name=document.createElement("div");
			sender_name.className +='entry';
			var key_name=document.createElement("label");
			key_name.className+='key';
			key_name.innerHTML="Sender Name:<br>";
			var value_name=document.createElement("label");
			value_name.className+='value';
			value_name.innerHTML=result[i].sender_name;
			sender_name.appendChild(key_name);
			sender_name.appendChild(value_name);
			container.appendChild(sender_name);
			
			var sender_mail=document.createElement("div");
			sender_mail.className +='entry';
			var key_mail=document.createElement("label");
			key_mail.className+='key';
			key_mail.innerHTML="EMail Address:<br>";
			var value_mail=document.createElement("label");
			value_mail.className+='value';
			value_mail.innerHTML=result[i].sender_mail;
			sender_mail.appendChild(key_mail);
			sender_mail.appendChild(value_mail);
			container.appendChild(sender_mail);
			
			var sender_phone=document.createElement("div");
			sender_phone.className +='entry';
			var key_phone=document.createElement("label");
			key_phone.className+='key';
			key_phone.innerHTML="Phone Number:<br>";
			var value_phone=document.createElement("label");
			value_phone.className+='value';
			value_phone.innerHTML=result[i].phone;
			sender_phone.appendChild(key_phone);
			sender_phone.appendChild(value_phone);
			container.appendChild(sender_phone);
			
			var entering_time=document.createElement("div");
			entering_time.className +='entry';
			var key_enter=document.createElement("label");
			key_enter.className+='key';
			key_enter.innerHTML="Entering Time:<br>";
			var value_enter=document.createElement("label");
			value_enter.className+='value';
			value_enter.innerHTML=result[i].entering_time;
			entering_time.appendChild(key_enter);
			entering_time.appendChild(value_enter);
			container.appendChild(entering_time);
			
			var leaving_time=document.createElement("div");
			leaving_time.className +='entry';
			var key_close=document.createElement("label");
			key_close.className+='key';
			key_close.innerHTML="Leaving Time:<br>";
			var value_close=document.createElement("label");
			value_close.className+='value';
			value_close.innerHTML=result[i].leaving_time;
			leaving_time.appendChild(key_close);
			leaving_time.appendChild(value_close);
			container.appendChild(leaving_time);
			
			var start_date=document.createElement("div");
			start_date.className +='entry';
			var key_start=document.createElement("label");
			key_start.className+='key';
			key_start.innerHTML="Booking Date:<br>";
			var value_start=document.createElement("label");
			value_start.className+='value';
			value_start.innerHTML=result[i].start_date;
			start_date.appendChild(key_start);
			start_date.appendChild(value_start);
			container.appendChild(start_date);
			
			var price=document.createElement("div");
			price.className +='entry';
			var key_price=document.createElement("label");
			key_price.className+='key';
			key_price.innerHTML="Price per hour:<br>";
			var value_price=document.createElement("label");
			value_price.className+='value';
			value_price.innerHTML="Rs "+result[i].price;
			price.appendChild(key_price);
			price.appendChild(value_price);
			container.appendChild(price);
			
			var price=result[i].price;
			var ref=result[i].ref_id;
			if(result[i].status=="Active")
			{
				var button=document.createElement("button");
				button.className +='send';
				button.innerHTML="Start Time Counter";
				button.addEventListener("click",function(){
					setCookie('ref',ref,4);
					setCookie("price",price,4);
					window.location.href='time_count.html';
				});
				container.appendChild(button);
			}
			else
			{
				var earn=document.createElement("div");
				earn.className +='entry';
				var key_earn=document.createElement("label");
				key_earn.className+='key';
				key_earn.innerHTML="Total Earnings:<br>";
				var value_earn=document.createElement("label");
				value_earn.className+='value';
				value_earn.innerHTML="Rs "+result[i].status;
				earn.appendChild(key_earn);
				earn.appendChild(value_earn);
				container.appendChild(earn);
				
			}
			
			document.body.appendChild(container);
			
			
			
			
		}
	}
}