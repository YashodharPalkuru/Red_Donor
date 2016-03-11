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
        <div class="col-sm-6 col-md-4 col-md-offset-4">
            <div class="account-wall">
                <div id="my-tab-content" class="tab-content">
						<div class="tab-pane active" id="login">
               		    <img class="profile-img" src=""   alt="">
               			<form class="form-signin" action="login.htm" method="POST">
               			<div class="form-group has-feedback">
						    <input type="text" class="form-control" name="uname" placeholder="Username" required autofocus/>
						    <i class="glyphicon glyphicon-user form-control-feedback"></i>
						</div>

               				<div class="form-group has-feedback">
               				<input type="password" class="form-control" name="pwd" placeholder="Password" required>
               				<i class="glyphicon glyphicon-lock form-control-feedback"></i>
               				</div>
               				
               				<input type="submit" class="btn btn-lg btn-primary btn-block" value="Sign In"  />               				
               			</form>
               			
               			<form class="form-signin" action="" method="">
               				<a class="btn btn-block btn-social btn-facebook" onclick="location='fblogin.htm'">
							    <i class="fa fa-facebook"></i> Sign in with Facebook
							</a>
               			    <a class="btn btn-block btn-social btn-google">
							    <i class="fa fa-google"></i>  Sign in with Google
							</a>
						</form>
               			<div id="tabs" data-tabs="tabs">
               				<p class="text-center"><a href="#register" data-toggle="tab">Need an Account?</a></p>
              				</div>
						</div>
						<div class="tab-pane" id="register">
							<form class="form-signin" action="register1.htm" method="POST">
							    <div class="form-group has-feedback">
								<input type="text" name="firstname" class="form-control" placeholder="First Name" required>
								<i class="glyphicon glyphicon-user form-control-feedback"></i>
								</div>
								<div class="form-group has-feedback">
								<input type="text" name="lastname" class="form-control" placeholder="Last Name" required>
								<i class="glyphicon glyphicon-user form-control-feedback"></i>
								</div>
								<div class="form-group has-feedback">
								<input type="text" id="email" name="email" class="form-control" placeholder="Email" onchange="isEmailAvailable()" required autofocus>
								<i class="glyphicon glyphicon-inbox form-control-feedback"></i>
							    </div>
								<div class="form-group has-feedback">
								<input type="password" name="password"class="form-control" placeholder="Password" required>
								<i class="glyphicon glyphicon-lock form-control-feedback"></i>
								</div>
								<div class="form-group has-feedback">
								<input type="submit" class="btn btn-lg btn-primary btn-block" value="Sign Up" />
								</div>
							</form>
							<form class="form-signin" action="" method="">
               				<a class="btn btn-block btn-social btn-facebook" onclick="location='fblogin.htm'">
							    <i class="fa fa-facebook"></i> Sign up with Facebook
							</a>
               			    <a class="btn btn-block btn-social btn-google">
							    <i class="fa fa-google"></i>  Sign up with Google
							</a>
						</form>
							
						<div id="tabs" data-tabs="tabs">
               			<p class="text-center"><a href="#login" data-toggle="tab">Have an Account?</a></p>
              			</div>
						</div>
					</div>
            </div>
        </div>
    </div>
</div>
 

    <!-- Put your page content here! -->

    <!-- jQuery -->
    <script src="resources/theme1/scripts/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="resources/theme1/scripts/bootstrap.min.js"></script>
    <script src="resources/theme1/scripts/reddonor.js"></script>
    


</body>
</html>