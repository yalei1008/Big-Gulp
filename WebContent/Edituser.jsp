<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<title>Edit Account Information</title>
</head>
<body>

<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand" href="restlist">Gulp!</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
		<li><a href="restlist">Account Information</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <!-- li><a href="login"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
        <li class="active"><a href="login"><span class="glyphicon glyphicon-log-in"></span> Login</a></li -->
        ${navRight}
      </ul>
    </div>
  </div>
</nav>

<div class="container">

	<h3>Edit Account Information</h3>		
		<form role="form" method="post" action="edituser">
			<input type="hidden" name="type" value="1">
			<div class="form-group">
				<input type="text" class="form-control" name="name" placeholder="Edit name" value="${name}">
			</div>
			
			<div class="form-group">
				<input type="text" class="form-control" name="zip" placeholder="Edit Zipcode" value="${zip}">
			</div>
			
			<div class="form-group">
				<input type="text" class="form-control" name="email" placeholder="Edit Email" value="${email}">
			</div>
			<button type="submit" class="btn btn-default" id="submit">Submit</button>
		</form>
</div>
</body>
</html>