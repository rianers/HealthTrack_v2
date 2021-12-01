package br.com.healthtrack.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * This Java filter demonstrates how to intercept the request and transform the
 * response to implement authentication feature for the website's front-end.
 *
 * @author www.codejava.net
 */
@WebFilter(value= {"/home/*", "/atividades/*", "/perfil/*", "/alimentos/*"})
public class SessionFilter implements Filter {
	private HttpServletRequest httpRequest;
	private HttpServletResponse httpResponse;

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		httpRequest = (HttpServletRequest) request;
		httpResponse = (HttpServletResponse) response;

		HttpSession session = httpRequest.getSession(false);
		
		boolean isLoggedIn = (session != null && session.getAttribute("user") != null);

		if (isLoggedIn) {
			chain.doFilter(request, response);
		} else {
			httpResponse.sendRedirect(httpRequest.getContextPath() + "/entrar");
		}
	}

	public void destroy() {
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}