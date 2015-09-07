<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="announcementTab" content="myPluginPage" />
<title>Send announcements to users</title>
</head>
<body>
	<h3>Enter message to send to all users:</h3>
	<br>
	<form action="/plugins/announcements/status" method="POST">
		<label>Server IP</label><br> 
		<input type="text" name="serverIp"> <br>
		
		<label>Server name</label><br> 
		<input type="text" name="serverName"> <br>
		
		<label>Sender username</label><br> 
		<input type="text" name="senderUsername"> <br>
		
		<label>Sender password</label><br> 
		<input type="text" name="senderPassword"> <br>
		
		<br>
		
		<label>Text of the message</label><br>
		<textarea rows="5" cols="30" name="message"></textarea><br>
		
		 <input type="submit" value="submit">
	</form>
</body>
</html>