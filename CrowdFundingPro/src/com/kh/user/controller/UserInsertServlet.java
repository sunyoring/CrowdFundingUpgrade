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
 * Servlet implementation class UserInsertServlet
 */
@WebServlet("/insert.me")
public class UserInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String userName = request.getParameter("userName");
		String emailId = request.getParameter("emailId");
		String userPwd = request.getParameter("userPwd");
		String userSsn = request.getParameter("userSsn");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String userCode = request.getParameter("userCode");
		String bNumber = request.getParameter("bNumber"); //사업자번호
		String bName = request.getParameter("bName"); //사업자명
		 
		System.out.println("userCode : " + userCode);

		User loginUser = new User(userCode, emailId, userPwd,userName, userSsn, phone, address);
		if(userCode.equals("03")) {
			loginUser.setbNumber(bNumber);
			loginUser.setbName(bName);
		}
		
		int result = new UserService().insertUser(loginUser); //insert 작업 성공여부를 가져옴
		
		if(result > 0 ) {
			response.sendRedirect(request.getContextPath());
		}else {
			request.setAttribute("msg", "회원가입에 실패했습니다.");
			
			RequestDispatcher view = request.getRequestDispatcher("views/common/errorPage.jsp"); //request에 정보가 있으니 RequestDispatcher 로 에러페이지로 보냄
			view.forward(request,response);
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
