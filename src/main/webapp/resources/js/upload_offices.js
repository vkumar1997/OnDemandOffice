//Update office. Similar to office form.js
//Created by Vikas Kuamr and Piyush Malhotra


//if no username cookie present then redirect to login page

if(!checkCookie("username") || getCookie("login_type")!="user")
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
		
		url: '/ood/offices/'+username,
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
		message.innerHTML="No Offices Found";
		document.getElementById("page1").appendChild(message);
	}
	else
	{
		document.getElementById('float').style.display='block';
		for(i=0; i<result.length; i++)
		{
			var office_card=document.createElement("div");
			office_card.className ='nd2-card office-cards';
		
			office_card.id=result[i].office_id;
			office_card.addEventListener("click",function(){
				setCookie("office_id",this.id,1);
				window.location.href='Uploaded_Office_Details.html';
			});
			var media_card=document.createElement("div");
			media_card.className='card-media';
			
			var img=document.createElement("img");

			img.src=result[i].files[0];
			
			img.style.height='300px';
			
			var overlay=document.createElement("div");
			overlay.className ='card-media-overlay with-background';
			
			var card_title=document.createElement("div");
			card_title.className='card-media-overlay with-background';
			
			var br=document.createElement('br');
			var heading1=document.createElement("h3");
			heading1.innerHTML=result[i].city;
			heading1.style.color='#9CD9C9';
			heading1.style.paddingLeft='12px';
			heading1.style.textAlign='center';
			
			var heading2=document.createElement("h3");
			heading2.innerHTML=result[i].approval;
			if(result[i].approval=="Approved")
			{
				heading2.style.color='#9CD9C9';
			}
			if(result[i].approval=="Waiting")
			{
				heading2.style.color='#b5ce48';
			}
			if(result[i].approval=="Rejected")
			{
				heading2.style.color='#cc5a3b';
			}
			if(result[i].approval=="Engaged")
			{
				heading2.style.color='#b5ce48';
			}
			heading2.style.paddingLeft='12px';
			heading2.style.textAlign='center';

			
			var sub_heading=document.createElement("h5");
			sub_heading.style.color='#fff';
			var sub_heading1=result[i].office_address;
			var sub_heading2='Price per hour: Rs '+result[i].rent_price;
			var sub_heading3='Space for '+result[i].estimated_people+' people';
			sub_heading.innerHTML=sub_heading1;
			sub_heading.appendChild(br);
			sub_heading.innerHTML+=sub_heading2;
			sub_heading.appendChild(br);
			sub_heading.innerHTML+=sub_heading3;
			sub_heading.style.paddingLeft='12px';
			sub_heading.style.textAlign='center';
			
			card_title.appendChild(heading1);
			card_title.appendChild(heading2);
			card_title.appendChild(sub_heading);
			overlay.appendChild(card_title);
			media_card.appendChild(img);
			media_card.appendChild(card_title);
			office_card.appendChild(media_card);
			document.getElementById("page1").appendChild(office_card);
			
			
			
		}
	}
}