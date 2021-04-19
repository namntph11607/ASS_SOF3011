package com.servlet.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.UserDAO;
import com.entity.User;

@WebServlet({ "/Admin/Users", "/Admin/Users/Delete" })
public class UsersAdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO dao;

	public UsersAdServlet() {
		super();

		this.dao = new UserDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

//		String uri = request.getRequestURI();

//		if (uri.contains("Users")) {
			this.index(request, response);
//		} else if (uri.contains("Delete")) {
//			this.delete(request, response);
//		}

	}

	private void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pageStr = request.getParameter("page"), 
				limitStr = request.getParameter("limitStr");

		int limit = limitStr == null ? 10 : Integer.parseInt(limitStr),
				page = pageStr == null ? 1 : Integer.parseInt(pageStr), offset = limit * (page - 1);

		List<User> listUser = this.dao.paginate(offset, limit);

		request.setAttribute("listU", listUser);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/views/admin/UsersAD.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr = request.getParameter("id");

		if (idStr == null) {
			// Bad request
		}

		int id = Integer.parseInt(idStr);

		User entity = new User();

		entity.setId(id);

		this.dao.delete(entity);

		response.sendRedirect("/ASS_SOF3011/Admin/Users");
	}

}
