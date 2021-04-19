package com.servlet.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.UserDAO;
import com.entity.User;

@WebServlet("/myAccount")
public class myAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO dao;

	public myAccountServlet() {
		super();

		this.dao = new UserDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession sess = request.getSession();
		sess.getAttribute("acc");

		request.getRequestDispatcher("/views/user/myAccount.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		User entity = this.dao.findById(id);
		try {
			String fullname = request.getParameter("fullname");
			String email = request.getParameter("email");

			entity.setFullname(fullname);
			entity.setEmail(email);

			this.dao.update(entity);
			response.sendRedirect("/ASS_SOF3011/myAccount");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
