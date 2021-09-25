package com.kh.user.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.user.model.service.UserService;
import com.kh.user.model.vo.User;

/**
 *  ****************************** 테스트중
 */
@WebServlet("/gLogin.me")
public class GoogleLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoogleLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String emailId = request.getParameter("emailId");
		String name = request.getParameter("name");
		String pImg = request.getParameter("pImg");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();

		User gLoginUser = new User();
		gLoginUser.setEmailId(emailId);
		gLoginUser.setUserName(name);
		
		int result = new UserService().insertUser(gLoginUser); //insert작업으로 성공여부를 가져옴
		
		if(result > 0 ) {
			request.getSession().setAttribute("msg", " 구글계정 회원가입 성공");
			response.sendRedirect(request.getContextPath());
		}else {
			request.setAttribute("msg", " 구글계정 회원가입 실패");
			
			RequestDispatcher view = request.getRequestDispatcher("views/common/errorPage.jsp"); //request에 정보가 있으니 RequestDispatcher 로 에러페이지로 보냄
			view.forward(request,response);
		}
		
		
		
		int duplicate = new UserService().emailIdCheck(emailId);
		if(duplicate != 0) {
			out.print("duplicate");
		}else {
			session.setAttribute("loginUser",gLoginUser);
			out.print("gLoginSuccess");
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
