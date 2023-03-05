<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.UUID"%>
<%
// response.setHeader("Content-Security-Policy", "script-src 'self' 'unsafe-inline'");
String cspName = UUID.randomUUID().toString().replaceAll("-", "");
response.setHeader("Content-Security-Policy", "script-src 'self' 'unsafe-inline' 'nonce-" + cspName
		+ "'; style-src 'self' 'unsafe-inline' 'nonce-" + cspName + "';");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CSP nonce</title>
<!-- 在HTML中不帶有 nonce 的 Javascript不會被執行 -->
<script>
	alert("Hacker");
</script>
<!-- 在HTML中執行帶有 nonce 的 Javascript -->
<script nonce="<%=cspName%>">
	alert("OK");
</script>
<style type="text/css" nonce="<%=cspName%>">
.text {
	color: red;
}
</style>
<style type="text/css">
.text {
	color: yellow;
}
</style>
</head>
<body>
	<h1 class="text">Form</h1>
	<form action="csp_nonce.jsp" method="post">
		<textarea rows="5" cols="50" name="note">
		</textarea>
		<p />
		<input type="submit" />
	</form>
	<hr>
	${ param.note }
</body>
</html>