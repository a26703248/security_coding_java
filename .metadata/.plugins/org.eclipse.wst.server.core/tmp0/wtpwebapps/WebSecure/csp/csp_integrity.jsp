<%@page import="test.Sha384Example"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String hash = Sha384Example.getHex("hello.js");
response.setHeader("Content-Security-Policy", "script-src 'self' 'sha384-" + hash + "';");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CSP integrity</title>
<script src="hello.js" integrity="sha384-NYwKfKobt6i9/OHWahXd6ipPaHKmANRoaUbP12q/hGpq3ywh/OHdZ2sFSZAGI5Am1"></script>
</head>
<body>
	<form action="csp_integrity.jsp" method="post">
		<textarea rows="5" cols="50" name="note">
		</textarea>
		<p />
		<input type="submit" />
	</form>
	<hr>
	${ param.note }
</body>
</html>