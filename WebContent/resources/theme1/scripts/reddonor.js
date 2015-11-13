
function isEmailAvailable(){
	var email = document.getElementById("email").value;
	var data = {};
	var url = "isEmailAvailable.htm";
	data.email = email;
	$.ajax({
		url: url,
		data: data,
		success: function(obj){
			if(obj.model.status) {
				console.log('sucess');
				// print error beside email input
				alert('not available');
			} else {
				console.log('failure');
				alert('available');
			}
			
		},
		error: function(data){
			console.log('failure'+ obj);
			
		}
	});
	
}