<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Website Manager!</title>

<link href="../css/bootstrap.min.css" rel="stylesheet">
<link href="../css/signin.css" rel="stylesheet">

</head>

<body>
<div class="signin">
	<div class="signin-head"><img src="../pic/myself.jpg" alt="" class="img-rounded"></div>
	<form class="form-signin" role="form" name="form" action="./login.do" method="post">
	    <div class="message">${message}</div>
		<input type="text" class="form-control" name="username" id="username" required autofocus />
		<input type="password" class="form-control" name="password" id="password" required />
		<button class="btn btn-lg btn-danger btn-block" type="submit">login</button>
		<label class="checkbox">
			<div class="remember"><input type="checkbox" value="remember-me"> Remeber me</div>
		</label>
	</form>
	<form class="form-signin" role="form" name="form" action="register.php" method="post">
		<button class="btn btn-lg btn-success btn-block" type="button">register</button>
	</form>
</div>

</body>
</html>