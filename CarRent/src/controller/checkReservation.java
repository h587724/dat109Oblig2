package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.Database;
import bean.Car;
import bean.Customer;
import bean.Office;
import bean.Reservation;

/**
 * Servlet implementation class checkReservation
 */
@WebServlet("/checkReservation")
public class checkReservation extends HttpServlet {
	private static final long serialVersionUID = 1L;
    Database db = new Database();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public checkReservation() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String reference = request.getParameter("reference");
		int refInt = Integer.parseInt(reference);
		Reservation res = null;
		try {
			res = db.getReservation(refInt);
		} catch (SQLException e) {
			System.out.println("Exception at main get reference");
		}
		int officeId = res.getOfficeId();
		Office office = null;
		try {
			office = db.getOffice(officeId);
		} catch (SQLException e) {
			System.out.println("Exception at get Office");
		}
		Car car = null;
		try {
			car = db.getCar(res.getCarId());
		} catch (SQLException e) {
			System.out.println("Exception at get car");
		}
		Customer customer = null;
		try {
			customer = db.getCustomer(res.getCustId());
		} catch (Exception e) {
			System.out.println("Exception at get customer");
		}
		request.setAttribute("referenceCar", car);
		request.setAttribute("referenceOffice", office);
		request.setAttribute("referenceCustomer", customer);
		request.setAttribute("reservationRef", res);
		RequestDispatcher rd = request.getRequestDispatcher("checkReservation.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		String reference = request.getParameter("reference");
		String referenceClean = reference.replaceAll("/[^A-Za-z]/g", reference);
		int refInt = Integer.parseInt(referenceClean);
		Reservation res = null;
		try {
			res = db.getReservation(refInt);
		} catch (SQLException e) {
			System.out.println("Exception at main get reference");
		}
		int officeId = res.getOfficeId();
		Office office = null;
		try {
			office = db.getOffice(officeId);
		} catch (SQLException e) {
			System.out.println("Exception at get Office");
		}
		Car car = null;
		try {
			car = db.getCar(res.getCarId());
		} catch (SQLException e) {
			System.out.println("Exception at get car");
		}
		Customer customer = null;
		try {
			customer = db.getCustomer(res.getCustId());
		} catch (Exception e) {
			System.out.println("Exception at get customer");
		}
		request.setAttribute("referenceCar", car);
		request.setAttribute("referenceOffice", office);
		request.setAttribute("referenceCustomer", customer);
		request.setAttribute("reservationRef", res);
		RequestDispatcher rd = request.getRequestDispatcher("checkReservation.jsp");
		rd.forward(request, response);
	}

}
