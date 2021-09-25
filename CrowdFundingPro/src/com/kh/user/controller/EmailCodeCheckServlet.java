package com.kh.user.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class EmailCodeCheckServlet
 */
@WebServlet("/emailCodeCheck.me")
public class EmailCodeCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmailCodeCheckServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();

		String emailCode = request.getParameter("emailCode");
		System.out.println("인증코드 확인 서블릿 사용자 입력 코드  : " + emailCode);
	
		String AuthenticationKey = (String)session.getAttribute("AuthenticationKey");
		System.out.println("인증코드 확인 서블릿 세션 보유 코드  : " + AuthenticationKey);
		response.setCharacterEncoding("UTF-8");
		System.out.println(AuthenticationKey.equals(emailCode));
		if(AuthenticationKey.equals(emailCode)) {
			out.print("check");
		}else {
			out.print("uncheck");
		}
		out.flush();
		out.close();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
