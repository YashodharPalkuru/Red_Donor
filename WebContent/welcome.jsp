<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
			<form class="form-signin" action="register.htm" method="POST">
				<div class="form-group has-feedback">
					<input type="text" name="firstname" class="form-control"
						placeholder="First Name" required> 
						<i class="glyphicon glyphicon-user form-control-feedback"></i>
				</div>
				<div class="form-group has-feedback">
					<input type="text" name="lastname" class="form-control"
						placeholder="Last Name" required> <i
						class="glyphicon glyphicon-user form-control-feedback"></i>
				</div>
				<div class="form-group has-feedback">
					<input type="text" id="email" name="email" class="form-control"
						placeholder="Email" onchange="isEmailAvailable()" required
						autofocus> <i
						class="glyphicon glyphicon-inbox form-control-feedback"></i>
				</div>
				<div class="form-group has-feedback">
					<input type="password" name="password" class="form-control"
						placeholder="Password" required> <i
						class="glyphicon glyphicon-lock form-control-feedback"></i>
				</div>
				<div class="form-group has-feedback">
					<input type="submit" class="btn btn-lg btn-primary btn-block"
						value="Sign Up" />
				</div>
			</form>


		</div>
    </div>






 


  <h1>Welcome ${facebook.name} (${facebook.id})</h1>
  <form action="./post" method="post">
    <textarea cols="80" rows="2" name="message"></textarea>
    <input type="submit" name="post" value="statuses" />
  </form>
<a href="logout.htm">logout</a>

</body>
</html>