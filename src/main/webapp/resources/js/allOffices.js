//Created by Vikas Kumar


//if no username cookie present then redirect to login page
if(!checkCookie("username") || getCookie("login_type")!="user")
{
	window.location.replace("login.html");
}



var show_search=false;

//get all offices
$(document).ready(function(){
	
	
	$.ajax({
		type: 'GET',
		url: '/ood/offices/approved',
		dataType: 'json',
		success: function (result) {
			populate(result);
			
		},
		error: function(){
			alert("Server not available");
		}
	});
});


//search bar show and hide 

function search_bar()
{
	if(show_search==false)
	{
		
		document.getElementById('header').style.height="90px";
		document.getElementById('search_text').style.display="block";
	}
	if(show_search==true)
	{
		document.getElementById('header').style.height="60px";
		document.getElementById('search_text').style.display="none";
	}
	
	show_search=!show_search;
}


//text listener for search bar. Send another ajax request
function textlistener()
{
	var search=document.getElementById('search_text').value;
	
	$.ajax({
		type: 'GET',
		url: '/ood/offices/approved',
		dataType: 'json',
		
		success: function (result) {
			repopulate(result, search);
		},
		error: function(){
			alert("Server not available");
		}
	});
	
}



//populate the views according to the office request
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
		for(i=0; i<result.length; i++)
		{
			var office_card=document.createElement("div");
			office_card.className ='nd2-card office-cards';
		
			
			office_card.id=result[i].office_id;
			
			//click listener for office 
			office_card.addEventListener("click",function(){
				setCookie("office_id",this.id,1);
				window.location.href='office_description.html';
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
			
			
			var heading=document.createElement("h3");
			heading.innerHTML=result[i].city;
			heading.style.color='#9CD9C9';
			heading.style.paddingLeft='12px';
			heading.style.textAlign='center';
			
			var sub_heading=document.createElement("h5");
			sub_heading.style.color='#fff';
			var sub_heading1=result[i].office_address;
			var sub_heading2='Price per hour: Rs '+result[i].rent_price;
			var sub_heading3='Space for '+result[i].estimated_people+' people';
			sub_heading.innerHTML=sub_heading1;
			var br=document.createElement('br');
			sub_heading.appendChild(br);
			sub_heading.innerHTML+=sub_heading2;
			sub_heading.appendChild(br);
			sub_heading.innerHTML+=sub_heading3;
			sub_heading.style.paddingLeft='12px';
			sub_heading.style.textAlign='center';
			
			card_title.appendChild(heading);
			card_title.appendChild(sub_heading);
			overlay.appendChild(card_title);
			media_card.appendChild(img);
			media_card.appendChild(card_title);
			office_card.appendChild(media_card);
			document.getElementById("page1").appendChild(office_card);
			
			
			
		}
	}
}


//repoulate the views according to the search bar value
function repopulate(result,search)
{
	var container = document.getElementById("page1");
	var elements = container.getElementsByClassName("nd2-card");

	while (elements[0]) {
		elements[0].parentNode.removeChild(elements[0]);
	}
	
	elements = container.getElementsByClassName("message");
	
	while (elements[0]) {
		elements[0].parentNode.removeChild(elements[0]);
	}
	
	
	if(result.length==0)
	{
		var message=document.createElement("p");
		message.className +='message';
		message.innerHTML="No Offices Found";
		document.getElementById("page1").appendChild(message);
	}
	else
	{
		for(i=0; i<result.length; i++)
			
		{
			if((result[i].city.toUpperCase().indexOf(search.toUpperCase())>-1)||(result[i].office_address.toUpperCase().indexOf(search.toUpperCase())>-1)||(result[i].rent_price.toString().toUpperCase().indexOf(search.toUpperCase())>-1)||(result[i].estimated_people.toString().toUpperCase().indexOf(search.toUpperCase())>-1)||(search==""))
			{
				var office_card=document.createElement("div");
				office_card.className ='nd2-card office-cards';
		
			
				office_card.id=result[i].office_id;
				office_card.addEventListener("click",function(){
					setCookie("office_id",this.id,1);
					window.location.href='office_description.html';
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
			
			
				var heading=document.createElement("h3");
				heading.innerHTML=result[i].city;
				heading.style.color='#9CD9C9';
				heading.style.paddingLeft='12px';
				heading.style.textAlign='center';
			
				var sub_heading=document.createElement("h5");
				sub_heading.style.color='#fff';
				var sub_heading1=result[i].office_address;
				var sub_heading2='Price per hour: Rs '+result[i].rent_price;
				var sub_heading3='Space for '+result[i].estimated_people+' people';
				sub_heading.innerHTML=sub_heading1;
				var br=document.createElement('br');
				sub_heading.appendChild(br);
				sub_heading.innerHTML+=sub_heading2;
				sub_heading.appendChild(br);
				sub_heading.innerHTML+=sub_heading3;
				sub_heading.style.paddingLeft='12px';
				sub_heading.style.textAlign='center';
			
				card_title.appendChild(heading);
				card_title.appendChild(sub_heading);
				overlay.appendChild(card_title);
				media_card.appendChild(img);
				media_card.appendChild(card_title);
				office_card.appendChild(media_card);
				document.getElementById("page1").appendChild(office_card);
			
			
			}
		}
	}
}