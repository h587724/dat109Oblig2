package bean;

import java.util.Date;

public class Reservation {

	private int officeId;
	private int custId;
	private int carId;
	private int timeSpan; 		// estimated in days
	private String regDate;
	private String rentFrom;
	private String returnDate;
	private int kmOnCar;
	private int cardId;
	private int reservationId;
	
	public Reservation () {
		this.officeId = 0;
		this.custId = 0;
		this.carId = 0;
		this.timeSpan = 0;
		this.regDate = null;
		this.rentFrom = null;
		this.returnDate = null;
		this.kmOnCar = 0;
		this.cardId = 0;
		this.reservationId = 0;
	}
	
	
	
	public Reservation (int officeId, int custId, int carId, int timeS, String regDate, String rentFrom, String returnDate, int km, int card) {
		this.officeId = officeId;
		this.custId = custId;
		this.carId = carId;
		this.timeSpan = timeS;
		this.regDate = regDate;
		this.rentFrom = rentFrom;
		this.returnDate = returnDate;
		this.kmOnCar = km;
		this.cardId = card;
		this.reservationId = 0;
	}
	
	public int getOfficeId() {
		return officeId;
	}
	
	public void setOfficeId(int officeId) {
		this.officeId = officeId;
	}
	
	public int getCustId() {
		return custId;
	}
	
	public void setCustId(int custId) {
		this.custId = custId;
	}
	
	public int getCarId() {
		return carId;
	}
	
	public void setCarId(int carId) {
		this.carId = carId;
	}
	
	public int getTimeSpan() {
		return timeSpan;
	}
	
	public void setTimeSpan(int timeSpan) {
		this.timeSpan = timeSpan;
	}
	
	public String getRegDate() {
		return regDate;
	}
	
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	
	public String getRentFrom() {
		return rentFrom;
	}
	
	public void setRentFrom(String rentFrom) {
		this.rentFrom = rentFrom;
	}
	
	public String getReturnDate() {
		return returnDate;
	}
	
	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}
	
	public int getKmOnCar() {
		return kmOnCar;
	}
	
	public void setKmOnCar(int kmOnCar) {
		this.kmOnCar = kmOnCar;
	}
	
	public int getCardId() {
		return cardId;
	}
	
	public void setCardId(int cardId) {
		this.cardId = cardId;
	}
	
	public int getReservationId() {
		return reservationId;
	}
	
	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}
	
}
