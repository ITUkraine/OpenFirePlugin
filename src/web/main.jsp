<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="announcementTab" content="myPluginPage" />
<title>Send announcements to users</title>
</head>
<body>
	Enter message to send to all users:
	<br>
	<form action="/plugins/announcements/status" method="POST">
		<textarea rows="5" cols="30" name="message"></textarea>
		<br> <input type="submit" value="submit">
	</form>
</body>
</html>