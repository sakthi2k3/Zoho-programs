<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Password Validation</title>
</head>
<body>
    <h1>Password Validation</h1>
    <form method="post">
        Enter your password: <input type="password" name="password" required><br>
        <custom:passwordValidator password="${param.password}"/><br>
        <input type="submit" value="Submit">
    </form>
</body>
</html>
