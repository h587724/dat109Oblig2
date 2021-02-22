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

/**
 * Servlet implementation class ChooseCar
 */
@WebServlet("/chooseCar")
public class chooseCar extends HttpServlet {
	private static final long serialVersionUID = 1L;
    Database db = new Database();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public chooseCar() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String selectedCar = request.getParameter("selectedCarId");
		request.setAttribute("selectedCarId", selectedCar);
		
		try {
			Car car = db.getCar(Integer.parseInt(selectedCar));
			request.setAttribute("selectedCar", car);
			session.setAttribute("selectedCar", car);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		RequestDispatcher rd = request.getRequestDispatcher("confirmReservation.jsp");
		rd.forward(request, response);
	}

}
