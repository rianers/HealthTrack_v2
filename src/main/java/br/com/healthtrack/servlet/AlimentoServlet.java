package br.com.healthtrack.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.healthtrack.DAOImpl.FoodDAOImpl;
import br.com.healthtrack.model.FoodModel;
import br.com.healthtrack.model.UserModel;

/**
 * Servlet implementation class AlimentosServlet
 */
@WebServlet(name = "AlimentosServlet", value = { "/alimentos", "/alimentos/nova", "/alimentos/criar", "/alimentos/alimento", "/alimentos/editar", "/alimentos/excluir" })
public class AlimentoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AlimentoServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doMethod(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doMethod(request, response);
	}

	protected void doMethod(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String action = request.getServletPath();
		try {
			switch (action) {
			case "/alimentos/nova":
				// showCreate(request, response); GET
				showCreate(request, response);
				break;
			case "/alimentos/criar":
				// create(request, response); POST
				create(request, response);
				break;
			case "/alimentos/alimento":
				// get(request, response); GET
				showUpdate(request, response);
				break;
			case "/alimentos/editar":
				// update(request, response); POST
				update(request, response);
				break;
			case "/alimentos/excluir":
				// delete(request, response); POST
				remove(request, response);
				break;
			default:
				// list all
				getAll(request, response);
				break;
			}
		} catch (SQLException err) {
			throw new ServletException(err);
		}
	}
	
	private void getAll(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		HttpSession session = request.getSession();
		UserModel user = (UserModel) session.getAttribute("user");

		FoodDAOImpl foodDAO = new FoodDAOImpl();
		List<FoodModel> foods = foodDAO.getAll(user.getUserId());
		
		request.setAttribute("foods", foods);
		request.getRequestDispatcher("/pages/alimento/index.jsp").forward(request, response);
	}
	
	private void showCreate(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		request.getRequestDispatcher("/pages/alimento/adicionar.jsp").forward(request, response);
	}

	private void create(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		FoodModel food = new FoodModel();
		FoodDAOImpl foodDAOImpl = new FoodDAOImpl();
		
		HttpSession session = request.getSession();
		UserModel user = (UserModel) session.getAttribute("user");
		
		food.setFoodName(request.getParameter("foodName"));
		food.setQuantityCalories(Double.parseDouble(request.getParameter("calories")));
		food.setHour(request.getParameter("time"));
		food.setFoodDescription(request.getParameter("description"));
		food.setDate(request.getParameter("date"));
		food.setUserId(user.getUserId());

		foodDAOImpl.insert(food);

		response.sendRedirect(request.getContextPath() + "/alimentos");
	}
	
	private void showUpdate(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		
		FoodDAOImpl foodDAOImpl = new FoodDAOImpl();
		
		Integer foodId = Integer.parseInt(request.getParameter("id"));
		FoodModel food = foodDAOImpl.get(foodId);
		
		
		request.setAttribute("food", food);
		request.getRequestDispatcher("/pages/alimento/editar.jsp").forward(request, response);
	}

	private void update(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		
		FoodModel food = new FoodModel();
		FoodDAOImpl foodDAOImpl = new FoodDAOImpl();
		
		Integer foodId = Integer.parseInt(request.getParameter("id"));
		food.setFoodName(request.getParameter("foodName"));
		food.setQuantityCalories(Double.parseDouble(request.getParameter("calories")));
		food.setHour(request.getParameter("hour"));
		food.setFoodDescription(request.getParameter("description"));
		food.setDate(request.getParameter("date"));
		
		foodDAOImpl.update(foodId, food);
		
		response.sendRedirect(request.getContextPath() + "/alimentos");
	}
	
	private void remove(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		
		FoodDAOImpl foodDAOImpl = new FoodDAOImpl();
		Integer foodId = Integer.parseInt(request.getParameter("id"));
		foodDAOImpl.remove(foodId);
		
		response.sendRedirect(request.getContextPath() + "/alimentos");
	}
	
}
