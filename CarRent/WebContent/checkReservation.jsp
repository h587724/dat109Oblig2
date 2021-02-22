<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="background.css">
<title>Check reservation</title>
</head>
<body>
<form action="checkReservation" method="get">
	<h2>Your reservation details:</h2></br>
	Office location: ${referenceOffice.address}</br>
	Your details: ${referenceCustomer.name} ${referenceCustomer.lName}</br>
	Car: ${referenceCar.brand} ${referenceCar.model}, ${referenceCar.color}</br>
	Dates: total of ${reservationRef.timeSpan} days from ${reservationRef.rentFrom} to ${reservationRef.returnDate}</br>
	The day you registered the reservation: ${reservationRef.regDate}</br>
	<a href="MainPage.jsp">Go back to the main page!</a>
</form>
</body>
</html>