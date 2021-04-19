package com.servlet.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.VideoDAO;
import com.entity.Video;

@WebServlet("/Details")
public class DetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private VideoDAO dao;
    
    public DetailServlet() {
        super();

        this.dao = new VideoDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		Video entity = this.dao.findById(id);

		
		List<Video> listDetail = this.dao.getListDetail();

		request.setAttribute("det", entity);
		request.setAttribute("listD", listDetail);
		
		request.getRequestDispatcher("/views/user/Details.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
