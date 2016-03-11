
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
				alert('available');
			} else {
				console.log('failure');
				alert('not available');
			}
			
		},
		error: function(data){
			console.log('failure'+ obj);
			
		}
	});
	
}


function getDonorList() {
console.log("getDonorList :: entered");
/*var bloodGroup = ($('#bloodGroup').val()!= null)?$('#bloodGroup').val() : "";
var state = ($('#states').val() != null) ? $('#states').val() : "";
var district = ($('#districts').val()!=null) ? $('#districts').val() : "";
var subDistrict = ($('#subDistricts').val() != null)? $('#subDistricts').val() : "";
var data = [bloodGroup,state,district,subDistrict];*/
$('#donorList').DataTable({
	"processing": true,
    "serverSide": true,
    //"data" :data,
    ajax: {
    url: "donorSearch.htm",
    type: 'POST'
}
});
console.log("getDonorList :: leaving");
} 



function getStateslist(){
	var data = {};
	var $states = $('#states');
	$states.empty();
	var url = "getStatesList.htm";
	data.country = "india";
	$.ajax({
		url: url,
		data: data,
		success : function(response){
			$.each(response.states, function(i,value){
				$states.append('<option>'+value.state+'</option>')
			})
			},
	error : function(obj){
		alert("something went wrong"+obj);
	}
		
	});	
	
}

function getDistrictslist(){
	var data = {};
	var $districts = $('#districts');
	$districts.empty();
	var url = "getDistrictsList.htm";
	data.state = $('#states').val();
	$.ajax({
		url: url,
		data: data,
		success : function(response){
			$.each(response.districts, function(i,value){
				$districts.append('<option>'+value.district+'</option>')
			})
			},
	error : function(obj){
		alert("something went wrong"+obj);
	}
		
	});	
	
}

function getSubDistrictslist(){
	var data = {};
	var $subDistricts = $('#subDistricts');
	$subDistricts.empty();
	var url = "getSubDistrictsList.htm";
	data.district = $('#districts').val();
	$.ajax({
		url: url,
		data: data,
		success : function(response){
			$.each(response.subDistricts, function(i,value){
				$subDistricts.append('<option>'+value.subDistrict+'</option>')
			})
			},
	error : function(obj){
		alert("something went wrong"+obj);
	}
		
	});	
}