package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;

import DAO.Database;
import bean.Car;
import bean.Office;

@WebServlet("/mainPage")
public class mainPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Database db = new Database();
	
    public mainPage() {
        super();
    }
    
    /*private void listOffices (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
    		
    		db = new Database();
			List<Office> officeList = new ArrayList<>();
			officeList = Database.getOfficeList();
			request.setAttribute("officeList", officeList);
		} catch (Exception e) {
			System.out.println("Error! " + e);
		}
    }
    try {
			List<Office> officeList = new ArrayList<>();
			officeList = db.getOfficeList();
			request.setAttribute("officeList", officeList);
			RequestDispatcher rd = request.getRequestDispatcher("MainPage.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			System.out.println("Error! " + e);
		}
    */
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Office> officeList = new ArrayList<>();
		try {
			officeList = db.getOfficeList();
			request.setAttribute("officeList", officeList);
			
			RequestDispatcher rd = request.getRequestDispatcher("MainPage.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		String from = request.getParameter("rentFromDate");
		String toDate = request.getParameter("rentTillDate");
		int officeId = Integer.parseInt(request.getParameter("selectedOfficeId"));
		request.setAttribute("rentTillDate", toDate);
		request.setAttribute("rentFrom", from);
		
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
	    Date firstDate = null;
		try {
			firstDate = sdf.parse(from);
		} catch (ParseException e2) {
			e2.printStackTrace();
		}
	    Date secondDate = null;
		try {
			secondDate = sdf.parse(toDate);
		} catch (ParseException e2) {
			e2.printStackTrace();
		}

	    long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
	    long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
		
		session.setAttribute("timeDifference", diff);
		session.setAttribute("rentFromDate", from);
		session.setAttribute("rentTillDate", toDate);
		session.setAttribute("selectedOfficeId", officeId);
		
		List<Car> cars = new ArrayList<>();
		try {
			System.out.println("************************");
			cars = db.lookupAvailableCars(from, officeId);
		} catch (SQLException e1) {
			System.out.println("SQL exception");
		} catch (ParseException e1) {
			System.out.println("Parse exception");
		}
		request.setAttribute("carList", cars);
		
		request.setAttribute("selectedOfficeId", officeId);
		
		Office selectedOffice = null;
		try {
			selectedOffice = db.getOffice(officeId);
		} catch (SQLException e) {	}
		request.setAttribute("selectedOfficeAddress", selectedOffice.getAddress());
		session.setAttribute("selectedOfficeAddress", selectedOffice.getAddress());
		
		RequestDispatcher rd = request.getRequestDispatcher("availableCars.jsp");
		rd.forward(request, response);
	}

}
