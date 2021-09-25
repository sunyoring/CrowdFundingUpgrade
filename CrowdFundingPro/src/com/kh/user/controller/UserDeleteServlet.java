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
 * Servlet implementation class UserDeleteServlet
 */
@WebServlet("/delete.me")
public class UserDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();

		User loginUser = (User)session.getAttribute("loginUser");
		
		String emailId = loginUser.getEmailId();
		System.out.println("deleteServlet emailId : " + emailId);

		int result = new UserService().deleteUser(emailId);
		System.out.println("servlet result : " + result);
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();

		if(result > 0 ) {
			out.print("deletesuccess");
			session.removeAttribute("loginUser");
			session.setAttribute("msg", "회원탈퇴가 완료되었습니다. 복구관련사항은 관리자에게 문의하세요");
		}else {
			out.print("deletfail");
			request.setAttribute("msg", "회원탈퇴가 실패했습니다.");
			RequestDispatcher view = request.getRequestDispatcher("views/common/errorPage.jsp");
			view.forward(request, response);
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
