package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.Database;
import bean.Car;
import bean.Card;
import bean.Customer;
import bean.Reservation;

@WebServlet("/confirmReservation")
public class confirmReservation extends HttpServlet {
	private static final long serialVersionUID = 1L;
      Database db = new Database();
 
    public confirmReservation() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("confirmedReservation.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String name = request.getParameter("custName");
		String lName = request.getParameter("custLName");
		String phoneNumb = request.getParameter("custPhoneNr");
		String cardNum = request.getParameter("cardNr");
		String address = request.getParameter("custAddress");

		Card card = new Card(cardNum);
		try {
			db.addCreditCard(card);
		} catch (SQLException e) {
			System.out.println("Exception at add credit card!");
		}
		try {
			card = db.getCard(cardNum);
		} catch (SQLException e) {
			System.out.println("Exception at get credit card!");
		}
		Customer customer = new Customer(name, lName, address, phoneNumb, card.getCardId());
		try {
			db.addCustomer(customer);
		} catch (SQLException e) {
			System.out.println("Exception at add customer!");
		}
		try {
			customer = db.getCustomerPhone(phoneNumb);
		} catch (SQLException e) {
			System.out.println("Exception at get customer");
		}
		Car car = (Car) session.getAttribute("selectedCar");
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date (System.currentTimeMillis());
		String dateStr = formatter.format(date);
		
		long difference = (long) session.getAttribute("timeDifference");
		int differenceInt = (int) difference;
		int selectedOfficeId = (int) session.getAttribute("selectedOfficeId");
		Reservation res = new Reservation(selectedOfficeId,
				customer.getCustId(), car.getCarId(), differenceInt,
				dateStr, (String) session.getAttribute("rentFromDate"), (String) session.getAttribute("rentTillDate"), 0, card.getCardId());
		int resRef = 0;
		try {
			resRef = db.getReservationId(customer.getCustId());
		} catch (SQLException e1) {
			System.out.println("Exception at get Reservation Id");
		}
		session.setAttribute("reservationId", resRef);
		try {
			db.addReservation(res);
		} catch (SQLException e) {
			System.out.println("RES SQL EXCEPTION");
		} catch (ParseException e) {
			System.out.println("RES PARSE EXCEPTION");
		}
		try {
			db.updateCarAvailability(car.getCarId(), (String) session.getAttribute("rentTillDate"));
		} catch (SQLException e) {
			System.out.println("Exception at update car! SQL");
		} catch (ParseException e) {
			System.out.println("Exception at update car! Parse");
		}
		RequestDispatcher rd = request.getRequestDispatcher("confirmedReservation.jsp");
		rd.forward(request, response);
	}

}
