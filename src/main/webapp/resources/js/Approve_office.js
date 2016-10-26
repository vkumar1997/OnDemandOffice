//Created by Vikas Kumar and Piyush Malhotra

//approve an office
$(document).ready(function(){
    $('#approve').click(function(e){
    	
		e.preventDefault();
		var id=getCookie("office_id");
		
		$.ajax({
			url:"/ood/approve/"+id,
			success: function (result) 
			{
				
				window.location.replace("Approved_offices.html");
			},
			error: function(ts)
			{
				alert("Server not available");
			}
		});
	});
})


//engage or disengage an office
$(document).ready(function(){
	
	var id=getCookie("office_id");
	var approval;
	
	
	//hide the update button for admin
	if(getCookie("login_type")=="admin" && document.getElementById('update')!=null)
	{
		document.getElementById('update').style.display='none';
	}
	
	
	//get the current office status
	$.ajax({
		type: 'GET',
		url: '/ood/offices/id/'+id,
		dataType: 'json',
		success: function (result) {
			approval=result.approval;
			
			
			 if(getCookie("login_type")=="admin" && document.getElementById('engage')!=null || approval=='Waiting')
			    {
			    	document.getElementById('engage').style.display='none';
			    }
			 
			    if(approval=="Approved")
			    {
			    	document.getElementById('engage').value="Engage Office";
			    	document.getElementById('engage').style.backgroundColor="#006400";
			    	
			    }
			    if(approval=="Engaged")
			    {
			    	
			    	document.getElementById('engage').value="Approve Office";
			    	document.getElementById('engage').style.backgroundColor="#800000";
			    	
			    }
			    
			    //engage an office if available and vice versa
			    $('#engage').click(function(e){
			    	e.preventDefault();
			    	var id=getCookie("office_id");
			    	if(document.getElementById('engage').value=="Engage Office")
			    	{
			    		$.ajax({
			    			url:"/ood/engage/"+id,
			    			success:function(result)
			    			{
			    				document.getElementById('engage').value="Approve Office";
					    		document.getElementById('engage').style.backgroundColor="#800000";
					    		window.location.replace('office_uploads.html');
			    			}
			    			,error: function(ts)
			    			{
			    				alert("Server not available");
			    			}
			    		});
			   
			    	}
			    	
			    	else if(document.getElementById('engage').value=="Approve Office")
			    	{
			    		$.ajax({
			    			url:"/ood/approve/"+id,
			    			success:function(result)
			    			{
			    				document.getElementById('engage').value="Engage Office";
					    		document.getElementById('engage').style.backgroundColor="#006400";
					    		window.location.replace('office_uploads.html');
			    			}
			    			,
			    			error: function(ts)
			    			{
			    				alert("Server not available");
			    			}
			    		});
			    		
			    	}
			    });
			
		},
		error: function(){
			alert("Server not available");
		}
	});
	
	
   
})


//reject an office
$(document).ready(function(){
    $('#reject').click(function(e){
		e.preventDefault();
		var id=getCookie("office_id");
		$.ajax({
			url:"/ood/reject/"+id,
			success: function (result) 
			{
				
				window.location.replace("Rejected_offices.html");
			},
			error: function(ts)
			{
				alert("Server not available");
			}
		});
	});
})



//delete an office
$(document).ready(function(){
    $('#delete').click(function(e){
		e.preventDefault();
		var id=getCookie("office_id");
		$.ajax({
			url:"/ood/delete/"+id,
			success: function (result) 
			{
				alert("Deleted Succesfully");
				if(getCookie("login_type")=="admin")
				{
					window.location.replace("Approved_offices.html");
				}
				if(getCookie("login_type")=="user")
				{
					window.location.replace("Find_Office.html");
				}
			},
			error: function(ts)
			{
				alert("Server not available");
			}
		});
	});
})


//move to this location if this button is clicked
function update()
{
	window.location.href="updateOffice.html";
}