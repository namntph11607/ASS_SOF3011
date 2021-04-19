package com.servlet.user;

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
import com.entity.Share;
import com.entity.Video;

@WebServlet({"/Favorite", "/unlike","/SendEmailfromFvr"})
public class favoriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FavoriteDAO daoFavorite;
	private VideoDAO daoVideo;
	private ShareDAO daoShare;
	
    public favoriteServlet() {
        super();

        this.daoVideo = new VideoDAO();
		this.daoShare = new ShareDAO();
        this.daoFavorite = new FavoriteDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		if(uri.contains("Favorite")) {
			this.index(request, response);
		}else if(uri.contains("SendEmailfromFvr")) {
			this.sendEmail(request, response);
		}else if(uri.contains("unlike")) {
			this.unlike(request, response);
		}
	}
	
	public void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idUserStr = request.getSession().getAttribute("UserID").toString();
		int id = Integer.parseInt(idUserStr);
		
		List<Video> listFavorite = this.daoFavorite.getListFavorite(id);
		
		request.setAttribute("listFvr", listFavorite);
		request.getRequestDispatcher("/views/user/favorite.jsp").forward(request, response);
	}

	public void unlike(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idVideoStr = request.getParameter("videoId");
		String idUserStr = request.getSession().getAttribute("UserID").toString();
		
		System.out.println(idVideoStr);
		System.out.println(idUserStr);

		int idVid = Integer.parseInt(idVideoStr);
		int idUser = Integer.parseInt(idUserStr);

		this.daoFavorite.UnLike(idVid, idUser);

		response.sendRedirect("/ASS_SOF3011/Favorite");
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
			response.sendRedirect("/ASS_SOF3011/Favorite");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
}
