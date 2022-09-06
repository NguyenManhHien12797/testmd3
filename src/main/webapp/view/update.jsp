
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>add</title>
</head>
<body>
<form action="/Students?action=update" method="post">
    <input type="text" class="name" placeholder="name" name="name" value="${students.name}">
    <input type="text" class="dateOfBirth" placeholder="dateOfBirth" name="dateOfBirth" value="${students.dateOfBirth}">
    <input type="text" class="address" placeholder="address" name="address" value="${students.address}">
    <input type="text" class="phoneNumber" placeholder="phoneNumber" name="phoneNumber" value="${students.phoneNumber}">
    <input type="text" class="email" placeholder="email" name="email" value="${students.email}">
    <input type="text" class="classroom" placeholder="classroom" name="classroom" value="${students.classroom}">
    <input type="submit" class="btn" value="Nhấn">
</form>

</body>
</html>
