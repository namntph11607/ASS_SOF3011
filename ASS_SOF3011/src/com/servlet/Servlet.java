package com.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.FavoriteDAO;
import com.dao.ShareDAO;
import com.dao.VideoDAO;
import com.entity.Favorite;
import com.entity.Share;
import com.entity.Video;

@WebServlet({ "/Home", "/SendEmail", "/LikeVid" })
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private VideoDAO daoVideo;
	private ShareDAO daoShare;
	private FavoriteDAO daoFavorite;

	public Servlet() {
		super();

		this.daoVideo = new VideoDAO();
		this.daoShare = new ShareDAO();
		this.daoFavorite = new FavoriteDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		if(uri.contains("Home")) {
			this.index(request, response);
		}else if(uri.contains("SendEmail")) {
			this.sendEmail(request, response);
		}else if(uri.contains("LikeVid")) {
			this.LikeVideo(request, response);
		}
	}
	
	public void index(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String pageStr = request.getParameter("page"), limitStr = request.getParameter("limitStr");

			int limit = limitStr == null ? 6 : Integer.parseInt(limitStr),
					page = pageStr == null ? 1 : Integer.parseInt(pageStr), 
					offset = limit * (page - 1);

			List<Video> listTitle = this.daoVideo.paginateTitle(offset, limit);
			
			request.setAttribute("limit", limitStr);
			request.setAttribute("listT", listTitle);
			request.setAttribute("page", page);
			request.getRequestDispatcher("/views/index.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sendEmail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String idStr = request.getParameter("posterId");
			int id = Integer.parseInt(idStr);
			Video entity = this.daoVideo.findById(id);
			System.out.println(idStr);

			final String username = "namntph11607@fpt.edu.vn";
			final String password = "thanhnam2001";
			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");
			// Ket noi voi Smtp Server
			Session session = Session.getInstance(props, new javax.mail.Authenticator() {
				protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
					return new javax.mail.PasswordAuthentication(username, password);
				}
			});
			//
			String emailTo = request.getParameter("email");
			try {
				// Tao message
				Message message = new MimeMessage(session);

				message.setFrom(new InternetAddress(username));// email nguoi gui
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailTo)); // email nguoi nhan
				message.setSubject("ASS_SOF3011 Send POSTER"); // tieu de
				String msg = "<h1> Title: " + entity.getTitle()+ "<br>" + "</h1>"
							+ "<img alt='poster' src='"+ entity.getPoster() + "' width='200px' height='180px'> <br>" + 
						"<a href='http://localhost:8080/ASS_SOF3011/Details?id="+ entity.getId() +"'>Show more</a>";
				message.setContent(msg, "text/html");
//				message.setReplyTo(message.getFrom());

				// Gui message
				Transport.send(message);
				System.out.println("done");
			} catch (Exception e) {
				e.printStackTrace();
			}
			//them vao DB share
			Share share = new Share();
			String userID = request.getSession().getAttribute("UserID").toString();;
			share.setUsid(Integer.parseInt(userID));
			share.setVdid(entity.getId());
			share.setEmail(emailTo);
			share.setShareDate(new Date());
			this.daoShare.insert(share);
			response.sendRedirect("/ASS_SOF3011/Home");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void LikeVideo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userIDstr = request.getSession().getAttribute("UserID").toString();
		String videoIDstr = request.getParameter("videoId");
		System.out.println("vidID: " + videoIDstr +", user: " + userIDstr);
		try {
			int UsId = Integer.parseInt(userIDstr);
			int VdId = Integer.parseInt(videoIDstr);
			
			Favorite favo = this.daoFavorite.checkFavorite(VdId, UsId);
			
			if(favo != null) {
				request.setAttribute("favoNull", favo);
				response.sendRedirect("/ASS_SOF3011/Home");
			}else {
				this.daoFavorite.saveLike(UsId, VdId);
				response.sendRedirect("/ASS_SOF3011/Favorite");	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
