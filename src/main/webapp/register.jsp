<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>register</title>

<link href="./css/bootstrap.min.css" rel="stylesheet">
<link href="./css/signin.css" rel="stylesheet">

</head>
<% String type=request.getParameter("type");%>
<body>
<div class="register">
	<form class="form-signin" role="form" name="form" action="./home/register.do" method="post">
	    <div class="message">
        	     <%
        	       if(type!=null&&type.equals("0")){
        	       %>
        	       two passwords are different!
        	     <%
                   }
                   %>
                   <%
                   if(type!=null&&type.equals("-1")){
                   	       %>
                   user existed!
                   <%
                   }
                   %>
        </div>
		<input type="text" class="form-control" name="username" id="username" required autofocus placeholder="User name(used to log in)" />
		<input type="text" class="form-control" name="nickname" id="nickname" required placeholder="Nick name" />
		<input type="password" class="form-control" name="password" id="password" required placeholder="Password"/>
		<input type="password" class="form-control" name="confirmed_password" id="confirmed_password" required placeholder="Confirm Password"/>
		<button class="btn btn-lg btn-danger btn-block" type="submit">register</button>
	</form>
	<form class="form-signin" role="form" name="form" action="./login.jsp" method="post">
		<button class="btn btn-lg btn-warning btn-block" type="submit">cancel</button>
	</form>
</div>

</body>
</html>