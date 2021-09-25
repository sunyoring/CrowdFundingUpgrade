package com.kh.user.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.user.model.service.UserService;
import com.kh.user.util.GenerateCertNumber;

/**
 * Servlet implementation class EmailCheckServlet
 */
@WebServlet("/sendEmail.me")
public class SendEmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SendEmailServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		String emailId = request.getParameter("emailId");
		System.out.println("*************"+emailId);
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		
		int duplicate = new UserService().emailIdCheck(emailId);
		if(duplicate != 0) {
			out.print("duplicate");
		}else {
			String host = "smtp.gmail.com";
			String user = "sunyoya2@gmail.com";
			String password = "tpal1234!!";
			
			String toEmail = emailId;
			
			//SMTP 서버 정보 설정
			Properties prop = new Properties();
			prop.put("mail.smtp.host",host);
			prop.put("mail.smtp.port", 465);
			prop.put("mail.smtp.auth", "true"); 
			prop.put("mail.smtp.ssl.enable", "true"); 
			prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
			prop.put("mail.smtp.starttls.required", "true");
			prop.put("mail.smtp.ssl.protocols", "TLSv1.2");

			//인증 번호 생성기			
			GenerateCertNumber gc = new GenerateCertNumber();
			gc.setCertNumLength(6);
			String AuthenticationKey = gc.excuteGenerate();
			 
			session.setAttribute("AuthenticationKey", AuthenticationKey);
			System.out.println("AuthenticationKey : " +  AuthenticationKey);
			
			
			Session mailSession = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(user, password);
				}
			});
			
			//email전송
			try {
				MimeMessage msg = new MimeMessage(mailSession);
				msg.setFrom(new InternetAddress(user, "KH"));
				msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));

				// 메일 제목
				msg.setSubject("안녕하세요  인증 메일입니다!");
				// 메일 내용
				msg.setText("인증 번호 : " + AuthenticationKey +"\n\n인증번호를 회원가입 입력란에 입력해주세요.");
				Transport.send(msg);
				System.out.println("이메일 전송");

			} catch (Exception e) {
				e.printStackTrace();// TODO: handle exception
			}
			out.print("sendSuccess");
		}

		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
