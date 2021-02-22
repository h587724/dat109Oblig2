package bean;

public class Customer {

	private String name;
	private String lName;
	private int custId;
	private String address;
	private String phoneNr;
	private int card;
	
	public Customer () {
		this.name = null;
		this.lName = null;
		this.custId = 0;
		this.address = null;
		this.phoneNr = null;
		this.card = 0;
	}
	
	public Customer (String name, String lName, String address, String phone) {
		this.name = name;
		this.lName = lName;
		this.custId = 0;
		this.address = address;
		this.phoneNr = phone;
		this.card = 0;
	}
	
	public Customer (String name, String lName, String address, String phone, int card) {
		this.name = name;
		this.lName = lName;
		this.custId = 0;
		this.address = address;
		this.phoneNr = phone;
		this.card = card;
	}
	
	public Customer (String name, String lName, int id, String address, String phone, int card) {
		this.name = name;
		this.lName = lName;
		this.custId = id;
		this.address = address;
		this.phoneNr = phone;
		this.card = card;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getlName() {
		return lName;
	}
	
	public void setlName(String lName) {
		this.lName = lName;
	}
	
	public int getCustId() {
		return custId;
	}
	
	public void setCustId(int custId) {
		this.custId = custId;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getPhoneNr() {
		return phoneNr;
	}
	
	public void setPhoneNr(String phoneNr) {
		this.phoneNr = phoneNr;
	}
	
	public int getCard() {
		return card;
	}
	
	public void setCard(int card) {
		this.card = card;
	}
	
	
	
}
