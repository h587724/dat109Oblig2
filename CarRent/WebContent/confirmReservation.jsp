<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="background.css">
<title>Confirmation of the reservation</title>
</head>
<body>
	<form action="confirmReservation" method="post">
		<c:set var="car" value="${selectedCar}"></c:set>
		<h2>The details about your reservation: </h2>
		</br>Car: ${car.brand}, ${car.model}, ${car.color}, type: ${car.type}.
		</br>Dates: rent from ${rentFromDate}, and return date ${rentTillDate}. ${timeDifference} days in total.
		</br>Pickup location: ${selectedOfficeAddress}.
		</br>To proceed further, please enter the following information:</br>
		Enter your name:<input type="text" name="custName" required></br>
		Enter your last name:<input type="text" name="custLName" required></br>
		Enter your phone number:<input type="text" name="custPhoneNr" required></br>
		Enter your card details:<input type="text" name="cardNr" required></br>
		Enter your address:<input type="text" name="custAddress" required></br>
		<input type="submit" value="Finish the reservation">
	</form>
</body>
</html>