<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>RedDonor</title>

<!-- Bootstrap Core CSS -->
<link href="resources/theme1/styles/bootstrap.css" rel="stylesheet">
<link href="resources/theme1/styles/login.css" rel="stylesheet">
<!-- Custom CSS -->
<link href="resources/theme1/styles/reddonor.css" rel="stylesheet">
<link href="resources/theme1/styles/bootstrap-social.css"
	rel="stylesheet">
<link href="resources/theme1/fa/css/font-awesome.css" rel="stylesheet">
</head>
<body>

	<nav class="navbar  navbar-default navbar-fixed-top" role="navigation">
		<div class="container">

			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">Red Donor</a>
			</div>

			<div class="collapse navbar-collapse navbar-right "
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li><a href="#">About</a></li>
					<li><a href="#">search</a></li>
					<li><a href="#">Contact</a></li>
				</ul>
			</div>

		</div>

	</nav>
	<div class="container">
		<div class="row">
			<div class="col-sm-12 col-md-10 col-md-offset-1">
				<h3>Search for Donor's</h3>
				
					<div class="row">

						<div class="col-md-2">
							<div class="form-group">
								<label for="bloodGroup">BloodGroup:</label> 
								<select	class="form-control" placeholder="Blood Group" name="bloodGroup" id="bloodGroup"
									value="">
									<option>A+</option>
									<option>A-</option>
									<option>B+</option>
									<option>B-</option>
									<option>AB+</option>
									<option>AB-</option>
									<option>O+</option>
									<option>O-</option>
									<option>BOMBAY</option>
								</select>
							</div>
						</div>
						<div class="col-md-3">
							<div class="form-group">
								<label for="state">State:</label> 
								<select class="form-control"
									 name="states" id="states" value="" onchange="getDistrictslist();">
									 
								</select>
							</div>
						</div>
						<div class="col-md-3">
							<div class="form-group">
								<label for="district">District:</label>
								 <select class="form-control"  
									id="districts" name="districts" value="" onchange="getSubDistrictslist();" ></select>
							</div>
						</div>
						<div class="col-md-3">
							<div class="form-group">
								<label for="subDistrict">SubDistrict:</label> 
								<select	class="form-control"  name="subDistricts" 
									id="subDistricts" value="" ></select>
									
							</div>
						</div>
						<div class="col-md-1">
							<label>&nbsp;</label>
							<button class="btn btn-primary" onclick = "getDonorList();" >Search</button>
						</div>
					</div>
				

			</div>
			<div class="col-sm-12 col-md-10 col-md-offset-1">
				<!-- <table class="table table-bordered" id="donorList" class="display">
					<thead>
						<tr>
							<th>Name</th>
							<th>BloodGroup</th>
							<th>Age</th>
							<th>MobileNumber</th>
							<th>FacebookId</th>
							<th>Location</th>
							<th>Notification type</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
						    <th>Name</th>
							<th>BloodGroup</th>
							<th>Age</th>
							<th>MobileNumber</th>
							<th>FacebookId</th>
							<th>Location</th>
							<th>Notification type</th>
						</tr>
					</tfoot>
				</table> -->
				<table class="table table-bordered" id="donorList" class="display">
					<thead>
						<tr>
							<th>Name</th>
							<th>BloodGroup</th>
							<th>Age</th>
							<th>MobileNumber</th>
							<th>Location</th>
							<th></th>
							<!-- <th>Notification type</th> -->
						</tr>
					</thead>
					<tbody>
						<tr>
						    <td>Yashodhar</td>
							<td>B+</td>
							<td>23</td>
							<td>9676342776</td>
							<td>Chandragiri</td>
							<td><a href="#" id='notification'>Send Notification</a></td>
							<!-- <th>Notification type</th> -->
						</tr>
					</tbody>
				</table>

				
			</div>
		</div>
	</div>


	<!-- Put your page content here! -->

	<!-- jQuery -->
	<script src="resources/theme1/scripts/jquery.js"></script>
	<script src="https://cdn.datatables.net/1.10.11/js/jquery.dataTables.min.js"></script>
	<link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.11/css/jquery.dataTables.css"/>
    <script type="text/javascript" charset="utf8" src="//cdn.datatables.net/1.10.11/js/jquery.dataTables.js"/>

	<!-- Bootstrap Core JavaScript -->
	<script src="resources/theme1/scripts/bootstrap.min.js"></script>
	<script src="resources/theme1/scripts/reddonor.js"></script>
	<script src="resources/theme1/scripts/fbnotification.js"></script>
	
	
	<script>
	 /* var dataSet = [
	               [ "Yashodhar", "A+", "Andhrapradesh", "chittoor1", "chandragiri", "connect" ],
	             /*  [ "Garrett Winters", "Accountant", "Tokyo", "8422", "2011/07/25", "$170,750" ],
	               [ "Ashton Cox", "Junior Technical Author", "San Francisco", "1562", "2009/01/12", "$86,000" ],
	               [ "Cedric Kelly", "Senior Javascript Developer", "Edinburgh", "6224", "2012/03/29", "$433,060" ],
	               [ "Airi Satou", "Accountant", "Tokyo", "5407", "2008/11/28", "$162,700" ],
	               [ "Brielle Williamson", "Integration Specialist", "New York", "4804", "2012/12/02", "$372,000" ],
	               [ "Herrod Chandler", "Sales Assistant", "San Francisco", "9608", "2012/08/06", "$137,500" ],
	               [ "Rhona Davidson", "Integration Specialist", "Tokyo", "6200", "2010/10/14", "$327,900" ],
	               [ "Colleen Hurst", "Javascript Developer", "San Francisco", "2360", "2009/09/15", "$205,500" ],
	               [ "Sonya Frost", "Software Engineer", "Edinburgh", "1667", "2008/12/13", "$103,600" ],
	               [ "Jena Gaines", "Office Manager", "London", "3814", "2008/12/19", "$90,560" ],
	               [ "Quinn Flynn", "Support Lead", "Edinburgh", "9497", "2013/03/03", "$342,000" ],
	               [ "Charde Marshall", "Regional Director", "San Francisco", "6741", "2008/10/16", "$470,600" ],
	               [ "Haley Kennedy", "Senior Marketing Designer", "London", "3597", "2012/12/18", "$313,500" ],
	               [ "Tatyana Fitzpatrick", "Regional Director", "London", "1965", "2010/03/17", "$385,750" ],
	               [ "Michael Silva", "Marketing Designer", "London", "1581", "2012/11/27", "$198,500" ],
	               [ "Paul Byrd", "Chief Financial Officer (CFO)", "New York", "3059", "2010/06/09", "$725,000" ],
	               [ "Gloria Little", "Systems Administrator", "New York", "1721", "2009/04/10", "$237,500" ],
	               [ "Bradley Greer", "Software Engineer", "London", "2558", "2012/10/13", "$132,000" ],
	               [ "Dai Rios", "Personnel Lead", "Edinburgh", "2290", "2012/09/26", "$217,500" ],
	               [ "Jenette Caldwell", "Development Lead", "New York", "1937", "2011/09/03", "$345,000" ],
	               [ "Yuri Berry", "Chief Marketing Officer (CMO)", "New York", "6154", "2009/06/25", "$675,000" ],
	               [ "Caesar Vance", "Pre-Sales Support", "New York", "8330", "2011/12/12", "$106,450" ],
	               [ "Doris Wilder", "Sales Assistant", "Sidney", "3023", "2010/09/20", "$85,600" ],
	               [ "Angelica Ramos", "Chief Executive Officer (CEO)", "London", "5797", "2009/10/09", "$1,200,000" ],
	               [ "Gavin Joyce", "Developer", "Edinburgh", "8822", "2010/12/22", "$92,575" ],
	               [ "Jennifer Chang", "Regional Director", "Singapore", "9239", "2010/11/14", "$357,650" ],
	               [ "Brenden Wagner", "Software Engineer", "San Francisco", "1314", "2011/06/07", "$206,850" ],
	               [ "Fiona Green", "Chief Operating Officer (COO)", "San Francisco", "2947", "2010/03/11", "$850,000" ],
	               [ "Shou Itou", "Regional Marketing", "Tokyo", "8899", "2011/08/14", "$163,000" ],
	               [ "Michelle House", "Integration Specialist", "Sidney", "2769", "2011/06/02", "$95,400" ],
	               [ "Suki Burks", "Developer", "London", "6832", "2009/10/22", "$114,500" ],
	               [ "Prescott Bartlett", "Technical Author", "London", "3606", "2011/05/07", "$145,000" ],
	               [ "Gavin Cortez", "Team Leader", "San Francisco", "2860", "2008/10/26", "$235,500" ],
	               [ "Martena Mccray", "Post-Sales support", "Edinburgh", "8240", "2011/03/09", "$324,050" ],
	               [ "Unity Butler", "Marketing Designer", "San Francisco", "5384", "2009/12/09", "$85,675" ]
	           ];
	             */
	 
	        	  
	        	  // getDonorList();
	        	   
	             /* $('#donorList').DataTable( {
	                   data: dataSet,
	                   columns: [
	                       { title: "Name" },
	                       { title: "BloodGroup" },
	                       { title: "Age" },
	                       { title: "MobileNumber" },
	                       { title: "FacebookId" },
	                       { title: "Location" }
	                   ]
	               } ); */
	          
	
	
	
	
	
	</script>
	


</body>
</html>