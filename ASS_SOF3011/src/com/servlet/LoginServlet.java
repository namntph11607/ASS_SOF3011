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
 * Servlet implementation class LoginServlet
 */
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO dao;

	public LoginServlet() {
		super();

		this.dao = new UserDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/views/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		User entity = this.dao.login(username, password);
		
		if(entity == null) {
			request.setAttribute("error", "Wrong username or password!");
			request.getRequestDispatcher("/views/login.jsp").forward(request, response);
//			response.sendRedirect("/ASS_SOF3011/Login");
		}else {
			//luu thong tin vao session
			request.getSession().setAttribute("acc", entity);
			request.getSession().setAttribute("UserID", entity.getId());
			if(entity.getRole() == 1) {
				response.sendRedirect("/ASS_SOF3011/Admin/Home");
			}else {	
				response.sendRedirect("/ASS_SOF3011/Home");
			}
		}
		
	}

}
