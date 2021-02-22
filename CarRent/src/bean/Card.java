package bean;

public class Card {

	private int cardId;
	private String number;
	
	public Card () {
		cardId = 0;
		number = null;
	}
	
	public Card (String num) {
		this.cardId = 0;
		this.number = num;
	}
	
	public Card (int id, String num) {
		this.cardId = id;
		this.number = num;
	}
	
	public int getCardId() {
		return cardId;
	}
	
	public void setCardId(int cardId) {
		this.cardId = cardId;
	}
	
	public String getNumber() {
		return number;
	}
	
	public void setNumber(String number) {
		this.number = number;
	}
	
}
