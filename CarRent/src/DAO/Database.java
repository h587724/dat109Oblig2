package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	
	public static void addOffice (Office office) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("insert into office (officeId, phoneNr, address)"
				+ " values (?, ?, ?)");
		statement.setInt(1, office.getOfficeId());
		statement.setString(2, office.getPhoneNr());
		statement.setString(3, office.getAddress());
		statement.execute();
	}
	
	public static void addCar (Car car) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("insert into cars (carId, officeId, brand, model, color, type, availability)"
				+ " values (?, ?, ?, ?, ?, ?, ?)");
		statement.setInt(1, car.getCarId());
		statement.setInt(2, car.getOfficeId());
		statement.setString(3, car.getBrand());
		statement.setString(4, car.getModel());
		statement.setString(5, car.getColor());
		statement.setString(6, car.getType().getType());
		statement.setInt(7, car.getAvailability());
		statement.execute();
	}
	
	public static void addCustomer (Customer customer) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("insert into customers (name, lName, customerId, address, phoneNr, card)"
				+ " values (?, ?, ?, ?, ?, ?)");
		statement.setString(1, customer.getName());
		statement.setString(2, customer.getlName());
		statement.setInt(3, customer.getCustId());
		statement.setString(4, customer.getAddress());
		statement.setString(5, customer.getPhoneNr());
		statement.setInt(6, customer.getCard().getCardId());
		statement.execute();
	}
	
	public static void addCreditCard (Card card) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("insert into creditCard (cardId, number) values (?, ?)");
		statement.setInt(1, card.getCardId());
		statement.setString(2, card.getNumber());
		statement.execute();
	}
	
	public static void addReservation (Reservation res) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("insert into reservations (officeId, customerId, carId, reservationId, timeSpan"
				+ "regDate, rentFromDate, rentTillDate, kmOnCar, cardId)"
				+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		statement.setInt(1, res.getOfficeId());
		statement.setInt(2, res.getCustId());
		statement.setInt(3, res.getCarId());
		statement.setInt(4, res.getReservationId());
		statement.setInt(5, res.getTimeSpan());
		statement.setDate(6, (Date) res.getRegDate());
		statement.setDate(7, (Date) res.getRentFrom());
		statement.setDate(8, (Date) res.getReturnDate());
		statement.setInt(9, res.getKmOnCar());
		statement.setInt(10, res.getCardId());
		statement.execute();
	}
	
	public static Office getOffice (int id) throws SQLException {
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
	
	public static Car getCar (int id) throws SQLException {
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
			car.setType(getPricing(rs.getString("type")));
			car.setAvailability(rs.getInt("availability"));
		}
		return car;
	}
	
	public static Customer getCustomer (int id) throws SQLException {
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
			dude.setCard(getCard(rs.getInt("cardId")));
		}
		return dude;
	}
	
	public static Card getCard (int id) throws SQLException {
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
	
	public static Pricing getPricing (String type) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("select * from pricing where type = ?");
		statement.setString(1, type);
		ResultSet rs = statement.executeQuery();
		Pricing pricing = new Pricing ();
		while (rs.next()) {
			pricing.setType(rs.getString("type"));
			pricing.setPrice(rs.getInt("price"));
		}
		return pricing;
	}
	
	public static Reservation getReservation (int id) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("select * from reservations where reservationId = ?");
		statement.setInt(1, id);
		ResultSet rs = statement.executeQuery();
		Reservation res = new Reservation ();
		while (rs.next()) {
			res.setOfficeId(rs.getInt("officeId"));
			res.setCustId(rs.getInt("customerId"));
			res.setCarId(rs.getInt("carId"));
			res.setTimeSpan(rs.getInt("timeSpan"));
			res.setRegDate(rs.getDate("regDate"));
			res.setRentFrom(rs.getDate("rentFromDate"));
			res.setReturnDate(rs.getDate("rentTillDate"));
			res.setKmOnCar(rs.getInt("kmOnCar"));
			res.setCardId(rs.getInt("cardId"));
			res.setReservationId(rs.getInt("reservationId"));
		}
		return res;
	}
	
	public static void updateCarAvailability (int id, int availability) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("update cars set availability = ? where carId = ?");
		statement.setInt(1, availability);
		statement.setInt(2, id);
		statement.execute();
	}
	
	public static void deleteReservation (int id) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("delete from reservations where reservationId = ?");
		statement.setInt(1, id);
		statement.execute();
	}
	
	public static void updateReservationReturnDate (int id, Date newReturn) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("update reservations set rentTillDate = ? where reservationId = ?");
		statement.setDate(1, newReturn);
		statement.setInt(2, id);
		statement.execute();
	}
	
	public static void main(String[] args) throws ParseException {
		Car car = null;
		try {
			car = getCar(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Type of the car " + car.getBrand() + " " + car.getModel() + " is " + car.getType().getType());
		try {
			updateCarAvailability(1, 10032021);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("The car " + car.getBrand() + " " + car.getModel() + " is available after " + new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH).parse(String.valueOf(car.getAvailability())));
	}
}
