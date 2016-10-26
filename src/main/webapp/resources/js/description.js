//office description page
//created by vikas kumar

//if no username cookie present then redirect to login page
if(!checkCookie("username"))
{
	alert("Please enable cookies and login again.")
	window.location.replace("login.html");	
}


var total_images;


//
$(document).ready(function(){
	
	
	//hide shrotlist button if already shortlisted
	if(getCookie('shortlist').indexOf(getCookie('office_id'))>-1 && document.getElementById('shortlist')!=null)
	{
		document.getElementById('shortlist').style.display="none";
	}

	
	//get description according to office id
	var id=getCookie("office_id");
	$.ajax({
		type: 'GET',
		url: '/ood/offices/id/'+id,
		dataType: 'json',
		success: function (result) {
			document.getElementById('head').innerHTML=result.city;
			document.getElementById('subhead').innerHTML=result.office_address;
			document.getElementById('link').addEventListener("click",function(){
				window.location.href="http://maps.google.com/maps?q=" + result.office_address;
			});
			
			
			//populate views
			setCookie('office_address',result.office_address,0.5);
			setCookie('owner_city',result.city,0.5);
			
			document.getElementById('t1').innerHTML=result.name;
			if(result.mail_id==getCookie('username') && document.getElementById('submit')!=null)
			{
				document.getElementById('submit').style.display="none";
				document.getElementById('shortlist').style.display="none";
			}
			setCookie('owner_name',result.name,0.5);
			document.getElementById('t2').innerHTML=result.mail_id;
			setCookie('owner_mail',result.mail_id,0.5);
			document.getElementById('t3').innerHTML=result.phone_no;
			setCookie('owner_phone',result.phone_no,0.5);
			document.getElementById('t4').innerHTML=result.address;
			setCookie('owner_address',result.address,0.5);
			document.getElementById('t5').innerHTML="Rs "+result.rent_price;
			setCookie('office_price',result.rent_price,0.5);
			document.getElementById('t6').innerHTML=result.estimated_people +" people";
			setCookie('estimated_people',result.estimated_people,0.5);
			
			document.getElementById('t7').innerHTML=result.opening_time;
			setCookie('opening_time',result.opening_time,0.5);
			document.getElementById('t8').innerHTML=result.closing_time;
			setCookie('closing_time',result.closing_time,0.5);
			
			document.getElementById('t9').innerHTML=result.pull_requests;
			document.getElementById('t10').innerHTML=result.approval;
			
			document.getElementById('describe').innerHTML="";
			description=result.description.split('\n');
			
			setCookie('describe',result.opening_time,0.5);
			
			if(result.description.length==0)
			{
				document.getElementById('describe').innerHTML=result.description;
				setCookie('describe',result.description,0.5);
			}
			else
			{
				for(var i=0; i<description.length;i++)
				{
					document.getElementById('describe').innerHTML=document.getElementById('describe').innerHTML+description[i]+"<br/>";
					setCookie('describe',getCookie('describe')+description[i]+"<br/>",0.5);
				}
			}
				
			
			if(result.water_facility==false)
			{
				document.getElementById('water').style.display='none';
			}
			if(result.washroom_facility==false)
			{
				document.getElementById('washroom').style.display='none';
			}
			if(result.ac_facility==false)
			{
				document.getElementById('ac').style.display='none';
			}
			if(result.computer_facility==false)
			{
				document.getElementById('computer').style.display='none';
			}
			if(result.office_maid==false)
			{
				document.getElementById('maid').style.display='none';
			}
			
			populate_image(result.files);
		},
		error: function(){
			alert("Server not available");
		}
	});
});


//populate images
function populate_image(files)
{
	total_images=files.length;
	document.getElementById('pre_image_1').style.backgroundImage="url('"+files[0]+"')";
	document.getElementById('pre_image_1').style.backgroundRepeat = "no-repeat";
	document.getElementById('pre_image_1').style.backgroundSize = "cover";
	document.getElementById('pre_image_1').style.backgroundPosition = "center";
	for(var i=2;i<=files.length;i++)
	{
		img_c=document.createElement('div');
		img_c.className="item left images";
		img_c.id="pre_image_"+i;
		img=document.createElement('div');
		img.className="index---hotelimgwrap---13dOU";
		img_c.appendChild(img);
		document.getElementById('contain').appendChild(img_c);
		document.getElementById('pre_image_'+i).style.backgroundImage="url('"+files[i-1]+"')";
		document.getElementById('pre_image_'+i).style.backgroundRepeat = "no-repeat";
		document.getElementById('pre_image_'+i).style.backgroundSize = "cover";
		document.getElementById('pre_image_'+i).style.backgroundPosition = "center";
	}
	
	for(var i=2;i<=files.length;i++)
	{
		var li=document.createElement('li');
		li.id='indicator_'+i;
		var span=document.createElement('span');
		
		document.getElementById('indicate').appendChild(li);
		document.getElementById('indicate').appendChild(span);
		document.getElementById('indicator_'+i).onclick=changeto(i);
		
		
	}
	changeto(1);
	
	
}



//populating the contact and summary of the table view
function tab(a,b,show,tab)
{
	window
	if(show==true)
	{
		document.write("<tr>");
		document.write("<td class='type'>"+a+"</td>");
		document.write("<td id="+tab+" class='value'>"+b+"</td>");
		
		document.write("</tr>");
	}
	else
	{
		document.write("<tr>");
		document.write("<td class='type' style='visibility:hidden;'>"+a+"</td>");
		document.write("<td id="+tab+" class='value' style='visibility:hidden;'>"+b+"</td>");
		document.write("</tr>");
	}
	
}

//for flipping through images
function change(x)
{
	document.getElementById('pre_image_'+current).className='item left';
	document.getElementById('indicator_'+current).className='';
	
		current+=x;
	if(current>total_images)
		current=total_images;
	else if(current<1)
		current=1;
	document.getElementById('pre_image_'+current).className='item next left';
	document.getElementById('indicator_'+current).className='active';
	
}


//for flipping through images
function changeto(x)
{
	change(x-current);
}


//shortlist
function shortlist()
{
	
	setCookie('shortlist',getCookie('shortlist')+"_"+getCookie('office_id'),500)
	document.getElementById('shortlist').style.display="none";	
}


//remove from shortlist
function short_remove()
{
	setCookie('shortlist',getCookie('shortlist').replace('_'+getCookie('office_id'),''));
	window.location.replace('shortlist.html');
}
