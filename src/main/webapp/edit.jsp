<%@page import="sj.dto.Student"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%Student student=(Student)request.getAttribute("student"); %>
<form action="edit" method="post">
Id::<input type="number" name="id" value="<%=student.getId()%>" readonly="readonly">
<br>
<br>
Name::<input type="text" name="name" value="<%=student.getName()%>" required="required">
<br>
<br>
Email::<input type="email" name="email" value="<%=student.getEmail()%>" required="required">
<br>
<br>
Password::<input type="password" name="password" value="<%=student.getPassword()%>" required="required">
<br>
<br>
Address::<input type="text" name="address" value="<%=student.getAddress()%>" required="required">
<br>
<br>
Phone::<input type="tel" name="phone" value="<%=student.getPhone()%>" required="required">
<br>
<br>
Course::Development<input type="radio" name="course" value="Development">
        Testing<input type="radio" name="course" value="Testing">
<br>
<br>
<input type="submit" value="EDIT">

</form>
</body>
</html>