package br.com.healthtrack.servlet;

import java.io.IOException;
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
 * Servlet implementation class AuthServlet
 */
@WebServlet(name= "AuthServlet" , value={ "/entrar", "/login", "/logout" })
public class AuthServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AuthServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		
		if(action.equals("/logout")) {
			HttpSession session = request.getSession();
			session.removeAttribute("user");
			response.sendRedirect(request.getContextPath() + "/entrar");
			
		} else {
			request.removeAttribute("password");
			request.removeAttribute("email");
			request.removeAttribute("errorMessage");
			
			request.getRequestDispatcher("/pages/entrar/index.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDAOImpl userDao = new UserDAOImpl();
		
		String email = (String) request.getParameter("email");
		String password = (String) request.getParameter("password");
		boolean hasError = false;
		
		UserModel user = userDao.getByEmail(email);
		
		
		if (user != null) {
			boolean matchPassword = userDao.checkPassword(user.getUserId(), PasswordHash.generatePasswordHash(password));
			if (matchPassword == true) {
				HttpSession session = request.getSession();
				session.setAttribute("user" , user);
				response.sendRedirect(request.getContextPath() + "/home");
			}else {
				hasError = true;
			}
		} else {
			hasError = true;
		}
		
		if (hasError) {
			request.setAttribute("email",request.getParameter("email"));
			request.setAttribute("password", request.getParameter("password"));
			request.setAttribute("errorMessage", "Usuário ou senha incorretos");
			request.getRequestDispatcher("/pages/entrar/index.jsp").forward(request, response);
		}
	}
	

}
