package br.com.healthtrack.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.List;

import br.com.healthtrack.DAOImpl.PhysicalActivityDAOImpl;
import br.com.healthtrack.model.UserActivityCategoryModel;
import br.com.healthtrack.model.UserActivityModel;
import br.com.healthtrack.model.UserModel;

/**
 * Servlet implementation class AtividadesServlet
 */
@WebServlet(name = "AtividadesServlet", value = { "/atividades", "/atividades/nova", "/atividades/criar", "/atividades/atividade", "/atividades/editar", "/atividades/excluir" })
public class AtividadeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AtividadeServlet() {
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
			case "/atividades/nova":
				// showCreate(request, response); GET
				showCreate(request, response);
				break;
			case "/atividades/criar":
				// create(request, response); POST
				create(request, response);
				break;
			case "/atividades/atividade":
				// get(request, response); GET
				showUpdate(request, response);
				break;
			case "/atividades/editar":
				// update(request, response); POST
				update(request, response);
				break;
			case "/atividades/excluir":
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

		PhysicalActivityDAOImpl physicalActivityDAO = new PhysicalActivityDAOImpl();

		List<UserActivityCategoryModel> activityCategory = physicalActivityDAO.getAllWithCategory(user.getUserId());

		request.setAttribute("physicalActivities", activityCategory);
		request.getRequestDispatcher("/pages/atividade/index.jsp").forward(request, response);
	}

	private void showCreate(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		request.getRequestDispatcher("/pages/atividade/adicionar.jsp").forward(request, response);
	}

	private void create(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		UserActivityModel userActivity = new UserActivityModel();
		PhysicalActivityDAOImpl userActivityDAOImpl = new PhysicalActivityDAOImpl();

		HttpSession session = request.getSession();
		UserModel user = (UserModel) session.getAttribute("user");

		userActivity.setActivityType(request.getParameter("activity"));
		userActivity.setActivityDescription(request.getParameter("description"));
		userActivity.setStartTime(request.getParameter("startTime"));
		userActivity.setEndTime(request.getParameter("endTime"));
		userActivity.setCalories(Double.parseDouble(request.getParameter("calories")));
		userActivity.setDate(request.getParameter("date"));
		userActivity.setCategoryId(Integer.parseInt(request.getParameter("category")));
		userActivity.setUserId(user.getUserId());

		userActivityDAOImpl.insert(userActivity);

		response.sendRedirect(request.getContextPath() + "/atividades");
	}
	
	private void showUpdate(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		
		PhysicalActivityDAOImpl userActivityDAOImpl = new PhysicalActivityDAOImpl();
		
		Integer userActivityId = Integer.parseInt(request.getParameter("id"));
		UserActivityCategoryModel userActivity = userActivityDAOImpl.getWithCategory(userActivityId);
		
		request.setAttribute("userActivity", userActivity);
		request.getRequestDispatcher("/pages/atividade/editar.jsp").forward(request, response);
	}

	private void update(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		UserActivityModel userActivity = new UserActivityModel();
		PhysicalActivityDAOImpl userActivityDAOImpl = new PhysicalActivityDAOImpl();
		
		Integer userActivityId = Integer.parseInt(request.getParameter("id"));
		userActivity.setActivityType(request.getParameter("activity"));
		userActivity.setActivityDescription(request.getParameter("description"));
		userActivity.setStartTime(request.getParameter("startTime"));
		userActivity.setEndTime(request.getParameter("endTime"));
		userActivity.setCalories(Double.parseDouble(request.getParameter("calories")));
		userActivity.setDate(request.getParameter("date"));
		userActivity.setCategoryId(Integer.parseInt(request.getParameter("category")));
		
		userActivityDAOImpl.update(userActivityId, userActivity);
		
		response.sendRedirect(request.getContextPath() + "/atividades");
	}
	
	private void remove(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		
		PhysicalActivityDAOImpl userActivityDAOImpl = new PhysicalActivityDAOImpl();
		Integer userActivityId = Integer.parseInt(request.getParameter("id"));
		userActivityDAOImpl.remove(userActivityId);
		
		response.sendRedirect(request.getContextPath() + "/atividades");
	}
	
}
