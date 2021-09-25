package com.kh.user.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.user.model.service.UserService;
import com.kh.user.model.vo.User;

/**
 * Servlet implementation class PointChargeServlet
 */
@WebServlet("/charge.me")
public class PointChargeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PointChargeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int userNo = ((User)request.getSession().getAttribute("loginUser")).getUserNo();
		int recharge = Integer.parseInt(request.getParameter("recharge"));
		
		System.out.println(userNo);
		System.out.println(recharge);
		
		int result = new UserService().chargePoint(userNo,recharge);
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		String PageUrl = null;
		
		
		if(result > 0) {
			PageUrl = "mypage.me";
			writer.println("<script>alert('충전이 완료되었습니다.'); location.href='" + PageUrl
					+ "';</script>");
			writer.close();
		}else {
			PageUrl = "mypage.me";
			writer.println("<script>alert('충전이 실패하였습니다.'); location.href='" + PageUrl
					+ "';</script>");
			writer.close();
			
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
