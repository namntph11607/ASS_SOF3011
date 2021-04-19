package com.servlet.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.dao.UserDAO;
import com.entity.User;

@WebServlet("/Admin/Users/Edit")
public class EditUServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserDAO dao;

	public EditUServlet() {
		super();

		this.dao = new UserDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		User entity = this.dao.findById(id);

		request.setAttribute("user", entity);

		request.getRequestDispatcher("/views/admin/UsersADedit.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		System.out.println("EditUserServlet - doPost");

		User entity = new User();

		try {
			// anh xa toan bo thong tin entity
			BeanUtils.populate(entity, request.getParameterMap());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(entity.getId() <= 0) {
			this.dao.store(entity);
			response.sendRedirect("/ASS_SOF3011/Admin/Users");
		}else {
			this.dao.update(entity);
			response.sendRedirect("/ASS_SOF3011/Admin/Users");
		}
	}

}
