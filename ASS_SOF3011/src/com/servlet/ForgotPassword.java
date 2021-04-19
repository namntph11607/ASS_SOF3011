package com.servlet;

import java.io.IOException;
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

import com.dao.UserDAO;
import com.entity.User;

@WebServlet("/ForgotPassword")
public class ForgotPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO dao;
     
    public ForgotPassword() {
        super();

		this.dao = new UserDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String username = request.getParameter("usernameF");
			
			User check  = this.dao.checkUsername(username);
			if(check != null) {
				//tim thay username 
				//cho gui password cua username tuong ung den mail da nhap
				final String mailname = "namntph11607@fpt.edu.vn";
				final String passmail = "thanhnam2001";
				Properties props = new Properties();
				props.put("mail.smtp.auth", "true");
				props.put("mail.smtp.starttls.enable", "true");
				props.put("mail.smtp.host", "smtp.gmail.com");
				props.put("mail.smtp.port", "587");
				//Ket noi voi Smtp Server
				Session session = Session.getInstance(props, new javax.mail.Authenticator() {
					protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
						return new javax.mail.PasswordAuthentication(mailname, passmail);
					}
				});
				//
				String emailTo = request.getParameter("emailF");
				
				try {
					//Tao message
					Message message = new MimeMessage(session);
					
					message.setFrom(new InternetAddress(username));//email nguoi gui
					message.setRecipients(
							Message.RecipientType.TO, 
							InternetAddress.parse(emailTo)); //email nguoi nhan
					message.setSubject("Forgot Password ASS_SOF3011");
					String msg = "<h1>Username: " + check.getUsername() + "<br>" +
							"Password: " + check.getPassword() + "</h1>";
					message.setContent(msg, "text/html");
//					message.setReplyTo(message.getFrom());
					//Gui message
					Transport.send(message);
					System.out.println("done");
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				response.sendRedirect("/ASS_SOF3011/Login");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
