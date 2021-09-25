package com.kh.user.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.user.model.service.UserService;
import com.kh.user.model.vo.User;

/**
 * Servlet implementation class UserUpdateServlet
 */
@WebServlet("/update.me")
public class UserUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserUpdateServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		String userId = ((User) request.getSession().getAttribute("loginUser")).getEmailId();
		String userPwd = ((User) request.getSession().getAttribute("loginUser")).getUserPwd();
		String newPwd = request.getParameter("userPwd");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		 
		User updateUser = null;

		if (userPwd != newPwd) { // 로그인유저의 비밀번호와 정보수정에서 넘어온 비밀번호가 다르면 비밀번호를 변경하는 것으로 판단
			updateUser = new UserService() .updateUser(new User(userId,newPwd,phone,address));
		} else {
			updateUser = new UserService() .updateUser(new User(userId,userPwd,phone,address));
		}

		RequestDispatcher view = request.getRequestDispatcher("views/user/myPage.jsp");
		if (updateUser != null) {
			request.setAttribute("sTag", "Y");
			request.setAttribute("msg", "성공적으로 비밀번호를 변경하였습니다.");
			request.getSession().setAttribute("loginUser", updateUser);
		} else {
			request.setAttribute("msg", "비밀번호 변경에 실패했습니다.");
		}
		view.forward(request, response);
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
