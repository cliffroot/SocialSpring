<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@ taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions' %>
 
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
		<tr><td><h1> ${group.name}</h1> </td> </tr>
		<tr><td> ${group.description} </td> </tr>
		</table>
	</td>
 	
</tr>
</table>
</div> 

<center><h2>Subscribers </h2></center>
<c:if test="${fn:length(users) > 0}">
<table border = 1 width = 50%>
		<c:forEach var="user" items="${users}">
			<tr>
	        	<td> ${user.name } ${user.surname } </td>
	        </tr>
		</c:forEach>
		
</table>
</c:if>
<c:if test="${fn:length(users) eq 0}">
	<h2>There are no subscribers!</h2>
</c:if>
<center><h2>Messages</h2></center>

<c:if test="${subscribed eq true}">
 <form method="POST" action="/Social/pages/addmessage">
 <textarea rows="6" cols="50" name="message" placeholder ="Write your message :)">
 </textarea>
 <input type="hidden" name="groupId" value="${group.id}"/>
 <input type="submit" value="Write Message"/>
 </form>
</c:if>
 
<center>
<table width=80% border = 1>
<tr><td width="15%"> <b>Photo</b> </td> <td width="15%"> <b>User</b> </td> <td><b> Message </b> </td> </tr>
		<c:forEach var="message" items="${messages}">
			<tr>
	        	<td width="15%"> <center> <img width="30%" height="15%" src="/Social/images/${message.user.photoUrl}"/></center></td>
	        	<td width="15%">
	        		<form method="post" action ="/Social/pages/showuser">
	        			<input type = "hidden" name = "userId" value = "${message.user.ID}"/>
	        		 	<a href = "javascript:;" onclick="parentNode.submit();"> ${message.user.name } ${message.user.surname } </a> 
	        		 </form>
	        	</td>
	        	<td width="60%"> ${message.text } </td>
	        </tr>
		</c:forEach>
</table>
</center>
<c:if test="${subscribed eq true}">
<form method="POST" action="/Social/pages/unsubscribe">
<input type="hidden" name="groupId" value="${group.id}"/>
 <input type = "submit" value = "unsubscribe"/>
 </form>
</c:if>
<c:if test="${subscribed eq false }">
<form method="POST" action="/Social/pages/subscribe">
<input type="hidden" name="groupId" value="${group.id}"/>
 <input type = "submit" value = "subscribe"/>
</form>
</c:if>
</body>
</html>