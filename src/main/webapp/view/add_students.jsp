<%--
  Created by IntelliJ IDEA.
  User: Acer
  Date: 9/6/2022
  Time: 11:15 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>add</title>
</head>
<body>
<form action="/Students?action=create" method="post">
    <input type="text" class="name" placeholder="name" name="name">
    <input type="date" class="dateOfBirth" placeholder="dateOfBirth" name="dateOfBirth">
    <input type="text" class="address" placeholder="address" name="address">
    <input type="text" class="phoneNumber" placeholder="phoneNumber" name="phoneNumber">
    <input type="text" class="email" placeholder="email" name="email">
    <select name="classroom" id="">
        <option value="1">C0522G1</option>
        <option value="2">C0622H1</option>
    </select>
    <input type="submit" class="btn" value="Nhấn">
</form>

</body>
</html>
