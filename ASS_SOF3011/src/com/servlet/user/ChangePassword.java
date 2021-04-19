package com.servlet.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.UserDAO;
import com.entity.User;

@WebServlet("/ChangePassword")
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO dao;

	public ChangePassword() {
		super();

		this.dao = new UserDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().getAttribute("acc");

		request.getRequestDispatcher("/views/user/myAccount.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		User entity = this.dao.findById(id);
		try {
			String username = request.getParameter("CurrentUser");
			String CurrentPass = request.getParameter("CurrentPass");
			String NewPass = request.getParameter("NewPass");
			String ConfirmPass = request.getParameter("ConfirmPass");
			//
			if(entity.getUsername().equalsIgnoreCase(username)) {
				//
				if(entity.getPassword().equalsIgnoreCase(CurrentPass)) {
					//
					if(NewPass.equalsIgnoreCase(ConfirmPass)) {
						entity.setPassword(ConfirmPass);
						//
						this.dao.update(entity);
						response.sendRedirect("/ASS_SOF3011/myAccount");
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
