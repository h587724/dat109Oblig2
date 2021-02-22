package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import bean.Car;
import bean.Card;
import bean.Customer;
import bean.Office;
import bean.Pricing;
import bean.Reservation;

public class Database {
	
	private static DBConnect dbconn = new DBConnect();
	private static Connection connection = dbconn.setConnection();
	
	public Database () {
		dbconn = new DBConnect();
		connection = dbconn.setConnection();
	}
	
	public void addOffice (Office office) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("insert into office (officeId, phoneNr, address)"
				+ " values (?, ?, ?)");
		statement.setInt(1, office.getOfficeId());
		statement.setString(2, office.getPhoneNr());
		statement.setString(3, office.getAddress());
		statement.execute();
	}
	
	public void addCar (Car car) throws SQLException, ParseException {
		PreparedStatement statement = connection.prepareStatement("insert into cars (carId, officeId, brand, model, color, type, availability, kmOnCar)"
				+ " values (?, ?, ?, ?, ?, ?, ?)");
		statement.setInt(1, car.getCarId());
		statement.setInt(2, car.getOfficeId());
		statement.setString(3, car.getBrand());
		statement.setString(4, car.getModel());
		statement.setString(5, car.getColor());
		statement.setString(6, car.getType());
		DateFormat dateF = new SimpleDateFormat ("yyyy-MM-dd");
		statement.setDate(7, (Date) dateF.parse(car.getAvailability()));
		statement.setInt(8, car.getKmOnCar());
		statement.execute();
	}
	
	public void updateCarAvailability (int id, String toDate) throws SQLException, ParseException {
		PreparedStatement statement = connection.prepareStatement("update cars set availability = ? where carId = ?");
		
		DateFormat dateF1 = new SimpleDateFormat ("MM/dd/yyyy");
		java.util.Date date1 = dateF1.parse(toDate);
		java.sql.Date sqlDate = new Date (date1.getTime());

		statement.setDate(1, sqlDate);
		statement.setInt(2, id);
		statement.execute();
	}
	
	public void addCustomer (Customer customer) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("insert into customers (name, lName, address, phoneNr, cardId)"
				+ " values (?, ?, ?, ?, ?)");
		statement.setString(1, customer.getName());
		statement.setString(2, customer.getlName());
		statement.setString(3, customer.getAddress());
		statement.setString(4, customer.getPhoneNr());
		statement.setInt(5, customer.getCard());
		statement.execute();
	}
	
	public void addCreditCard (Card card) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("insert into creditCard (number) values (?)");
		statement.setString(1, card.getNumber());
		statement.execute();
	}
	
	public void addReservation (Reservation res) throws SQLException, ParseException {
		PreparedStatement statement = connection.prepareStatement("insert into reservations (officeId, customerId, carId, timeSpan,"
				+ "regDate, rentFromDate, rentTillDate, kmOnCar, cardId)"
				+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
		
		statement.setInt(1, res.getOfficeId());
		statement.setInt(2, res.getCustId());
		statement.setInt(3, res.getCarId());
		statement.setInt(4, res.getTimeSpan());
		
		DateFormat dateF1 = new SimpleDateFormat ("MM/dd/yyyy");
		DateFormat dateF2 = new SimpleDateFormat ("yyyy-MM-dd");
		
		java.util.Date date1 = dateF2.parse(res.getRegDate());
		java.sql.Date sqlDate = new Date (date1.getTime());
		
		java.util.Date date2 = dateF1.parse(res.getRentFrom());
		java.sql.Date sqlDate2 = new Date (date2.getTime());

		java.util.Date date3 = dateF1.parse(res.getReturnDate());
		java.sql.Date sqlDate3 = new Date (date3.getTime());

		statement.setDate(5, sqlDate);
		statement.setDate(6, sqlDate2);
		statement.setDate(7, sqlDate3);
		statement.setInt(8, res.getKmOnCar());
		statement.setInt(9, res.getCardId());
		statement.execute();
		System.out.println("Reservations is registred!");
	}
	
	public List<Car> lookupAvailableCars (String from, int officeId) throws SQLException, ParseException {  //Date format: MM-dd-yyyy
		DateFormat dateF1 = new SimpleDateFormat ("MM/dd/yyyy");
		java.util.Date date1 = dateF1.parse(from);
		java.sql.Date sqlDate = new Date (date1.getTime());
		PreparedStatement statement = connection.prepareStatement("select * from cars where availability <= ? and officeId = ?");
		statement.setDate(1, sqlDate);
		statement.setInt(2, officeId);
		List<Car> cars = new ArrayList<>();
		ResultSet rs = statement.executeQuery();
		while (rs.next()) {
			int carId  = rs.getInt("carId");
			int officeId2 = rs.getInt("officeId");
			String brand = rs.getString("brand");
			String model = rs.getString("model");
			String color = rs.getString("color");
			String type = rs.getString("type");
			DateFormat dateF = new SimpleDateFormat ("yyyy-MM-dd");
			String availability = dateF.format(rs.getDate("availability"));
			int km = rs.getInt("kmOnCar");
			cars.add(new Car (carId, officeId2, brand, model, color, type, availability, km));
		}
		return cars;
	}
	
	public Office getOffice (int id) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("select * from office where officeId = ?");
		statement.setInt(1, id);
		ResultSet rs = statement.executeQuery();
		Office office = new Office();
		while (rs.next()) {
			office.setAddress(rs.getString("address"));
			office.setOfficeId(rs.getInt("officeId"));
			office.setPhoneNr(rs.getString("phoneNr"));
		}
		return office;
	}
	
	public List<Office> getOfficeList() throws SQLException {
		List<Office> officeList = new ArrayList<>();
		PreparedStatement statement = connection.prepareStatement("select * from office");
		ResultSet rs = statement.executeQuery();
		while (rs.next()) {
			int id = rs.getInt("officeId");
			String phone = rs.getString("phoneNr");
			String address = rs.getString("address");
			Office office = new Office (id, phone, address);
			officeList.add(office);
		}
		return officeList;
	}
	
	public Car getCar (int id) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("select * from cars where carId = ?");
		statement.setInt(1, id);
		ResultSet rs = statement.executeQuery();
		Car car = new Car();
		while (rs.next()) {
			car.setCarId(rs.getInt("carId"));
			car.setOfficeId(rs.getInt("officeId"));
			car.setBrand(rs.getString("brand"));
			car.setModel(rs.getString("model"));
			car.setColor(rs.getString("color"));
			car.setType(rs.getString("type"));
			DateFormat dateF = new SimpleDateFormat ("yyyy-MM-dd");
			car.setAvailability(dateF.format(rs.getDate("availability")));
		}
		return car;
	}
	
	public Customer getCustomerPhone (String phone) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("select * from customers where phoneNr = ?");
		statement.setString(1, phone);
		ResultSet rs = statement.executeQuery();
		Customer dude = new Customer();
		while (rs.next()) {
			dude.setName(rs.getString("name"));
			dude.setlName(rs.getString("lName"));
			dude.setCustId(rs.getInt("customerId"));
			dude.setAddress(rs.getString("address"));
			dude.setPhoneNr(rs.getString("phoneNr"));
			dude.setCard(rs.getInt("cardId"));
		}
		return dude;
	}
	
	public Customer getCustomer (int id) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("select * from customers where customerId = ?");
		statement.setInt(1, id);
		ResultSet rs = statement.executeQuery();
		Customer dude = new Customer();
		while (rs.next()) {
			dude.setName(rs.getString("name"));
			dude.setlName(rs.getString("lName"));
			dude.setCustId(rs.getInt("customerId"));
			dude.setAddress(rs.getString("address"));
			dude.setPhoneNr(rs.getString("phoneNr"));
			Card card = getCardId(rs.getInt("cardId"));
			dude.setCard(card.getCardId());
		}
		return dude;
	}
	
	public Card getCardId (int id) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("select * from creditCard where card_id = ?");
		statement.setInt(1, id);
		ResultSet rs = statement.executeQuery();
		Card card = new Card ();
		while (rs.next()) {
			card.setCardId(rs.getInt("card_id"));
			card.setNumber(rs.getString("number"));
		}
		return card;
	}
	
	public Card getCard (String id) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("select * from creditCard where number = ?");
		statement.setString(1, id);
		ResultSet rs = statement.executeQuery();
		Card card = new Card ();
		while (rs.next()) {
			card.setCardId(rs.getInt("card_id"));
			card.setNumber(rs.getString("number"));
		}
		return card;
	}
	
	public int getReservationId(int custId) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("select max(reservationId) from reservations where customerId = ?");
		statement.setInt(1, custId);
		ResultSet rs = statement.executeQuery();
		int resId = 0;
		while (rs.next()) {
			resId = rs.getInt(1);
		}
		return resId;
	}
	
	public String getPricing (String type) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("select * from pricing where type = ?");
		statement.setString(1, type);
		ResultSet rs = statement.executeQuery();
		Pricing pricing = new Pricing ();
		while (rs.next()) {
			pricing.setType(rs.getString("type"));
			pricing.setPrice(rs.getString("price"));
		}
		return pricing.getPrice();
	}
	
	public Reservation getReservation (int customerId, int cardId) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("select * from reservations where customerId = ? and cardId = ?");
		statement.setInt(1, customerId);
		statement.setInt(2, cardId);
		ResultSet rs = statement.executeQuery();
		Reservation res = new Reservation ();
		while (rs.next()) {
			res.setOfficeId(rs.getInt("officeId"));
			res.setCustId(rs.getInt("customerId"));
			res.setCarId(rs.getInt("carId"));
			res.setTimeSpan(rs.getInt("timeSpan"));
			DateFormat dateF1 = new SimpleDateFormat ("MM/dd/yyyy");
			String regDate = dateF1.format(rs.getDate("regDate"));
			String rentFrom = dateF1.format(rs.getDate("rentFromDate"));
			String returnDate = dateF1.format(rs.getDate("rentTillDate"));
			res.setRegDate(regDate);
			res.setRentFrom(rentFrom);
			res.setReturnDate(returnDate);
			res.setKmOnCar(rs.getInt("kmOnCar"));
			res.setCardId(rs.getInt("cardId"));
			res.setReservationId(rs.getInt("reservationId"));
		}
		return res;
	}
	
	public Reservation getReservation (int id) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("select * from reservations where reservationId = ?");
		statement.setInt(1, id);
		ResultSet rs = statement.executeQuery();
		Reservation res = new Reservation ();
		while (rs.next()) {
			res.setOfficeId(rs.getInt("officeId"));
			res.setCustId(rs.getInt("customerId"));
			res.setCarId(rs.getInt("carId"));
			res.setTimeSpan(rs.getInt("timeSpan"));
			DateFormat dateF1 = new SimpleDateFormat ("MM/dd/yyyy");
			String regDate = dateF1.format(rs.getDate("regDate"));
			String rentFrom = dateF1.format(rs.getDate("rentFromDate"));
			String returnDate = dateF1.format(rs.getDate("rentTillDate"));
			res.setRegDate(regDate);
			res.setRentFrom(rentFrom);
			res.setReturnDate(returnDate);
			res.setKmOnCar(rs.getInt("kmOnCar"));
			res.setCardId(rs.getInt("cardId"));
			res.setReservationId(rs.getInt("reservationId"));
		}
		return res;
	}
	
	public void updateCarAvailability (int id, int availability) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("update cars set availability = ? where carId = ?");
		statement.setInt(1, availability);
		statement.setInt(2, id);
		statement.execute();
	}
	
	public void deleteReservation (int id) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("delete from reservations where reservationId = ?");
		statement.setInt(1, id);
		statement.execute();
	}
	
	public void updateReservationReturnDate (int id, Date newReturn) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("update reservations set rentTillDate = ? where reservationId = ?");
		statement.setDate(1, newReturn);
		statement.setInt(2, id);
		statement.execute();
	}
	//testing
	public static void main(String[] args) throws ParseException, SQLException {
	Database db = new Database();
	Office office = db.getOffice(2);
	System.out.println(office.getAddress());
	//List<Car> cars = db.lookupAvailableCars("10101010");
	//	for (int i = 0; i < cars.size(); i++) {
	//		System.out.println(cars.get(i).getBrand() + " : " + cars.get(i).getModel());
	//	}
	}
}
