<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Sign Up Screen</title>
</head>
<body>
	<h1> SignUp: </h1>
	<form method = POST action = "/Social/pages/signup" enctype="multipart/form-data">
		<input type = "text" placeholder = "Name" name = "Name" /> <br/>
		<input type = "text" placeholder = "Surname" name = "Surname"/> <br/>
		<input type = "password" placeholder = "Password" name = "Password"/> <br/>
		<input type = "file" placeholder = "Your photo :)" name = "Photo"/> <br/>
		<input type = "submit" value = "Sign up!"/>
	</form>	
</body>
</html>