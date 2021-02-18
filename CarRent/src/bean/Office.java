package bean;

public class Office {
	
	private int officeId;
	private String phoneNr;
	private String address;
	
	public Office () {
		officeId = 0;
		phoneNr = null;
		address = null;
	}
	
	public Office (int id, String phone, String address) {
		this.officeId = id;
		this.phoneNr = phone;
		this.address = address;
	}
	
	public int getOfficeId() {
		return officeId;
	}
	
	public void setOfficeId(int officeId) {
		this.officeId = officeId;
	}
	
	public String getPhoneNr() {
		return phoneNr;
	}
	
	public void setPhoneNr(String phoneNr) {
		this.phoneNr = phoneNr;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
}
