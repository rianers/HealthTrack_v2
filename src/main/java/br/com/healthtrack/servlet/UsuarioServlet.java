package br.com.healthtrack.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.healthtrack.DAOImpl.UserDAOImpl;
import br.com.healthtrack.model.UserModel;
import br.com.healthtrack.util.PasswordHash;

/**
 * Servlet implementation class UsuarioEditarServlet
 */
@WebServlet(name = "UsuarioEditarServlet", value = { "/perfil", "/perfil/editar", "/cadastro", "/cadastrar" })
public class UsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UsuarioServlet() {
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
			case "/cadastro":
				// showCreate(request, response); GET
				showCreate(request, response);
				break;
			case "/cadastrar":
				// create(request, response); POST
				create(request, response);
				break;
			case "/perfil":
				// get(request, response); GET
				get(request, response);
				break;
			case "/perfil/editar":
				// update(request, response); POST
				update(request, response);
				break;
			default:
				break;
			}
		} catch (SQLException err) {
			throw new ServletException(err);
		}
	}

	private void showCreate(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		request.removeAttribute("name");
		request.removeAttribute("email");
		request.removeAttribute("height");
		request.removeAttribute("weight");
		request.removeAttribute("gender");
		request.removeAttribute("password");
		request.removeAttribute("confirmPassword");
		request.removeAttribute("errorMessages");
		request.getRequestDispatcher("/pages/usuario/criar.jsp").forward(request, response);
	}

	private void create(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		UserDAOImpl userDao = new UserDAOImpl();
		UserModel userModel = new UserModel();

		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		String email = request.getParameter("email");

		UserModel user = userDao.getByEmail(email);

		ArrayList<String> errorMessages = new ArrayList<String>(2);

		if (user != null) {
			errorMessages.add("Esse e-mail já está cadastrado");
		}

		if (!password.equals(confirmPassword)) {
			errorMessages.add("Suas senhas estão diferentes");
		}

		if (errorMessages.size() == 0) {
			userModel.setUserGender(request.getParameter("gender"));
			userModel.setUserHeight(Double.parseDouble(request.getParameter("height")));
			userModel.setUserWeight(Double.parseDouble(request.getParameter("weight")));
			userModel.setUserMail(email);
			userModel.setUserName(request.getParameter("name"));

			userModel.setUserPassword(PasswordHash.generatePasswordHash(password));

			userDao.insert(userModel);

			user = userDao.getByEmail(email);

			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			response.sendRedirect(request.getContextPath() + "/home");
		} else {
			request.setAttribute("name", request.getParameter("name"));
			request.setAttribute("email", request.getParameter("email"));
			request.setAttribute("height", request.getParameter("height"));
			request.setAttribute("weight", request.getParameter("weight"));
			request.setAttribute("gender", request.getParameter("gender"));
			request.setAttribute("password", password);
			request.setAttribute("confirmPassword", confirmPassword);
			request.setAttribute("errorMessages", errorMessages);
			request.getRequestDispatcher("/pages/usuario/criar.jsp").forward(request, response);
		}
	}

	private void get(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		HttpSession session = request.getSession();
		UserModel user = (UserModel) session.getAttribute("user");

		request.setAttribute("successMessages", null);
		request.setAttribute("errorMessages", null);
		request.setAttribute("errorMessagesTitle", null);
		request.setAttribute("successMessagesTitle", null);

		request.setAttribute("userId", user.getUserId());
		request.setAttribute("name", user.getUserName());
		request.setAttribute("email", user.getUserMail());
		request.setAttribute("height", user.getUserHeight());
		request.setAttribute("weight", user.getUserWeight());
		request.setAttribute("gender", user.getUserGender());

		request.getRequestDispatcher("/pages/usuario/index.jsp").forward(request, response);
	}

	private void update(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		UserDAOImpl userDao = new UserDAOImpl();
		UserModel userModel = new UserModel();

		HttpSession session = request.getSession();
		UserModel currentSessionUser = (UserModel) session.getAttribute("user");

		ArrayList<String> errorMessages = new ArrayList<String>(1);
		ArrayList<String> successMessages = new ArrayList<String>(1);
		String errorMessagesTitle = null;
		String successMessagesTitle = null;

		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		Integer userId = currentSessionUser.getUserId();

		if (password != null && password != "") {
			if (!password.equals(confirmPassword)) {
				errorMessagesTitle = "Atenção";
				errorMessages.add("Suas senhas estão diferentes");
			}
		}

		if (errorMessages.size() == 0) {
			userModel.setUserGender(request.getParameter("gender"));
			userModel.setUserHeight(Double.parseDouble(request.getParameter("height")));
			userModel.setUserWeight(Double.parseDouble(request.getParameter("weight")));
			userModel.setUserMail(request.getParameter("email"));
			userModel.setUserName(request.getParameter("name"));

			successMessagesTitle = "Sucesso";
			if (password != null && password != "") {
				userDao.update(userModel, userId, PasswordHash.generatePasswordHash(password));
				successMessages.add("Perfil e senha atualizados com sucesso.");
			} else {
				userDao.update(userModel, userId);
				successMessages.add("Perfil atualizado com sucesso.");
			}

			UserModel userUpdated = userDao.get(userId);
			session.setAttribute("user", userUpdated);

			request.setAttribute("userId", userUpdated.getUserId());
			request.setAttribute("name", userUpdated.getUserName());
			request.setAttribute("email", userUpdated.getUserMail());
			request.setAttribute("height", userUpdated.getUserHeight());
			request.setAttribute("weight", userUpdated.getUserWeight());
			request.setAttribute("gender", userUpdated.getUserGender());

			request.setAttribute("successMessages", successMessages);
			request.setAttribute("successMessagesTitle", successMessagesTitle);
			request.getRequestDispatcher("/pages/usuario/index.jsp").forward(request, response);
		} else {
			request.setAttribute("name", request.getParameter("name"));
			request.setAttribute("email", request.getParameter("email"));
			request.setAttribute("height", request.getParameter("height"));
			request.setAttribute("weight", request.getParameter("weight"));
			request.setAttribute("gender", request.getParameter("gender"));
			request.setAttribute("password", password);
			request.setAttribute("confirmPassword", confirmPassword);
			request.setAttribute("errorMessages", errorMessages);
			request.setAttribute("errorMessagesTitle", errorMessagesTitle);

			request.getRequestDispatcher("/pages/usuario/index.jsp").forward(request, response);
		}
	}
}
