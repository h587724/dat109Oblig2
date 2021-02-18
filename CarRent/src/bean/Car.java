package bean;

public class Car {

	private int carId;
	private int officeId;
	private String brand;
	private String model;
	private String color;
	private Pricing type;
	private int availability;
	
	public Car () {
		this.carId = 0;
		this.officeId = 0;
		this.brand = null;
		this.model = null;
		this.color = null;
		this.type = null;
		this.availability = 0;
	}
	
	public Car (int id, int office, String brand, String model, String color, Pricing type) {
		this.carId = id;
		this.officeId = office;
		this.brand = brand;
		this.model = model;
		this.color = color;
		this.type = type;
		this.availability = 1;
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
	
	public Pricing getType() {
		return type;
	}
	
	public void setType(Pricing type) {
		this.type = type;
	}
	
	public int getAvailability() {
		return availability;
	}
	
	public void setAvailability(int availability) {
		this.availability = availability;
	}
	
	
}
