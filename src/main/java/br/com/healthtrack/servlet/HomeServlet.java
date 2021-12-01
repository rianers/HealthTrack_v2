package br.com.healthtrack.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.healthtrack.DAOImpl.FoodDAOImpl;
import br.com.healthtrack.DAOImpl.PhysicalActivityDAOImpl;
import br.com.healthtrack.model.UserModel;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet(name= "HomeServlet" ,value={ "/home" })
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public HomeServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserModel user = (UserModel) session.getAttribute("user");
		
		PhysicalActivityDAOImpl physical =  new PhysicalActivityDAOImpl();
		FoodDAOImpl food = new FoodDAOImpl();
		
		request.setAttribute("userName", user.getUserName());
		request.setAttribute("weight", user.getUserWeight());
		request.setAttribute("height", user.getUserHeight());
		Double imc = user.calculateIMC(user.getUserWeight(), user.getUserHeight());
		request.setAttribute("imc", imc);
		request.setAttribute("lastFood", food.getLastFood(user.getUserId()));
		request.setAttribute("lastActivity", physical.getLastActivity(user.getUserId()));
		request.getRequestDispatcher("/pages/home/index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
