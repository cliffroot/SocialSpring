<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Welcome screen</title>
</head>
<body bgcolor=#A7A9C2>
<table style="background-color:#8489C2;" width =100%>
<tr>
<td width=40% ailgn="right">
	<h2> Awesome Social Network 1.4  </h2>
</td> 
<td width = 50% align="right">
	<span>
	${user.name} ${user.surname}
	</span>
</td>
<td width = 10% align="left">
	<form method = POST action = "/Social/pages/logout">
		<input type="submit"  value="Log Out"/>
	</form>
</td>
</tr>
</table>	
	
<table width=100%> 
<tr>
	<td width=20%>&nbsp</td>
	<td width=60%>
		<table width = 100% style="background-color:#6671ED;">
		<tr>
		<td width = 30%"><a href = "/Social/pages/groups"> Groups </a></td>
		</tr>
		<tr>
		<td width = 30%"><a href = "/Social/pages/account">My Account </a></td>
		</tr>
		</table>
	</td>
	<td width=20%>&nbsp</td>
</tr>
</table>
</body>
</html>