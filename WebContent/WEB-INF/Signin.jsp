<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Log In Screen</title>
</head>
<body>
	<h1> Please login </h1>
	<form method = POST action = "/Social/pages/welcome">
		<input type = "hidden" name = "login" value = "1"/>
		<input type = "text" placeholder = "Login" name = "name" />
		<input type = "password" placeholder = "Password" name = "password"/>
		<input type = "submit" value = "Login!"/>
	</form>	
	or register <a href="/Social/pages/register"> here </a>
</body>
</html>