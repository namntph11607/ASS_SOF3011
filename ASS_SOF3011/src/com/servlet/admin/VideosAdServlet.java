package com.servlet.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.VideoDAO;
import com.entity.Video;

@WebServlet({"/Admin/Videos", "/Admin/Videos/Delete"})
public class VideosAdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private VideoDAO dao;
     
    public VideosAdServlet() {
        super();

        this.dao = new VideoDAO();
    }

	protected void doGet(
			HttpServletRequest request, 
			HttpServletResponse response
			) throws ServletException, IOException {
		String pageStr = request.getParameter("page"),
				limitStr = request.getParameter("limit");
		
		int limit = limitStr == null ? 10 : Integer.parseInt(limitStr), 
			page = pageStr == null ? 1 : Integer.parseInt(pageStr),
			offset = limit * (page - 1);
		
		List<Video> listVideo = this.dao.paginate(offset, limit);
		
		request.setAttribute("limit", limitStr);
		request.setAttribute("listV", listVideo);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/views/admin/VideosAD.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr = request.getParameter("id");

		if (idStr == null) {
			// Bad request
		}

		int id = Integer.parseInt(idStr);

		Video entity = new Video();

		entity.setId(id);

		this.dao.delete(entity);

		response.sendRedirect("/ASS_SOF3011/Admin/Videos");
	}

}
