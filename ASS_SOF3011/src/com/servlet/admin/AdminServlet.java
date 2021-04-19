package com.servlet.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Admin/Home")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String viewsFavorites = "/views/dashboard/Favorites.jsp";
		String viewsFavorite_User = "/views/dashboard/FavoriteUser.jsp";
		String viewsShared_Friend = "/views/dashboard/sharedFriend.jsp";
		request.setAttribute("viewsFavorites", viewsFavorites);
		request.setAttribute("viewsFavorite_User", viewsFavorite_User);
		request.setAttribute("viewsShared_Friend", viewsShared_Friend);
		request.getRequestDispatcher("/views/admin/admin.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
