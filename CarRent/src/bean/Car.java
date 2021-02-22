package bean;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import DAO.Database;

public class Car {

	private int carId;
	private int officeId;
	private String brand;
	private String model;
	private String color;
	private String type;
	private String availability;
	private String price;
	private int kmOnCar;
	
	public Car () {
		this.carId = 0;
		this.officeId = 0;
		this.brand = null;
		this.model = null;
		this.color = null;
		this.type = null;
		this.availability = null;
	}
	
	public Car (int id, int office, String brand, String model, String color, String type, String availability, int km) {
		this.carId = id;
		this.officeId = office;
		this.brand = brand;
		this.model = model;
		this.color = color;
		this.type = type;
		this.availability = availability;
		Database db = new Database();
		try {
			this.price = db.getPricing(type);
		} catch (SQLException e) {	}
		this.kmOnCar = km;
	}
	
	public String getPrice() {
		return price;
	}
	
	public void setPrice(String price) {
		this.price = price;
	}
	
	public int getCarId() {
		return carId;
	}

	public void setCarId(int carId) {
		this.carId = carId;
	}

	public int getOfficeId() {
		return officeId;
	}
	
	public void setOfficeId(int officeId) {
		this.officeId = officeId;
	}
	
	public String getBrand() {
		return brand;
	}
	
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public String getModel() {
		return model;
	}
	
	public void setModel(String model) {
		this.model = model;
	}
	
	public String getColor() {
		return color;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getAvailability() {
		return availability;
	}
	
	public void setAvailability(String availability) {
		this.availability = availability;
	}
	
	public int getKmOnCar() {
		return kmOnCar;
	}
	
	public void setKmOnCar(int km) {
		this.kmOnCar = km;
	}
	
}
