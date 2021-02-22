<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Car Rental System</title>
	<link rel="stylesheet" type="text/css" href="background.css">
	<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script>
        $(function () {
            $(".datepicker").datepicker();
        });
    </script>
    <style>
    	.datepicker{
    	}
    </style> 
<title></title>
</head>
<body>
	<h1 id="h1">Group 12 Car Rental System</h1>
        <form action="mainPage" method="post" name="rentFrom" id="form">
        	Select an office:&nbsp;
        	<select name="selectedOfficeId" style="width: 200px;">
				<c:forEach items="${officeList}" var="office">
					<option value="${office.officeId}">
					${office.address}
					</option>
				</c:forEach>
			</select>
       		<h4>When do you want to acquire car?: </h4>
            <input type="text" name="rentFromDate" class="datepicker"/>
            <h4>When do you want to return car?: </h4>
            <input type="text" name="rentTillDate" class="datepicker"/>
   
            <input type="submit" value="Register">
        </form>
		<form action="checkReservation" method="post">
			If you want to check your reservation, type in your reference number and hit the button below!
			</br>
			<input type="text" name="reference">
			<input type=submit value="Check previous reservation">
		</form>
</body>
</html>