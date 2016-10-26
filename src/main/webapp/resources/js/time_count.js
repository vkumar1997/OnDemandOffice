
//Created by Vikas Kumar
//Time Counter page
$(document).ready(function(){
	
	window.onbeforeunload= function() { return "The timer will be lost. You will have to restart the timer again. Are you sure you want to leave."; };
	
	var seconds=0;
	var minutes=0;
	var hours=0;
	var price=getCookie('price');
	var ref_id=getCookie('ref');
	var interval,pri;
	$('#verify').click(function(e){
		
		if(ref_id==document.getElementById('ref_verify').value)
		{	
			document.getElementById('timer_cont').style.display='block';
			document.getElementById('form').style.display='none';
			interval=window.setInterval(function(){
				// do your thing
				seconds=seconds+1;
	    
				if(seconds==60)
				{
					minutes=minutes+1;
					seconds=0;
				}
				if(minutes==60)
				{
					hours=hours+1;
					minutes=0;
				}
	    
	    
				document.getElementById('time').innerHTML=hours+" : "+minutes+" : "+seconds;
				if(seconds<10)
				{
					document.getElementById('time').innerHTML=hours+" : "+minutes+" : 0"+seconds;
				}
				if(minutes<10)
				{
					document.getElementById('time').innerHTML=hours+" : 0"+minutes+" : "+seconds;
				}
				if(minutes<10 && seconds<10)
				{
					document.getElementById('time').innerHTML=hours+" : 0"+minutes+" : 0"+seconds;
				}	
	    
				document.getElementById('total').innerHTML="Rs "+Number((hours+minutes/60+seconds/3600)*price).toFixed(2);
				pri=Number((hours+minutes/60+seconds/3600)*price).toFixed(2);
			}, 1000);
		}
		else
		{
			alert('Wrong Reference Id. Please consult the person who booked your office');
		}
				
	});
	
	$('#stop').click(function(e){
		clearInterval(interval);
		document.getElementById('stop').style.display='none';
		$.ajax({
			type: 'POST',
			url:"/ood/status/",
			data:{full_price:pri, reference:ref_id},
			
			success: function (result) 
			{
				window.location.replace("request_history.html");
			},
			error: function(ts)
			{
				alert("Server not available");
			}
		});
	});
});