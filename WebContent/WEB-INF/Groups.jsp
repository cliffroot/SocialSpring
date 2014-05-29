<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	
<div style="width:100%; height:auto; background:#6671ED;"> 
<table width=100%> 

<tr>
 	
	<td width=30%>
		<table width = 100% style="border-right:2px dashed white;">
			<tr>
			<td width = 100%"><a href = "/Social/pages/groups"> Groups </a></td>
			</tr>
			<tr>
			<td width = 30%"><a href = "/Social/pages/account">My Account </a></td>
			</tr>
		</table>
	</td>
	<td width=70% >
		<table width="70%" >
		<tr> <td> <b> Name </b> </td> <td> <b> Description </b> </td> </tr>
		<c:forEach var="group" items="${groups}">
			<tr>
	          <td>
	          		<form action="/Social/pages/showgroup" method="post"> <a href = "javascript:;" onclick="parentNode.submit();"> ${group.name} </a>
	          		<input type = "hidden" name = "id" value = "${group.id}"/> </form> 
	          </td>
	          <td>${group.description}</td>
	        </tr>
		</c:forEach>
		</table>
	</td>
 	
</tr>

</table>
</div> 
	<h3>
	You can create some new group in here:
	</h3>
	<form method = POST action = "/Social/pages/creategroup">
		<input type="text" 		name="name"  placeholder="Your group name :)"/> <br/>
		<input type="text" name="description" placeholder="Some group description here..."/>
		<br/>
		<input type="submit" value="Create Group"/>
	</form>
</body>
</html>