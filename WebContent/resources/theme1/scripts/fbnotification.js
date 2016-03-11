
	           $(document).ready(function() {
	        	   //getStateslist();
	        	   //getDistrictslist();
	        	   //getSubDistrictslist();
	        	   $.ajaxSetup({ cache: true });
	        	   $.getScript('//connect.facebook.net/en_US/sdk.js', function(){
	        	     FB.init({
	        	       appId: '1626283114256597',
	        	       version: 'v2.5' 
	        	     });     
	        	   } );
	        	     
	        	
	        	    $('#notification').click(function(){
	        	    	console.log("user clicked on send notification");
	        	        FB.getLoginStatus(function(response){
		        	    	if(response.status == 'connected'){
		        	    		console.log('facebook user logged in'+response.status);
		        	    		if(response.authResponse){
		        	    				FB.api('/me',function(response){
	        	    					console.log('its good to see you '+response.name);
	        	    					sendNotification();
	        	    				});
	        	    			}
	        	    			else{
	        	    				console.log('user has cancelled the login');
	        	    			}
		        	    	}
		        	    	else if(response.status == 'not_authorized'|| response.status == 'unknown'){
		        	    		FB.login(function(response){
		        	    			if(response.authResponse){
		        	    				
		        	    				FB.api('/me',function(response){
		        	    					console.log('its good to see you '+response.name);
		        	    				});
		        	    				sendNotification();
		        	    				
		        	    			}
		        	    			else{
		        	    				console.log('user has cancelled the login');
		        	    			}
		        	    		});
		        	    		console.log('not connected'+response.status);
		        	    	}
		        	    	
		        	    });	
	        	    });
	        	    
	        	   });
	        	   
	        	   function getacessToken(){
	    				var accessToken = '1626283114256597|d009f78c766e45f09bd1dfe7a0bb966f';
	    				return accessToken;
   	    			
	        	   }
	        	   function getCallbackURL(){
	    				
	    				return '';
  	    			
	        	   }
	        	   
	        	   function getDonorFbId(){
	        		   // we need to get the fb id of the donor to which notification to be send.
	        		   /* var user = document.getElementById(elementId)
	        		   $.ajax({
	        			   url : 'getFaceBookId.htm',
	        			   data : data,
	        			   
	        		   }); */
	        		  var donorFbId = '942491815828177';
	        		  console.log('donor user id is ::: '+donorFbId);
	        		  return donorFbId;
	        	   }
	        	   
	        	   function sendNotification(){
	        		   // to get the app access code now we are hard coding it, in future we need to get it from graph api
	        		   /* FB.api('/oauth/access_token',
	        				   'GET',
	        				   {"client_id":"1626283114256597",
	        			   		"client_secret":"d009f78c766e45f09bd1dfe7a0bb966f"
	        				   
	        				   },
	        				   function(response){
	        					   
	        				   }
	        				   ); */
	        			var accessToken  = getacessToken();
	        			var callBackUrl =  getCallbackURL();
	        			var donorFbId = getDonorFbId();
	        	   FB.api(
	        			   '/'+donorFbId+'/notifications?access_token='+accessToken,
	        			   'POST',
	        			   {"template":"we need a B+ blood testing6","href":"localhost:8080/home.jsp"},
	        			   function(response) {
	        				   if(response.success){
	        			       console.log('notifications posted '+response.success);
	        			       
	        				   }
	        				   else{
	        					   console.log('error occuredd '+response.error.message);
	        				   }
	        			   }
	        			 );
	        	   }
	        	   
	        	   
	        	  