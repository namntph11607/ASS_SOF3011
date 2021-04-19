package com.servlet.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.dao.VideoDAO;
import com.entity.Video;

@WebServlet("/Admin/Videos/Edit")
public class EditVServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private VideoDAO dao;
	
    public EditVServlet() {
        super();

        this.dao = new VideoDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		Video entity = this.dao.findById(id);

		request.setAttribute("vid", entity);

		request.getRequestDispatcher("/views/admin/VideosADedit.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Video entity = new Video();

		try {
			// anh xa toan bo thong tin entity
			BeanUtils.populate(entity, request.getParameterMap());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(entity.getId() <= 0) {
			this.dao.store(entity);
			response.sendRedirect("/ASS_SOF3011/Admin/Videos");
		}else {
			this.dao.update(entity);
			response.sendRedirect("/ASS_SOF3011/Admin/Videos");
		}
	}

}
