package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.UserDAO;
import com.entity.User;

/**
 * Servlet implementation class SignUpServlet
 */
@WebServlet("/SignUp")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO dao;

	public SignUpServlet() {
		super();

		this.dao = new UserDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("/ASS_SOF3011/SignUp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User entity = new User();
		try {
			String username = request.getParameter("usernameR");
			String password = request.getParameter("passwordR");
			String fullname = request.getParameter("fullnameR");
			String email = request.getParameter("emailR");
			//
			User check  = this.dao.checkUsername(username);
			if(check != null) {
				//da co username
				//khong cho tao tai khoan
				//
			}else {
				//khong bi trung username -> duoc tao tai khoan
				entity.setUsername(username);
				entity.setPassword(password);
				entity.setFullname(fullname);
				entity.setEmail(email);
				entity.setRole(0);

				this.dao.store(entity);
				response.sendRedirect("/ASS_SOF3011/Login");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
