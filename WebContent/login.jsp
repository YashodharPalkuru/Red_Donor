<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login To Blood India</title>
</head>
<body>
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
							<form class="form-signin" action="register.htm" method="POST">
							   <div class="form-group has-feedback">
								<input type="text" name="mobile" class="form-control" placeholder="Mobile Number" required autofocus>
								<i class="glyphicon glyphicon-phone form-control-feedback"></i>
							   </div>
							   <div class="form-group has-feedback">
								<input type="text" name="firstname" class="form-control" placeholder="First Name" required>
								<i class="glyphicon glyphicon-user form-control-feedback"></i>
								</div>
								<div class="form-group has-feedback">
								<input type="text" name="lastname" class="form-control" placeholder="Last Name" required>
								<i class="glyphicon glyphicon-user form-control-feedback"></i>
								</div>
								<div class="form-group has-feedback">
								<input type="password" class="form-control" placeholder="Password" required>
								<i class="glyphicon glyphicon-lock form-control-feedback"></i>
								</div>
								<div class="form-group has-feedback">
								<input type="submit" class="btn btn-lg btn-primary btn-block" value="Sign Up" />
								</div>
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

</body>
</html>