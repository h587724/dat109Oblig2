<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="background.css">
<meta charset="UTF-8">
<title>Available cars</title>
</head>
<body>
	<form action="chooseCar" method="post">
		<caption><h4>The list of available cars for the desired period:</h4></caption>
			</br>
			Select car:&nbsp;
        	<select name="selectedCarId" style="width: 200px;">
				<c:forEach items="${carList}" var="car">
					<option value="${car.carId}">
						<tr>
							<td>Brand: ${car.brand};</td>
							<td>Model: ${car.model};</td>
							<td>Office address: ${selectedOfficeAddress};</td>
							<td>Color: ${car.color};</td>
							<td>Car type: ${car.type};</td>
							<td>Price per day: ${car.price};</td>
						</tr>
					</option>
				</c:forEach>
			</select>
			<input type="submit" value="Register">
	</form>
</body>
</html>