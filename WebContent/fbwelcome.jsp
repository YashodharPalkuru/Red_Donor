<html>
<head>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
 <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>welcome to BloodIndia</title>

    <!-- Bootstrap Core CSS -->
    <link href="resources/theme1/styles/bootstrap.css" rel="stylesheet">
 <link href="resources/theme1/styles/login.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="resources/theme1/styles/reddonor.css" rel="stylesheet">
    <link href="resources/theme1/styles/bootstrap-social.css" rel="stylesheet">
    <link href="resources/theme1/fa/css/font-awesome.css" rel="stylesheet">
    
</head>
<body>

  <nav class="navbar  navbar-default navbar-fixed-top" role="navigation">
        <div class="container">
         
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">Red Donor</a>
            </div>

            <div class="collapse navbar-collapse navbar-right " id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="#">About</a>
                    </li>
                    <li>
                        <a href="#">search</a>
                    </li>
                    <li>
                        <a href="#">Contact</a>
                    </li>
                </ul>
            </div>
            
        </div>
        
    </nav>
    
    <div class="container">
    <div class="row">
     	<div class="col-sm-12 col-md-12">
     	<h3 align="center"  >Its Glad to see you ${facebook.name} , please complete the following section.</h3>
  
     	</div>
        <div class="col-sm-6 col-md-4 col-md-offset-4">
        
        	<form class="form-signin" action="register2.htm" method="POST">
        	<input type="hidden" name="facebookID" id ="facebookID" value="${facebook.id}"/>
				<div class="form-group has-feedback">
					<label for="bloodGroup">BloodGroup:</label> 
					<select type="text" name="bloodGroup" class="form-control"
						placeholder="BloodGroup" required>
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
				<div class="form-group has-feedback">
					<label for="mobile">Mobile:</label> 
					<input type="Number" name="mobile" class="form-control" maxlength="10"   
						placeholder="Mobile number" required>
				</div>
				<div class="form-group has-feedback">
					<label for="state">State:</label> 
					<select class="form-control" name="states" id="states" value="" onchange="getDistrictslist();"></select>
				</div>
				<div class="form-group has-feedback">
					<label for="district">District:</label>
					 <select class="form-control"  
									id="districts" name="districts" value="" onchange="getSubDistrictslist();" ></select>
				</div>
				<div class="form-group has-feedback">
					<label for="subDistrict">SubDistrict:</label> 
					<select	class="form-control"  name="subDistricts" 
									id="subDistricts" value="" ></select>
				</div>
				<div class="form-group has-feedback">
					<input type="submit" class="btn btn-lg btn-primary btn-block"
						value="done" />
				</div>
			</form>
        	
            
        </div>
    </div>
</div>
 

    <!-- Put your page content here! -->

    <!-- jQuery -->

	<script src="resources/theme1/scripts/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="resources/theme1/scripts/bootstrap.min.js"></script>
    <script src="resources/theme1/scripts/reddonor.js"></script>

    
    
	<script>
		$(document).ready(function() {
			getStateslist();
			getDistrictslist();
			getSubDistrictslist();
		});
	</script>


</body>
</html>









				







<%-- <%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head><
<script type = "text/javascript" >
    history.pushState(null, null, 'redirectUrl');
    window.addEventListener('popstate', function(event) {
    history.pushState(null, null, 'redirectUrl');
    });
    </script>
</head>
<body>

  <div class="container">
    <div class="row">
			

		</div>
    </div>


 


  <h1>Welcome ${facebook.name} (${facebook.id})</h1>
  <form action="./post" method="post">
    <textarea cols="80" rows="2" name="message"></textarea>
    <input type="submit" name="post" value="statuses" />
  </form>
<a href="logout.htm">logout</a>

</body>
</html> --%>