package bean;

public class Pricing {

	private String type;
	private String price;
	
	public Pricing () {
		type = null;
		price = null;
	}
	
	public Pricing (String type, String price) {
		this.type = type;
		this.price = price;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getPrice() {
		return price;
	}
	
	public void setPrice(String price) {
		this.price = price;
	}
	
}
