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
 <img width=10% height=10% src="/Social/images/${user.photoUrl}" />
<form method = POST action = "/Social/pages/changephoto" enctype="multipart/form-data">
	<input type = "file"   placeholder = "Your photo :)" name = "Photo"/> <br/>
	<input type = "submit" placeholder = "change photo" value = "change photo"/>
</form>
<h3>
You can change your password in here:
</h3>
<form method = POST action = "/Social/pages/changepassword">
	<input type="hidden"     name="changePassword" value="1"/>
	<input type="password"   name="oldPassword" placeholder="your old password"/><br/>
	<input type="password"   name="newPassword" placeholder="your new password"/><br/>
	<input type="submit" value="Change password"/>
</form>
<center>
<table border = 1>
<tr> <td> <b> Name </b> </td> <td> ${user.name } </td> </tr>
<tr> <td> <b> Surname </b> </td> <td> ${user.surname } </td> </tr>
<tr> <td> <b> Password </b> </td> <td> ${user.password } </td> </tr>
</table> 
</center>
</body>
</html>